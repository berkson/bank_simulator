package com.berkson.bank_simulator.web.api.v1;

import com.berkson.bank_simulator.web.annotation.security.IsUser;
import com.berkson.bank_simulator.web.model.AccountDto;
import com.berkson.bank_simulator.web.services.business.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = AccountController.ROOT)
@RestController
@Validated
public class AccountController {
    public static final String ROOT = "/api/v1/account";
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @IsUser
    public AccountDto getAccount(@PathVariable Long id) {
        return accountService.findById(id).orElseThrow();
    }
}
