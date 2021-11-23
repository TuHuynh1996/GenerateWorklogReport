package gwr.library.enums.base;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;

import org.springframework.util.ObjectUtils;

public abstract class EnumConverter<E extends CodeEnum> implements AttributeConverter<E, String> {

    private Class<E> enumClass;
    
    public EnumConverter(Class<E> enumClass) {
    	this.enumClass = enumClass;
    }

    @Override
    public String convertToDatabaseColumn(E e) {
        return ObjectUtils.isEmpty(e)? null : e.getValue();
    }

    @Override
    public E convertToEntityAttribute(String s) {
    	return ObjectUtils.isEmpty(s)? null: Stream.of(enumClass.getEnumConstants()).filter(e->s.equals(e.getValue())).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
