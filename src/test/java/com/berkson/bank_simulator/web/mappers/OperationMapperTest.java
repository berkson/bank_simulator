package com.berkson.bank_simulator.web.mappers;

import com.berkson.bank_simulator.data.domain.Account;
import com.berkson.bank_simulator.data.domain.Operation;
import com.berkson.bank_simulator.data.enums.OperationType;
import com.berkson.bank_simulator.web.model.AccountDto;
import com.berkson.bank_simulator.web.model.OperationDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class OperationMapperTest {

    OperationMapper operationMapper = OperationMapper.INSTANCE;
    Operation credit;
    OperationDto debit;
    static final long ID2 = 2L;
    static final long ID3 = 3L;
    static final long ID1 = 1L;

    @BeforeEach
    void setUp() {
        Account account = new Account();
        account.setId(ID2);
        account.setAccountNumber(1);
        account.setBalance(BigDecimal.valueOf(2500.35));
        AccountDto accountDto = new AccountDto();
        accountDto.setId(ID3);
        accountDto.setAccountNumber(255);
        accountDto.setBalance(BigDecimal.valueOf(1200.30));
        credit = new Operation();
        credit.setId(ID1);
        credit.setValue(BigDecimal.valueOf(25.10));
        credit.setOperationType(OperationType.CREDIT);
        credit.setAccount(account);
        debit = new OperationDto();
        debit.setId(ID2);
        debit.setValue(BigDecimal.valueOf(200.00));
        debit.setOperationType(OperationType.DEBIT);
        debit.setAccount(accountDto);
    }

    @Test
    void operationDtoToOperation() {
        Operation operation = operationMapper.operationDtoToOperation(debit);
        assertThat(operation.getId(), is(ID2));
        assertThat(operation.getOperationType(), is(OperationType.DEBIT));
        assertThat(operation.getValue(), is(BigDecimal.valueOf(200.00)));
        assertThat(operation.getAccount().getId(), is(ID3));
        assertThat(operation.getAccount().getBalance(), is(BigDecimal.valueOf(1200.30)));
    }

    @Test
    void operationToOperationDto() {
        OperationDto dto = operationMapper.operationToOperationDto(credit);
        assertThat(dto.getId(), is(ID1));
        assertThat(dto.getOperationType(), is(OperationType.CREDIT));
        assertThat(dto.getValue(), is(BigDecimal.valueOf(25.10)));
        assertThat(dto.getAccount().getId(), is(ID2));
        assertThat(dto.getAccount().getBalance(), is(BigDecimal.valueOf(2500.35)));
    }
}