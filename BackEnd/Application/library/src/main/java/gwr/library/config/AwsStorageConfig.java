package gwr.library.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 * The Class AwsStorageConfig.
 */
@Configuration
public class AwsStorageConfig {
    
    /** The endpoint url. */
//    @Value("${cloud.aws.s3.endpointUrl}")
//    private String endpointUrl;

    /** The access key. */
    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;
    
    /** The secret key. */
    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;
    
    /** The region. */
    @Value("${cloud.aws.region}")
    private String region;
    
    
    /**
     * Gerenate S3 client.
     *
     * @return the amazon S3
     */
    @Bean
    public AmazonS3 gerenateS3client() {
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        return AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(region).build();
    }


}
