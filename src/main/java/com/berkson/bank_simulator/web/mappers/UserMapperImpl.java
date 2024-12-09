package com.berkson.bank_simulator.web.mappers;

import com.berkson.bank_simulator.data.domain.Account;
import com.berkson.bank_simulator.data.domain.Authority;
import com.berkson.bank_simulator.data.domain.User;
import com.berkson.bank_simulator.web.model.AccountDto;
import com.berkson.bank_simulator.web.model.AuthorityDto;
import com.berkson.bank_simulator.web.model.UserDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By : Berkson Ximenes
 * Date : 09/12/2024
 **/

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDtoToUser(UserDto userDto) {
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
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setAccount(accountDtoToAccount(userDto.getAccount()));
        user.setAuthorities(authorityDtoListToAuthorityList(userDto.getAuthorities()));

        return user;
    }

    @Override
    public UserDto userToUserDto(User user) {
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
        userDto.setAccount(accountToAccountDto(user.getAccount()));
        userDto.setAuthorities(authorityListToAuthorityDtoList(user.getRoles()));

        return userDto;
    }

    protected Account accountDtoToAccount(AccountDto accountDto) {
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

        return account;
    }

    protected Authority authorityDtoToAuthority(AuthorityDto authorityDto) {
        if (authorityDto == null) {
            return null;
        }

        Authority authority = new Authority();

        authority.setId(authorityDto.getId());
        authority.setAuthority(authorityDto.getAuthority());
        authority.setDescription(authorityDto.getDescription());

        return authority;
    }

    protected List<Authority> authorityDtoListToAuthorityList(List<AuthorityDto> list) {
        if (list == null) {
            return null;
        }

        List<Authority> list1 = new ArrayList<Authority>(list.size());
        for (AuthorityDto authorityDto : list) {
            list1.add(authorityDtoToAuthority(authorityDto));
        }

        return list1;
    }

    protected AccountDto accountToAccountDto(Account account) {
        if (account == null) {
            return null;
        }

        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setCreatedAt(account.getCreatedAt());
        accountDto.setModifiedAt(account.getModifiedAt());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setBalance(account.getBalance());

        return accountDto;
    }

    protected AuthorityDto AuthorityToAuthorityDto(Authority authority) {
        if (authority == null) {
            return null;
        }

        AuthorityDto authorityDto = new AuthorityDto();

        authorityDto.setId(authority.getId());
        authorityDto.setAuthority(authority.getAuthority());
        authorityDto.setDescription(authority.getDescription());

        return authorityDto;
    }

    protected List<AuthorityDto> authorityListToAuthorityDtoList(List<Authority> collection) {
        if (collection == null) {
            return null;
        }

        List<AuthorityDto> list = new ArrayList<AuthorityDto>(collection.size());
        for (Authority authority : collection) {
            list.add(AuthorityToAuthorityDto(authority));
        }

        return list;
    }
}

