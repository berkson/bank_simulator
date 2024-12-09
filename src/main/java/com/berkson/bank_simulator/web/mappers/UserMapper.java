package com.berkson.bank_simulator.web.mappers;

import com.berkson.bank_simulator.data.domain.User;
import com.berkson.bank_simulator.web.model.UserDto;
import org.mapstruct.factory.Mappers;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
