package com.berkson.bank_simulator.web.services.business.operation.strategies;

import com.berkson.bank_simulator.web.exceptions.RequestProblemException;
import com.berkson.bank_simulator.web.model.AccountDto;
import com.berkson.bank_simulator.web.model.OperationDto;
import com.berkson.bank_simulator.web.services.business.AccountServiceImpl;
import com.berkson.bank_simulator.web.services.business.OperationService;
import com.berkson.bank_simulator.web.services.framework.BeanService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreditStrategy implements OperationStrategy {
    private final OperationDto operation;
    private final AccountServiceImpl accountService = BeanService.getBean(AccountServiceImpl.class);
    private final OperationService operationService = BeanService.getBean(OperationService.class);

    public CreditStrategy(OperationDto operation) {
        this.operation = operation;
    }

    @Override
    public void execute() throws RequestProblemException {
        try {
            AccountDto account = accountService.findById(operation.getAccount().getId()).orElseThrow();
            account.setBalance(account.getBalance().add(operation.getValue()));
            accountService.save(account);
            operationService.save(operation);
        } catch (Exception e) {
            log.error("Erro ao realizar crédito. Erro {}", e.getMessage(), e);
            throw new RequestProblemException("Não foi possível realizar o crédito", e);
        }
    }
}
