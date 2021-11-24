package gwr.library.enums;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import gwr.library.enums.base.CodeEnum;

/**
 * The Enum EnumDemo.
 */
@JsonFormat(shape = Shape.OBJECT)
public enum EnumDemo implements CodeEnum {

	/** The value1. */
	VALUE1("1", "value1", 1), /** The value2. */
 VALUE2("2", "value2", 2), /** The value3. */
 VALUE3("3", "value2", 3), /** The value4. */
 VALUE4("4", "value4", 4);

	/**
	 * Instantiates a new enum demo.
	 *
	 * @param value the value
	 * @param display the display
	 * @param sortOrder the sort order
	 */
	private EnumDemo(String value, String display, int sortOrder) {
		this.value = value;
		this.display = display;
		this.sortOrder = sortOrder;
	}
	
	/**
	 * Gets the.
	 *
	 * @param value the value
	 * @return the enum demo
	 */
	@JsonCreator(mode = Mode.DEFAULT)
	public static EnumDemo of(String value) {
		return ObjectUtils.isEmpty(value)? null: Stream.of(EnumDemo.values()).filter(e->value.equals(e.getValue())).findFirst().orElse(null);
	}
	
	/**
	 * Gets the.
	 *
	 * @param value the value
	 * @return the enum demo
	 */
	@JsonCreator(mode = Mode.DELEGATING)
	public static EnumDemo of(Object value) {
		if(ObjectUtils.isEmpty(value)) {
			return null;
		}
		if(value instanceof Map) {
			Map<String, String> mapEnum = (Map<String, String>)value;
			return ObjectUtils.isEmpty(value)? null: Stream.of(EnumDemo.values()).filter(e->e.getValue().equals(mapEnum.get("value"))).findFirst().orElseThrow(IllegalArgumentException::new);
		}
		return ObjectUtils.isEmpty(value)? null: Stream.of(EnumDemo.values()).filter(e->value.equals(e.getValue())).findFirst().orElseThrow(IllegalArgumentException::new);
	}

	/** The value. */
	private final String value;
	
	/** The display. */
	private final String display;
	
	/** The sort order. */
	private final int sortOrder;

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@Override
	public String getValue() {
		return this.value;
	}

	/**
	 * Gets the display.
	 *
	 * @return the display
	 */
	@Override
	public String getDisplay() {
		return this.display;
	}

	/**
	 * Gets the sort order.
	 *
	 * @return the sort order
	 */
	@Override
	public int getSortOrder() {
		return this.sortOrder;
	}
}
