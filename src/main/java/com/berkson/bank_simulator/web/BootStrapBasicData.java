package com.berkson.bank_simulator.web;

import com.berkson.bank_simulator.data.domain.Account;
import com.berkson.bank_simulator.data.domain.Authority;
import com.berkson.bank_simulator.data.domain.User;
import com.berkson.bank_simulator.data.repository.AuthorityRepository;
import com.berkson.bank_simulator.data.repository.UserRepository;
import com.berkson.bank_simulator.web.mappers.UserMapper;
import com.berkson.bank_simulator.web.services.UserService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class BootStrapBasicData implements ApplicationRunner {
    private final AuthorityRepository authorityRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public BootStrapBasicData(AuthorityRepository authorityRepository, UserService userService,
                              UserRepository userRepository, UserMapper userMapper) {
        this.authorityRepository = authorityRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (authorityRepository.count() == 0) {
            this.chargeAuths();
        }
        if (userRepository.count() == 0) {
            this.createAdmin();
        }
    }

    private void chargeAuths() {
        Authority admin = new Authority("ROLE_ADMIN", "Administrador");
        Authority user = new Authority("ROLE_USER", "Usuário");

        authorityRepository.saveAllAndFlush(List.of(admin, user));
    }

    private void createAdmin() {
        User user = new User();
        user.setAccount(new Account(1, BigDecimal.ZERO));
        user.setAge(30);
        user.setName("José Soares");
        user.setEmail("jose@teste.com.br");
        user.setAuthorities(List.of(authorityRepository.findByAuthority("ROLE_ADMIN")));
        user.setPassword("123456789");
        user.setUsername("53036189009");
        userService.save(userMapper.userToUserDto(user));

    }

}
