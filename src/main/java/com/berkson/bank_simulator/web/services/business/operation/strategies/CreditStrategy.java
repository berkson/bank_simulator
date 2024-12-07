package com.berkson.bank_simulator.web.services.business.operation.strategies;

import com.berkson.bank_simulator.data.domain.Operation;
import com.berkson.bank_simulator.web.exceptions.RequestProblemException;
import com.berkson.bank_simulator.web.services.business.AccountServiceImpl;
import com.berkson.bank_simulator.web.services.business.OperationService;
import com.berkson.bank_simulator.web.services.business.operation.OperationStrategy;
import com.berkson.bank_simulator.web.services.framework.BeanService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreditStrategy implements OperationStrategy {
    private Operation operation;
    private final AccountServiceImpl accountService = BeanService.getBean(AccountServiceImpl.class);
    private final OperationService operationService = BeanService.getBean(OperationService.class);

    @Override
    public void execute() throws RequestProblemException {

    }
}
