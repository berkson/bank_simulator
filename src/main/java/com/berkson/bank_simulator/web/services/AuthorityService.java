package com.berkson.bank_simulator.web.services;

import com.berkson.bank_simulator.web.model.AuthorityDto;

import java.util.List;

public interface AuthorityService extends CrudService<AuthorityDto, Long> {
    List<AuthorityDto> findAll();
}
