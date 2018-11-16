package com.akandouch.invoicec.service;

import com.akandouch.invoicec.domain.DomainEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CrudService<T extends DomainEntity> {
    T save(T t);
    T findOne(String id);
    List<T> findAll();
    Page<T> findAllByPage(Integer pageSize, Integer pageNumber, String orderColumn, String direction);
    void delete(String id);
}
