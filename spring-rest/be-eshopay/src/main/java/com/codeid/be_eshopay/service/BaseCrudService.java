package com.codeid.be_eshopay.service;

import com.codeid.be_eshopay.model.dto.PaginationDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseCrudService<T, ID> {
    PaginationDTO<List<T>> findAll(Pageable pageable);

    //List<OptionsDTO<T>> findAllOptions();

    T findById(ID id);

    T create(T entity);

    T update(ID id, T entity);

    void deleteById(ID id);

}
