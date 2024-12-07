package com.berkson.bank_simulator.data.domain;

import com.berkson.bank_simulator.data.enums.OperationType;
import jakarta.persistence.*;
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
@Entity
@Table(name = "operations")
@AttributeOverride(name = "id", column = @Column(name = "operation_id"))
public class Operation extends AuditableEntity {

    @Column(name = "operation_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Column(nullable = false)
    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
