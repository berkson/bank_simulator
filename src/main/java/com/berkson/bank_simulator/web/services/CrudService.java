package com.berkson.bank_simulator.web.services;


import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CrudService<T, ID> {
    T save(T entity);

    @Transactional
    Optional<T> findById(ID id);
}
