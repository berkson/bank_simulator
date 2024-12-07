package com.berkson.bank_simulator.web.mappers;

import com.berkson.bank_simulator.data.domain.Authority;
import com.berkson.bank_simulator.web.model.AuthorityDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.is;

class AuthorityMapperTest {

    AuthorityMapper authorityMapper = AuthorityMapper.INSTANCE;
    Authority authority;
    AuthorityDto authorityDto;
    static final long ID1 = 1L;
    static final long ID2 = 1L;

    @BeforeEach
    void setUp() {
        authority = new Authority(ID1, "ADMIN", "Administrador");
        authorityDto = new AuthorityDto(ID2, "USER", "Usuário");
    }

    @Test
    void authorityDtoToAuthority() {
        Authority a = authorityMapper.authorityDtoToAuthority(authorityDto);
        assertThat(a.getId(), is(ID2));
        assertThat(a.getAuthority(), is("USER"));
        assertThat(a.getDescription(), containsStringIgnoringCase("usuário"));
    }

    @Test
    void authorityToAuthorityDto() {
        AuthorityDto d = authorityMapper.authorityToAuthorityDto(authority);
        assertThat(d.getId(), is(ID1));
        assertThat(d.getAuthority(), is("ADMIN"));
        assertThat(d.getDescription(), containsStringIgnoringCase("admin"));
    }
}