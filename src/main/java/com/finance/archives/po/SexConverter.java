package com.finance.archives.po;

import javax.persistence.AttributeConverter;

public class SexConverter implements AttributeConverter<SexEmum,Integer> {
    @Override
    public Integer convertToDatabaseColumn(SexEmum sexEmum) {
        return sexEmum.getId();
    }

    @Override
    public SexEmum convertToEntityAttribute(Integer id) {
        return SexEmum.getEnmuById(id);
    }
}
