package com.labs.tenderservice.repository;

import com.labs.tenderservice.entity.ID;

import java.util.List;

public interface IRepository<T> {
    T add(T t);
    T getById(ID id);
    List<T> getAll();
    T update(T t);
    T delete(ID id);
}
