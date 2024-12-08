package com.berkson.bank_simulator.web.api.v1;

import com.berkson.bank_simulator.web.model.AuthorityDto;
import com.berkson.bank_simulator.web.services.AuthorityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = AuthorityController.ROOT)
@RestController
@Validated
public class AuthorityController {
    public static final String ROOT = "/api/v1/auth";
    private final AuthorityService authorityService;

    public AuthorityController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AuthorityDto> getAll() {
        return authorityService.findAll();
    }
}
