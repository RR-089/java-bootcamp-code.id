package com.codeid.be_eshopay.constant.converter;

import com.codeid.be_eshopay.constant.FintechType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class FintechTypeConverter implements AttributeConverter<FintechType, String> {

    @Override
    public String convertToDatabaseColumn(FintechType attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public FintechType convertToEntityAttribute(String dbData) {
        return dbData != null ? FintechType.fromString(dbData) : null;
    }
}
