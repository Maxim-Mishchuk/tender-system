package com.labs.tenderservice.repository.impl.ram;

import com.labs.tenderservice.repository.IRepository;
import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.IDEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class RAMRepository<T extends IDEntity> implements IRepository<T> {
    protected Map<ID, T> repository = new HashMap<>();

    @Override
    public T add(T t) {
        if (repository.containsKey(t.getId())) {
            return null;
        }
        repository.put(t.getId(), t);
        return t;
    }

    @Override
    public T getById(ID id) {
        return repository.get(id);
    }

    @Override
    public List<T> getAll() {
        return repository.values().stream().toList();
    }

    public T update(T t) {
        if (repository.containsKey(t.getId())) {
            repository.put(t.getId(), t);
            return t;
        }
        return null;
    }

    @Override
    public T delete(ID id) {
        return repository.remove(id);
    }
}
