package com.berkson.bank_simulator.web.mappers;

import com.berkson.bank_simulator.data.domain.Account;
import com.berkson.bank_simulator.web.model.AccountDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class AccountMapperTest {

    static final long ID2 = 2L;
    static final long ID3 = 3L;
    AccountMapper accountMapper = AccountMapper.INSTANCE;
    Account account;
    AccountDto accountDto;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setId(ID2);
        account.setAccountNumber(1);
        account.setBalance(BigDecimal.valueOf(2500.35));
        accountDto = new AccountDto();
        accountDto.setId(ID3);
        accountDto.setAccountNumber(255);
        accountDto.setBalance(BigDecimal.valueOf(1200.30));
    }

    @Test
    void accountDtoToAccount() {
        Account account = accountMapper.accountDtoToAccount(accountDto);
        assertThat(account.getId(), is(ID3));
        assertThat(account.getAccountNumber(), is(255));
        assertThat(account.getBalance(), is(BigDecimal.valueOf(1200.30)));
    }

    @Test
    void accountToAccountDto() {
        AccountDto dto = accountMapper.accountToAccountDto(account);
        assertThat(dto.getId(), is(ID2));
        assertThat(dto.getAccountNumber(), is(1));
        assertThat(dto.getBalance(), is(BigDecimal.valueOf(2500.35)));
    }
}