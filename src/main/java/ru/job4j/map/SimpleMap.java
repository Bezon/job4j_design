package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= LOAD_FACTOR * capacity) {
            expand();
        }
        boolean rsl = false;
        int index = index(key);
        if (table[index] == null) {
            rsl = true;
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return (hashCode) ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int index(K key) {
        return indexFor(hash(Objects.hashCode(key)));
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] newtable = new MapEntry[capacity];
        for (MapEntry<K, V> kvMapEntry : table) {
            if (kvMapEntry != null) {
                int index = index(kvMapEntry.key);
                newtable[index] = kvMapEntry;
            }
        }
        table = newtable;
    }

    @Override
    public V get(K key) {
        int index = index(key);
        V rsl = null;
        if (table[index] != null) {
            if (Objects.hashCode(key) == Objects.hashCode(table[index].key)) {
                if (Objects.equals(key, table[index].key)) {
                    rsl = table[index].value;
                }
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        int index = index(key);
        boolean rsl = true;
        if (table[index] == null) {
            rsl = false;
        } else if (Objects.hashCode(key) == Objects.hashCode(table[index].key)) {
            if (Objects.equals(key, table[index].key)) {
                table[index] = null;
            }
        }
        count--;
        modCount++;
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;

            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}