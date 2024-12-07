package com.berkson.bank_simulator.web.model;

import com.berkson.bank_simulator.data.domain.Account;
import com.berkson.bank_simulator.data.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created By : Berkson Ximenes
 * Date : 06/12/2024
 **/
@Getter
@Setter
@NoArgsConstructor
@JsonPropertyOrder(value = {"account", "operationType", "value"})
public class OperationDto extends AuditableEntityDto {

    @JsonProperty(value = "tipoDaOperacao")
    private OperationType operationType;
    @JsonProperty(value = "valor")
    @NotNull
    private BigDecimal value;
    @JsonProperty(value = "conta")
    private AccountDto account;


}
