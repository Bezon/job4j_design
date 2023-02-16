package ru.job4j.generic;

import java.util.HashMap;
import java.util.Map;

public final class MemStore<T extends Base> implements Store<T> {

    private final Map<String, T> storage = new HashMap<>();

    @Override
    public void add(T model) {
        this.storage.putIfAbsent(model.getId(), model);
    }

    @Override
    public boolean replace(String id, T model) {
        return this.storage.replace(id, storage.get(id), model);
    }

    @Override
    public boolean delete(String id) {
        return this.storage.remove(id, storage.get(id));
    }

    @Override
    public T findById(String id) {
        return this.storage.get(id);
    }
}