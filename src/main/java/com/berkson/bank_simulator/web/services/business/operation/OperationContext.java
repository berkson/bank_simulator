package com.berkson.bank_simulator.web.services.business.operation;

import com.berkson.bank_simulator.web.exceptions.RequestProblemException;
import com.berkson.bank_simulator.web.services.business.operation.strategies.OperationStrategy;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Setter
public class OperationContext {
    private OperationStrategy operationStrategy;

    public void execute() throws RequestProblemException {
        operationStrategy.execute();
    }
}
