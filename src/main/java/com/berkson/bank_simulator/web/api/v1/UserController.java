package com.berkson.bank_simulator.web.api.v1;

import com.berkson.bank_simulator.web.annotation.security.IsUser;
import com.berkson.bank_simulator.web.model.UserDto;
import com.berkson.bank_simulator.web.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequestMapping(value = UserController.ROOT)
@RestController
@Validated
public class UserController {
    public static final String ROOT = "/api/v1/user";
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    @IsUser
    public UserDto getUser(Principal principal) {
        return this.userService.findByUsername(principal.getName());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto save(@RequestBody UserDto userDto) {
        return userService.save(userDto);
    }
}
