package com.codeid.be_eshopay.service;

import java.util.List;

public interface BaseCrudService<T, ID> {
    List<T> findAll();

    T findById(ID id);

    T create(T entity);

    T update(ID id, T entity);

    void deleteById(ID id);

}
