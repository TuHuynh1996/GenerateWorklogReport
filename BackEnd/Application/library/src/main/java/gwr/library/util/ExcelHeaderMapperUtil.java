package gwr.library.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import gwr.library.util.annotation.ExcelHeader;
import lombok.var;

public class ExcelHeaderMapperUtil {


    /** The workbook. */
    private XSSFWorkbook workbook;
    
    private Integer headerRowNumber = 0;
    
    private Integer sheetNumber = 0;

    /**
     * Instantiates a new excell ultis.
     *
     * @param inputStream the input stream
     * @param classType   the class type
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public ExcelHeaderMapperUtil(InputStream inputStream) throws IOException {
        this.workbook = new XSSFWorkbook(inputStream);
    }
    
    public ExcelHeaderMapperUtil(InputStream inputStream, int headerRowNumber, int sheetNumber) throws IOException {
        this.workbook = new XSSFWorkbook(inputStream);
        this.headerRowNumber = headerRowNumber;
        this.sheetNumber = sheetNumber;
    }

    /**
     * Gets the all items to object.
     *
     * @param <T>             the generic type
     * @param headerRowNumber the header row number
     * @param sheetNumber     the sheet number
     * @return the all items to object
     * @throws Exception the exception
     */
    public <T> List<T>  map(Class<T> classType) throws Exception {

        // get sheet work place
        XSSFSheet worksheet = workbook.getSheetAt(sheetNumber);

        // get List header items
        Map<Integer, String> headerItems = getItemsByRow(worksheet, headerRowNumber);

        // get all attributes object need to set
        Field[] fields = classType.getDeclaredFields();
        Map<Integer, Field> attibutesSetMap = new HashMap<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExcelHeader.class)) {
                var excelHeaderName = field.getAnnotation(ExcelHeader.class);
                for (Map.Entry<Integer, String> entry : headerItems.entrySet()) {
                    if (excelHeaderName.value().equals(entry.getValue())) {
                        field.setAccessible(true);
                        attibutesSetMap.put(entry.getKey(), field);
                        break;
                    }
                }
            }
            for (Map.Entry<Integer, String> entry : headerItems.entrySet()) {
                if (field.getName().toLowerCase().equals(entry.getValue().replaceAll("[^\\w]", "").toLowerCase())) {
                    field.setAccessible(true);
                    attibutesSetMap.put(entry.getKey(), field);
                    break;
                }
            }
        }

        // get all items row to object list
        List<T> objList = new ArrayList<T>();
        int rowCount = worksheet.getPhysicalNumberOfRows();
        for (int i = headerRowNumber + 1; i < rowCount; i++) {
            T obj = classType.getDeclaredConstructor().newInstance();
            Map<Integer, String> items = getItemsByRow(worksheet, i);
            for (Map.Entry<Integer, Field> attibutes : attibutesSetMap.entrySet()) {
                attibutes.getValue().set(obj, items.get(attibutes.getKey()));
            }
            objList.add(obj);
        }

        return objList;
    }

    /**
     * Gets the items by row.
     *
     * @param worksheet the work sheet
     * @param rowNumber the row number
     * @return the items by row
     */
    private Map<Integer, String> getItemsByRow(XSSFSheet worksheet, int rowNumber) {
        Map<Integer, String> mapItem = new HashMap<>();
        XSSFRow row = worksheet.getRow(rowNumber);
        if (row == null) {
            return null;
        }
        int colCount = row.getPhysicalNumberOfCells();
        for (int i = 0; i < colCount; i++) {
            XSSFCell cell = row.getCell(i);
            var cellTypeCode = cell.getCellType();
            switch (cellTypeCode) {
            case NUMERIC:
                mapItem.put(i, String.valueOf(row.getCell(i).getNumericCellValue()));
                break;
            case STRING:
                mapItem.put(i, row.getCell(i).getStringCellValue());
                break;
            case FORMULA:
                mapItem.put(i, row.getCell(i).getStringCellValue());
                break;
            case BOOLEAN:
                mapItem.put(i, String.valueOf(row.getCell(i).getBooleanCellValue()));
                break;
            default:
                mapItem.put(i, "");
                break;
            }
        }
        return mapItem;
    }
}

