package com.berkson.bank_simulator.web.services.business;

import com.berkson.bank_simulator.data.domain.Account;
import com.berkson.bank_simulator.data.repository.AccountRepository;
import com.berkson.bank_simulator.web.mappers.AccountMapper;
import com.berkson.bank_simulator.web.model.AccountDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountServiceImpl(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDto save(AccountDto entity) {
        Account account = accountMapper.accountDtoToAccount(entity);
        Account savedAccount = accountRepository.save(account);
        return accountMapper.accountToAccountDto(savedAccount);
    }

    @Override
    public Optional<AccountDto> findById(Long id) {
        return Optional.of(accountRepository
                .findById(id).map(accountMapper::accountToAccountDto)
                .orElseThrow());
    }
}
