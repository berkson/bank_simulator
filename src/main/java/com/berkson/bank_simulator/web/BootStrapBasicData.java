package com.berkson.bank_simulator.web;

import com.berkson.bank_simulator.data.domain.Authority;
import com.berkson.bank_simulator.data.repository.AuthorityRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BootStrapBasicData implements ApplicationRunner {
    private final AuthorityRepository authorityRepository;

    public BootStrapBasicData(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (authorityRepository.count() == 0) {
            this.chargeAuths();
        }
    }

    private void chargeAuths() {
        Authority admin = new Authority("ROLE_ADMIN", "Administrador");
        Authority user = new Authority("ROLE_USER", "Usuário");

        authorityRepository.saveAllAndFlush(List.of(admin, user));
    }


}
