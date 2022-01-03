package gwr.library.enums;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import gwr.library.enums.base.CodeEnum;


@JsonFormat(shape = Shape.OBJECT)
public enum GenderEnum implements CodeEnum {

    MALE("1", "Male", 1), 
    FEMALE("2", "Female", 2), 
    OTHER("3", "Other", 3);

    private GenderEnum(String value, String display, int sortOrder) {
        this.value = value;
        this.display = display;
        this.sortOrder = sortOrder;
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static GenderEnum of(String value) {
        return ObjectUtils.isEmpty(value) ? null
                : Stream.of(GenderEnum.values()).filter(e -> value.equals(e.getValue())).findFirst()
                        .orElseThrow(IllegalArgumentException::new);
    }

    @SuppressWarnings("unchecked")
    @JsonCreator(mode = Mode.DEFAULT)
    public static GenderEnum of(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        if (value instanceof Map) {
            Map<String, String> mapEnum = (Map<String, String>) value;
            return ObjectUtils.isEmpty(value) ? null
                    : Stream.of(GenderEnum.values()).filter(e -> e.getValue().equals(mapEnum.get("value")))
                            .findFirst().orElseThrow(IllegalArgumentException::new);
        }
        return ObjectUtils.isEmpty(value) ? null
                : Stream.of(GenderEnum.values()).filter(e -> value.equals(e.getValue())).findFirst()
                        .orElseThrow(IllegalArgumentException::new);
    }

    /** The value. */
    @JsonProperty("value")
    private final String value;

    /** The display. */
    @JsonProperty("label")
    private final String display;

    /** The sort order. */
    @JsonIgnore
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
