package ru.job4j.iterator;

import javax.swing.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (row == data.length ) {
            return false;
        }
        while (data[row].length == 0){
            if (row == data.length - 1){
                return false;
            }
            column = 0;
            row++;
        }
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = data[row][column];
        if (data[row].length > 1 && column < data[row].length - 1){
            column++;
        }
        if (column == 0){
            row++;
        }
        return rsl;
    }
}