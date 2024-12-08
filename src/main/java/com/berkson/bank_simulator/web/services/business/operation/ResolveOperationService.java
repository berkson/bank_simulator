package com.berkson.bank_simulator.web.services.business.operation;

import com.berkson.bank_simulator.web.exceptions.RequestProblemException;
import com.berkson.bank_simulator.web.model.OperationDto;

public interface ResolveOperationService {
    void resolve(OperationDto operation) throws RequestProblemException;
}
