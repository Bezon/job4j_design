package ru.job4j.set;

import ru.job4j.collection.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        if (this.contains(value)) {
            return false;
        } else {
            set.add(value);
        }
        return true;
    }

    @Override
    public boolean contains(T value) {
        for (T t : set) {
            if (t == null || value.equals(t)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}