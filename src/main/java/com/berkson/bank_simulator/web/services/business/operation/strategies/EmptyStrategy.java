package com.berkson.bank_simulator.web.services.business.operation.strategies;

import com.berkson.bank_simulator.web.exceptions.RequestProblemException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmptyStrategy implements OperationStrategy {
    @Override
    public void execute() throws RequestProblemException {
        log.error("Estratégia vazia/inexistente foi chamada!");
    }
}
