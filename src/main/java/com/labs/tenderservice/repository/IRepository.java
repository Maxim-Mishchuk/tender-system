package com.labs.tenderservice.repository;

import java.util.List;

public interface IRepository<T> {
    T create(T t);
    T read(long id);
    T update(T t);
    void delete(long id);
    List<T> getAll();

}
