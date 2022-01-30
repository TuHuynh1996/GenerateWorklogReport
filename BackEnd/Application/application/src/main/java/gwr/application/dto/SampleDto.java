package gwr.application.dto;

import gwr.library.util.annotation.ExcelHeader;
import lombok.Data;

/**
 * Instantiates a new sample dto.
 */
@Data
public class SampleDto {
	
	/** The sample 1. */
    @ExcelHeader(value = "sample1")
	private String sample1a;
	
	/** The sample 2. */
    @ExcelHeader(value = "sample2")
	private String sample2a;
	
	/** The sample 3. */
    @ExcelHeader(value = "sample3")
	private String sample3a;
	
	/** The sample 4. */
    @ExcelHeader(value = "sample4")
	private String sample4a;
	
}
