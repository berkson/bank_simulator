package com.berkson.bank_simulator.web.services.business.operation.strategies;

import com.berkson.bank_simulator.web.exceptions.RequestProblemException;
import com.berkson.bank_simulator.web.model.AccountDto;

public interface OperationStrategy {
    void execute() throws RequestProblemException;
}
