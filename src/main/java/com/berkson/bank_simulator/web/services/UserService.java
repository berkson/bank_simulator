package com.berkson.bank_simulator.web.services;


import com.berkson.bank_simulator.web.model.UserDto;

public interface UserService extends CrudService<UserDto, Long> {
    UserDto findByUsername(String name);
}
