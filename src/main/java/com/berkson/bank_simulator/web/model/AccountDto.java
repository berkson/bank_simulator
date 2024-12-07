package com.berkson.bank_simulator.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created By : Berkson Ximenes
 * Date : 06/12/2024
 **/

@Getter
@Setter
@NoArgsConstructor
public class AccountDto extends AuditableEntityDto {
    @JsonProperty(value = "numeroDaConta")
    @NotNull
    @Min(value = 1, message = "{min.account.number.value}")
    @Max(value = 2147483647, message = "{max.account.number.value}")
    private Integer accountNumber;
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;
    @OneToOne(mappedBy = "account")
    private UserDto user;
    @OneToMany(mappedBy = "account")
    private List<OperationDto> operations = new ArrayList<>();

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), accountNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof AccountDto account))
            return false;

        return Objects.equals(this.getId(), account.getId())
                && Objects.equals(this.accountNumber, account.accountNumber);
    }
}
