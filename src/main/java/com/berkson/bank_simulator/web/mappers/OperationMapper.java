package com.berkson.bank_simulator.web.mappers;


import com.berkson.bank_simulator.data.domain.Operation;
import com.berkson.bank_simulator.web.model.OperationDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OperationMapper {
    OperationMapper INSTANCE = Mappers.getMapper(OperationMapper.class);

    Operation operationDtoToOperation(OperationDto operationDto);

    OperationDto operationToOperationDto(Operation operation);

}
