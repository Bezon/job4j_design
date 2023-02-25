package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        /*используйте код из предыдущего задания*/
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> l = head;
        for (int i = 0; i < index; i++) {
            l = l.next;
        }
        return l.item;
    }

    public T deleteFirst() {
        return null;
    }

    @Override
    public Iterator<T> iterator() {

         return new Iterator<T>() {
            int expectedModCount = modCount;
            Node<T> l = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return l != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T result = l.item;
                l = l.next;
                return result;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}