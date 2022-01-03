package gwr.library.enums;

import java.util.Map;
import java.util.stream.Stream;

import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import gwr.library.enums.base.CodeEnum;

@JsonFormat(shape = Shape.OBJECT)
public enum ApprovalStatusEnum implements CodeEnum {

    APPROVED("1", "Approved", 1), 
    CANCEL("2", "Cancel", 2), 
    PENDING("3", "Pending", 3), 
    WAITING("4", "Waiting", 4);

    private ApprovalStatusEnum(String value, String display, int sortOrder) {
        this.value = value;
        this.display = display;
        this.sortOrder = sortOrder;
    }

    @JsonCreator(mode = Mode.DELEGATING)
    public static ApprovalStatusEnum of(String value) {
        return ObjectUtils.isEmpty(value) ? null
                : Stream.of(ApprovalStatusEnum.values()).filter(e -> value.equals(e.getValue())).findFirst()
                        .orElse(null);
    }

    @SuppressWarnings("unchecked")
    @JsonCreator(mode = Mode.DEFAULT)
    public static ApprovalStatusEnum of(Object value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        if (value instanceof Map) {
            Map<String, String> mapEnum = (Map<String, String>) value;
            return ObjectUtils.isEmpty(value) ? null
                    : Stream.of(ApprovalStatusEnum.values()).filter(e -> e.getValue().equals(mapEnum.get("value")))
                            .findFirst().orElseThrow(IllegalArgumentException::new);
        }
        return ObjectUtils.isEmpty(value) ? null
                : Stream.of(ApprovalStatusEnum.values()).filter(e -> value.equals(e.getValue())).findFirst()
                        .orElseThrow(IllegalArgumentException::new);
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
