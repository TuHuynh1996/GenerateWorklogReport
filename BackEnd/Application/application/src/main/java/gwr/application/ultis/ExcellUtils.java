package gwr.application.ultis;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class ExcellUtils<T> {
	final Class<T> typeParameterClass;

	public ExcellUtils(Class<T> typeParameterClass) {
		this.typeParameterClass = typeParameterClass;
	}

	public List<?> mapData(MultipartFile file, int headerLineNumber, int sheetNumber) throws Exception {
//			MapperFacade mapper = mapperFactory.getMapperFacade();
////			UserDTO userDTO = new UserDTO();
////			userDTO.setName("xyz");
//			
//			Users user = mapper.map(userDTO, Users.class);

//			BeanUtils.copyProperties(content, searchContent);	
		Field[] fields = typeParameterClass.getDeclaredFields();

//		    Field field = getClass().getDeclaredField(fieldName);
//		    field.setInt(this, value);

		try {
			Object ob = typeParameterClass.newInstance();
			for (Field field : fields) {
				field.setAccessible(true);
				field.set(ob, "1");
			}
			List<Object> objList = new ArrayList<Object>();
			objList.add(ob);
			return objList;
//				XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
//				XSSFSheet worksheet = workbook.getSheetAt(sheetNumber);

//				for (int i = headerLineNumber + 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
			//
//				}

//				return null;
		} catch (Exception e) {
			throw e;
		}

	}

}
