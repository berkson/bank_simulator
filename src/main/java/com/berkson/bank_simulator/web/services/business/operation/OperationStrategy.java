package com.berkson.bank_simulator.web.services.business.operation;

import com.berkson.bank_simulator.web.exceptions.RequestProblemException;

public interface OperationStrategy {
    void execute() throws RequestProblemException;
}
