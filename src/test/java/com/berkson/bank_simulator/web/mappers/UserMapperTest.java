package com.berkson.bank_simulator.web.mappers;

import com.berkson.bank_simulator.data.domain.Account;
import com.berkson.bank_simulator.data.domain.User;
import com.berkson.bank_simulator.web.model.AccountDto;
import com.berkson.bank_simulator.web.model.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class UserMapperTest {

    User user;
    UserDto userDto;
    UserMapper userMapper = UserMapper.INSTANCE;
    static final long ID1 = 1L;
    static final long ID2 = 2L;
    static final long ID3 = 3L;

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
        user = new User();
        account.setUser(user);
        user.setId(ID1);
        user.setName("José Ailton");
        user.setUsername("16685999020");
        user.setEmail("ailton@teste.com.br");
        user.setPassword("teste");
        user.setAge(32);
        user.setAccount(account);
        userDto = new UserDto();
        account.setUser(user);
        userDto.setId(ID2);
        userDto.setName("Darcio");
        userDto.setUsername("78008215011");
        userDto.setEmail("darciolo@teste.com.br");
        userDto.setPassword("12345");
        userDto.setAge(60);
        userDto.setAccount(accountDto);
    }

    @Test
    void userDtoToUser() {
        User u = userMapper.userDtoToUser(userDto);
        assertThat(u.getId(), is(ID2));
        assertThat(u.getAge(), is(60));
        assertThat(u.getEmail(), equalTo("darciolo@teste.com.br"));
        assertThat(u.getName(), containsStringIgnoringCase("Darcio"));
        assertThat(u.getAccount().getId(), is(ID3));
        assertThat(u.getAccount().getAccountNumber(), is(255));
        assertThat(u.getAccount().getBalance(), is(BigDecimal.valueOf(1200.30)));
    }

    @Test
    void userToUserDto() {
        UserDto dto = userMapper.userToUserDto(user);
        assertThat(dto.getId(), is(ID1));
        assertThat(dto.getAge(), is(32));
        assertThat(dto.getName(), containsStringIgnoringCase("Ailton"));
        assertThat(dto.getAccount().getId(), is(ID2));
        assertThat(dto.getAccount().getAccountNumber(), is(1));
        assertThat(dto.getAccount().getBalance(), is(BigDecimal.valueOf(2500.35)));
    }
}