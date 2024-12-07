package com.berkson.bank_simulator.web.mappers;

import com.berkson.bank_simulator.data.domain.Authority;
import com.berkson.bank_simulator.web.model.AuthorityDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface AuthorityMapper {
    AuthorityMapper INSTANCE = Mappers.getMapper(AuthorityMapper.class);

    Authority authorityDtoToAuthority(AuthorityDto authorityDto);

    AuthorityDto authorityToAuthorityDto(Authority authority);
}
