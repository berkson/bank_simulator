package com.berkson.bank_simulator.web.services.business;

import com.berkson.bank_simulator.web.model.AccountDto;
import com.berkson.bank_simulator.web.model.OperationDto;
import com.berkson.bank_simulator.web.services.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OperationService extends CrudService<OperationDto, Long> {
    Page<OperationDto> findAllByAccount(AccountDto accountDto, Pageable pageable);
}
