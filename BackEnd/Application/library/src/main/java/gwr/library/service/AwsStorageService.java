package gwr.library.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;

/**
 * The Class AwsStorageService.
 */
@Service
public class AwsStorageService {

    /** The bucket name. */
    @Value("${cloud.aws.s3.bucketName}")
    private String bucketName;

    /** The amazon S3. */
    @Autowired
    private AmazonS3 amazonS3;

    /**
     * Upload file.
     *
     * @param multipartFile the multipart file
     * @param filename      the filename
     * @return the string
     * @throws Exception the exception
     */
    public String uploadFile(MultipartFile multipartFile, String filename) throws Exception {
        File file = convertMultipartFileToFile(multipartFile);
        amazonS3.putObject(new PutObjectRequest(bucketName, filename, file));
        file.delete();
        return filename;
    }

    /**
     * Download file.
     *
     * @param fileName the file name
     * @return the byte[]
     * @throws Exception the exception
     */
    public byte[] downloadFile(String fileName) throws Exception {
        S3Object s3Object = amazonS3.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] file = IOUtils.toByteArray(inputStream);
            return file;
        } catch (Exception e) {
            // maybe logger here
            throw e;
        }
    }

    /**
     * Public delete file.
     *
     * @param fileName the file name
     * @return the string
     */
    public String publicDeleteFile(String fileName) {
        amazonS3.deleteObject(bucketName, fileName);
        return fileName;
    }

    /**
     * Get all files from S3 bucket.
     *
     * @return the list
     */
    public List<String> listFiles() {
        ListObjectsV2Request listObjectsRequest = new ListObjectsV2Request().withBucketName(bucketName);
        List<String> keys = new ArrayList<>();
        ListObjectsV2Result result = amazonS3.listObjectsV2(listObjectsRequest);
        for (S3ObjectSummary object : result.getObjectSummaries()) {
            if (object.getSize() < 1) {
                break;
            }
            keys.add(object.getKey());
        }
        return keys;
    }

    /**
     * Get all files from S3 bucket. Not really work!
     * Actually the list of files with a valid starting path name.
     *
     * @return the list
     */
    public List<String> listFilesInPath(String path) {
        ListObjectsV2Request listObjectsRequest = new ListObjectsV2Request().withBucketName(bucketName)
                .withPrefix(path);
        List<String> keys = new ArrayList<>();
        ListObjectsV2Result result = amazonS3.listObjectsV2(listObjectsRequest);
        for (S3ObjectSummary object : result.getObjectSummaries()) {
            if (object.getSize() < 1) {
                break;
            }
            keys.add(object.getKey());
        }
        return keys;
    }

    /**
     * Convert multipart file to file.
     *
     * @param multipartFile the multipart file
     * @return the file
     * @throws Exception the exception
     */
    private File convertMultipartFileToFile(MultipartFile multipartFile) throws Exception {
        File convFile = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(multipartFile.getBytes());
        } catch (Exception e) {
            // maybe logger here
            throw e;
        }
        return convFile;
    }
}
