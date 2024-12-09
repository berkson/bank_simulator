package com.berkson.bank_simulator.data.repository;

import com.berkson.bank_simulator.data.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created By : Berkson Ximenes
 * Date : 07/12/2024
 **/

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(int number);
}
