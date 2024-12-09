package com.berkson.bank_simulator.web.services.business;

import com.berkson.bank_simulator.data.domain.Operation;
import com.berkson.bank_simulator.data.repository.OperationRepository;
import com.berkson.bank_simulator.web.mappers.OperationMapper;
import com.berkson.bank_simulator.web.model.AccountDto;
import com.berkson.bank_simulator.web.model.OperationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationServiceImpl implements OperationService {
    private final OperationRepository operationRepository;
    private final OperationMapper operationMapper;

    public OperationServiceImpl(OperationRepository operationRepository, OperationMapper operationMapper) {
        this.operationRepository = operationRepository;
        this.operationMapper = operationMapper;
    }

    @Override
    public OperationDto save(OperationDto entity) {
        Operation operation = operationMapper.operationDtoToOperation(entity);
        Operation savedOperation = operationRepository.save(operation);
        return operationMapper.operationToOperationDto(savedOperation);
    }

    @Override
    public Optional<OperationDto> findById(Long id) {
        return Optional.of(operationRepository
                .findById(id).map(operationMapper::operationToOperationDto)
                .orElseThrow());
    }

    @Override
    public Page<OperationDto> findAllByAccount(Long id, Pageable pageable) {
        return operationRepository.findAllByAccount_Id(id, pageable)
                .map(operationMapper::operationToOperationDto);
    }
}
