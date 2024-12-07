package com.berkson.bank_simulator.web.mappers;

import com.berkson.bank_simulator.data.domain.Account;
import com.berkson.bank_simulator.web.model.AccountDto;
import org.mapstruct.factory.Mappers;

public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);


    Account accountDtoToAccount(AccountDto accountDto);


    AccountDto accountToAccountDto(Account account);

}
