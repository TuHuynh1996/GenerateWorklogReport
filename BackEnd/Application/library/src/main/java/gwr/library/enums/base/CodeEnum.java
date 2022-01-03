package gwr.library.enums.base;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * The Interface CodeEnum.
 */
public interface CodeEnum {
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
    @JsonValue
	public String getValue();
	
	/**
	 * Gets the display.
	 *
	 * @return the display
	 */
	public String getDisplay();
	
	/**
	 * Gets the sort order.
	 *
	 * @return the sort order
	 */
	public int getSortOrder();
}
