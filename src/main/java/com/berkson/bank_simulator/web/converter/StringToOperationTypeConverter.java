package com.berkson.bank_simulator.web.converter;

import com.berkson.bank_simulator.data.enums.OperationType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created By : Berkson Ximenes
 * Date : 07/12/2024
 **/

@Component
public class StringToOperationTypeConverter implements Converter<String, OperationType> {
    @Override
    public OperationType convert(String source) {
        return OperationType.valueOf(source.toUpperCase());
    }
}
