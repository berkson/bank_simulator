package com.berkson.bank_simulator.web.mappers;

import com.berkson.bank_simulator.data.domain.Account;
import com.berkson.bank_simulator.data.domain.Operation;
import com.berkson.bank_simulator.data.domain.User;
import com.berkson.bank_simulator.web.model.AccountDto;
import com.berkson.bank_simulator.web.model.OperationDto;
import com.berkson.bank_simulator.web.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account accountDtoToAccount(AccountDto accountDto) {
        if (accountDto == null) {
            return null;
        }

        Account account = new Account();

        account.setId(accountDto.getId());
        account.setCreatedAt(accountDto.getCreatedAt());
        account.setModifiedAt(accountDto.getModifiedAt());
        if (accountDto.getAccountNumber() != null) {
            account.setAccountNumber(accountDto.getAccountNumber());
        }
        account.setBalance(accountDto.getBalance());
        account.setUser(userDtoToUser(accountDto.getUser()));
        account.setOperations(operationDtoListToOperationList(accountDto.getOperations()));

        return account;
    }

    @Override
    public AccountDto accountToAccountDto(Account account) {
        if (account == null) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setCreatedAt(account.getCreatedAt());
        accountDto.setModifiedAt(account.getModifiedAt());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setBalance(account.getBalance());
        accountDto.setUser(userToUserDto(account.getUser()));
        accountDto.setOperations(operationListToOperationDtoList(account.getOperations()));

        return accountDto;
    }

    protected User userDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();

        user.setId(userDto.getId());
        user.setCreatedAt(userDto.getCreatedAt());
        user.setModifiedAt(userDto.getModifiedAt());
        user.setUsername(userDto.getUsername());
        user.setAge(userDto.getAge());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        return user;
    }

    protected Operation operationDtoToOperation(OperationDto operationDto) {
        if (operationDto == null) {
            return null;
        }

        Operation operation = new Operation();

        operation.setId(operationDto.getId());
        operation.setCreatedAt(operationDto.getCreatedAt());
        operation.setModifiedAt(operationDto.getModifiedAt());
        operation.setOperationType(operationDto.getOperationType());
        operation.setValue(operationDto.getValue());

        return operation;
    }

    protected List<Operation> operationDtoListToOperationList(List<OperationDto> list) {
        if (list == null) {
            return null;
        }

        List<Operation> list1 = new ArrayList<Operation>(list.size());
        for (OperationDto operationDto : list) {
            list1.add(operationDtoToOperation(operationDto));
        }

        return list1;
    }

    protected UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setModifiedAt(user.getModifiedAt());
        userDto.setUsername(user.getUsername());
        userDto.setAge(user.getAge());
        userDto.setName(user.getName());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    protected OperationDto operationToOperationDto(Operation operation) {
        if (operation == null) {
            return null;
        }

        OperationDto operationDto = new OperationDto();

        operationDto.setId(operation.getId());
        operationDto.setCreatedAt(operation.getCreatedAt());
        operationDto.setModifiedAt(operation.getModifiedAt());
        operationDto.setOperationType(operation.getOperationType());
        operationDto.setValue(operation.getValue());

        return operationDto;
    }

    protected List<OperationDto> operationListToOperationDtoList(List<Operation> list) {
        if (list == null) {
            return null;
        }

        List<OperationDto> list1 = new ArrayList<OperationDto>(list.size());
        for (Operation operation : list) {
            list1.add(operationToOperationDto(operation));
        }

        return list1;
    }
}
