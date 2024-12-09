package com.berkson.bank_simulator.web.api.v1;

import com.berkson.bank_simulator.web.annotation.security.IsUser;
import com.berkson.bank_simulator.web.model.OperationDto;
import com.berkson.bank_simulator.web.services.business.OperationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created By : Berkson Ximenes
 * Date : 09/12/2024
 **/

@RequestMapping(value = OperationController.ROOT)
@RestController
@Validated
public class OperationController {
    public final static String ROOT = "/api/v1/operation";
    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/account/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @IsUser
    public Page<OperationDto> getByAccount(@PathVariable Long id,
                                           @RequestParam(value = "pag", defaultValue = "0") int pag,
                                           @RequestParam(value = "pgsize", defaultValue = "5") int pgsize,
                                           @RequestParam(value = "ord", defaultValue = "id") String ord,
                                           @RequestParam(value = "dir", defaultValue = "DESC") String dir) {
        PageRequest pageRequest = PageRequest.of(pag, pgsize, Sort.Direction.valueOf(dir.toUpperCase()), ord);
        return operationService.findAllByAccount(id, pageRequest);
    }
}
