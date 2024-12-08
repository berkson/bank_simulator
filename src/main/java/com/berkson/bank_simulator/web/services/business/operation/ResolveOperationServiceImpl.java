package com.berkson.bank_simulator.web.services.business.operation;

import com.berkson.bank_simulator.web.exceptions.RequestProblemException;
import com.berkson.bank_simulator.web.model.OperationDto;
import com.berkson.bank_simulator.web.services.business.operation.strategies.CreditStrategy;
import com.berkson.bank_simulator.web.services.business.operation.strategies.DebitStrategy;
import com.berkson.bank_simulator.web.services.business.operation.strategies.EmptyStrategy;
import com.berkson.bank_simulator.web.services.business.operation.strategies.OperationStrategy;
import org.springframework.stereotype.Service;

@Service
public class ResolveOperationServiceImpl implements ResolveOperationService {
    private final OperationContext context;

    public ResolveOperationServiceImpl(OperationContext context) {
        this.context = context;
    }

    @Override
    public void resolve(OperationDto operation) throws RequestProblemException {
        context.setOperationStrategy(resolveStrategy(operation));

        try {
            context.execute();
        } catch (RequestProblemException e) {
            throw new RequestProblemException(e);
        }
    }

    // Várias opções de implementação. Escolhi switch por simplicidade.
    private OperationStrategy resolveStrategy(OperationDto operation) {
        switch (operation.getOperationType()) {
            case DEBIT -> new DebitStrategy(operation);
            case CREDIT -> new CreditStrategy(operation);
        }
        return new EmptyStrategy();
    }
}
