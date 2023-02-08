package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void isEmptyArrayOnParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void isNotContainEqualOnParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Name"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Name");
    }

    @Test
    void isNotContainKeyOnParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=Name"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("=Name");
    }

    @Test
    void isNotContainValueOnParse() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Name="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Name=");
    }
}