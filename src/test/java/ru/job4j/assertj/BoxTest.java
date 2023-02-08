package ru.job4j.assertj;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String rsl = box.whatsThis();
        assertThat(rsl).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String rsl = box.whatsThis();
        assertThat(rsl).isNotEmpty()
                .isEqualTo("Tetrahedron")
                .startsWith("Tetra");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String rsl = box.whatsThis();
        assertThat(rsl).isNotNull()
                .isEqualTo("Cube")
                .endsWith("be");
    }

    @Test
    void isSphereHas0Verticles() {
        Box box = new Box(0, 10);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isLessThan(1)
                .isEqualTo(0);
    }

    @Test
    void isTetrahedronHas4Verticles() {
        Box box = new Box(4, 10);
        int rsl = box.getNumberOfVertices();
        assertThat(rsl).isGreaterThan(1)
                .isEqualTo(4);
    }

    @Test
    void isTetrahedronExist() {
        Box box = new Box(4, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue()
                .toString().equals(true);
    }

    @Test
    void isUnknownNonExist() {
        Box box = new Box(12, 10);
        boolean rsl = box.isExist();
        assertThat(rsl).isFalse();
    }

    @Test
    void isCube15Area1350() {
        Box box = new Box(8, 15);
        double rsl = box.getArea();
        assertThat(rsl).isGreaterThan(1345.0d)
            .isCloseTo(1350.0d, withPrecision(0.01d));
    }

    @Test
    void isSphere5Area314() {
        Box box = new Box(0, 5);
        double rsl = box.getArea();
        assertThat(rsl).isLessThan(315.1d)
                .isCloseTo(314.15d, withPrecision(0.01d));
    }
}