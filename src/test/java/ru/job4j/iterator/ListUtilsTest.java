package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        Predicate<Integer> pred = f -> f.intValue() > 2;
        ListUtils.removeIf(input, pred);
        assertThat(input).hasSize(1).containsSequence(1);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.addAfter(input, 0, 6);
        ListUtils.addAfter(input, 0, 3);
        Predicate<Integer> pred = f -> f.intValue() == 3;
        ListUtils.replaceIf(input, pred, 5);
        assertThat(input).hasSize(4).containsSequence(1, 5, 6, 5);
    }


    @Test
    void whenRemoveAll() {
        ListUtils.addAfter(input, 0, 4);
        ListUtils.addAfter(input, 0, 3);
        List<Integer> elements = new ArrayList(List.of(3, 6, 5));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(2).containsSequence(1, 4);
    }
}