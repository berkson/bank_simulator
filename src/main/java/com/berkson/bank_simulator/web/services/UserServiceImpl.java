package com.berkson.bank_simulator.web.services;

import com.berkson.bank_simulator.data.domain.Account;
import com.berkson.bank_simulator.data.domain.User;
import com.berkson.bank_simulator.data.repository.AccountRepository;
import com.berkson.bank_simulator.data.repository.UserRepository;
import com.berkson.bank_simulator.web.mappers.UserMapper;
import com.berkson.bank_simulator.web.model.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AccountRepository accountRepository;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.accountRepository = accountRepository;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserDto save(UserDto entity) {
        User user = userMapper.userDtoToUser(entity);
        if (user.getId() != null && userRepository.existsById(user.getId())) {
            return getUser(user);
        }
        Account account = user.getAccount();
        Account savedAccount = accountRepository.save(account);
        user.setAccount(savedAccount);
        return getUser(user);
    }

    private UserDto getUser(User user) {
        User savedUser;
        savedUser = userRepository.save(user);
        return userMapper.userToUserDto(savedUser);
    }


    @Override
    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id).map(userMapper::userToUserDto);
    }
}
