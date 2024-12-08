package com.berkson.bank_simulator.web.services;

import com.berkson.bank_simulator.data.domain.Authority;
import com.berkson.bank_simulator.data.repository.AuthorityRepository;
import com.berkson.bank_simulator.web.mappers.AuthorityMapper;
import com.berkson.bank_simulator.web.model.AuthorityDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;
    private final AuthorityMapper authorityMapper;

    public AuthorityServiceImpl(AuthorityRepository authorityRepository, AuthorityMapper authorityMapper) {
        this.authorityRepository = authorityRepository;
        this.authorityMapper = authorityMapper;
    }

    @Override
    public AuthorityDto save(AuthorityDto entity) {
        Authority authority = authorityMapper.authorityDtoToAuthority(entity);
        Authority savedAuthority = authorityRepository.save(authority);
        return authorityMapper.authorityToAuthorityDto(savedAuthority);
    }

    @Override
    public Optional<AuthorityDto> findById(Long id) {
        return authorityRepository.findById(id)
                .map(authorityMapper::authorityToAuthorityDto);
    }

    @Override
    public List<AuthorityDto> findAll() {
        return authorityRepository.findAll()
                .stream().map(authorityMapper::authorityToAuthorityDto)
                .toList();
    }
}
