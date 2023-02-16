package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {
    @Test
    void whenAddAndFindThenRolenameIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("M", "Manager"));
        Role result = store.findById("M");
        assertThat(result.getRolename()).isEqualTo("Manager");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("M", "Manager"));
        Role result = store.findById("A");
        assertThat(result).isNull();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("A", "Admin"));
        boolean rsl = store.delete("A");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("M", "Manager"));
        boolean rsl = store.replace("A", new Role("A", "Admin"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("M", "Manager"));
        boolean rsl = store.replace("M", new Role("M", "Manager"));
        assertThat(rsl).isTrue();
    }
}