package com.berkson.bank_simulator.web.mappers;

import com.berkson.bank_simulator.data.domain.User;
import com.berkson.bank_simulator.web.model.UserDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "account.user", ignore = true)
    @Mapping(target = "account.operations", ignore = true)
    User userDtoToUser(UserDto userDto);

    @Mapping(target = "account.user", ignore = true)
    @Mapping(target = "account.operations", ignore = true)
    UserDto userToUserDto(User user);

}
