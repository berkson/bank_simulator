package com.berkson.bank_simulator.domain;

import jakarta.persistence.*;
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
@Entity
@Table(name = "account")
@AttributeOverride(name = "id", column = @Column(name = "account_id"))
public class Account extends AuditableEntity {
    @Column(name = "account_number", nullable = false, unique = true)
    private int accountNumber;
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;
    @OneToOne(mappedBy = "account")
    private User user;
    @OneToMany(mappedBy = "account")
    private List<Operation> operations = new ArrayList<>();

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), accountNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Account account))
            return false;

        return Objects.equals(this.getId(), account.getId())
                && Objects.equals(this.accountNumber, account.accountNumber);
    }
}
