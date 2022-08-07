package lapr.project.auth.domain.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lapr.project.auth.domain.model.UserRole;
import org.junit.jupiter.api.Test;

class UserRoleStoreTest {
    @Test
    void testCreate() {
        UserRole actualCreateResult = (new UserRoleStore()).create("42", "The characteristics of someone or something");
        assertEquals("The characteristics of someone or something", actualCreateResult.getDescription());
        assertEquals("42", actualCreateResult.getId());
    }

    @Test
    void testCreate2() {
        UserRoleStore userRoleStore = new UserRoleStore();
        userRoleStore.add(new UserRole("42", "The characteristics of someone or something"));
        UserRole actualCreateResult = userRoleStore.create("UserRole id and/or description cannot be blank.",
                "The characteristics of someone or something");
        assertEquals("The characteristics of someone or something", actualCreateResult.getDescription());
        assertEquals("USERROLE ID AND/OR DESCRIPTION CANNOT BE BLANK.", actualCreateResult.getId());
    }

    @Test
    void testAdd() {
        UserRoleStore userRoleStore = new UserRoleStore();
        assertTrue(userRoleStore.add(new UserRole("42", "The characteristics of someone or something")));
    }

    @Test
    void testAdd2() {
        UserRoleStore userRoleStore = new UserRoleStore();
        userRoleStore.add(new UserRole("42", "The characteristics of someone or something"));
        assertFalse(userRoleStore.add(new UserRole("42", "The characteristics of someone or something")));
    }

    @Test
    void testAdd3() {
        assertFalse((new UserRoleStore()).add(null));
    }

    @Test
    void testRemove() {
        UserRoleStore userRoleStore = new UserRoleStore();
        assertFalse(userRoleStore.remove(new UserRole("42", "The characteristics of someone or something")));
    }

    @Test
    void testRemove2() {
        UserRoleStore userRoleStore = new UserRoleStore();
        userRoleStore.add(new UserRole("42", "The characteristics of someone or something"));
        assertTrue(userRoleStore.remove(new UserRole("42", "The characteristics of someone or something")));
    }

    @Test
    void testRemove3() {
        assertFalse((new UserRoleStore()).remove(null));
    }

    @Test
    void testGetById() {
        assertFalse((new UserRoleStore()).getById("42").isPresent());
    }

    @Test
    void testGetById2() {
        UserRoleStore userRoleStore = new UserRoleStore();
        userRoleStore.add(new UserRole("42", "The characteristics of someone or something"));
        assertTrue(userRoleStore.getById("42").isPresent());
    }

    @Test
    void testGetById3() {
        UserRoleStore userRoleStore = new UserRoleStore();
        userRoleStore.add(new UserRole("Id", "The characteristics of someone or something"));
        assertFalse(userRoleStore.getById("42").isPresent());
    }

    @Test
    void testGetById4() {
        UserRoleStore userRoleStore = new UserRoleStore();
        userRoleStore.add(new UserRole("42", "The characteristics of someone or something"));
        assertFalse(userRoleStore.getById("").isPresent());
    }

    @Test
    void testExists() {
        assertFalse((new UserRoleStore()).exists("42"));
        assertFalse((new UserRoleStore()).exists((UserRole) null));
    }

    @Test
    void testExists2() {
        UserRoleStore userRoleStore = new UserRoleStore();
        userRoleStore.add(new UserRole("42", "The characteristics of someone or something"));
        assertTrue(userRoleStore.exists("42"));
    }

    @Test
    void testExists3() {
        UserRoleStore userRoleStore = new UserRoleStore();
        userRoleStore.add(new UserRole("Id", "The characteristics of someone or something"));
        assertFalse(userRoleStore.exists("42"));
    }

    @Test
    void testExists4() {
        UserRoleStore userRoleStore = new UserRoleStore();
        userRoleStore.add(new UserRole("42", "The characteristics of someone or something"));
        assertFalse(userRoleStore.exists(""));
    }

    @Test
    void testExists5() {
        UserRoleStore userRoleStore = new UserRoleStore();
        assertFalse(userRoleStore.exists(new UserRole("42", "The characteristics of someone or something")));
    }

    @Test
    void testExists6() {
        UserRoleStore userRoleStore = new UserRoleStore();
        userRoleStore.add(new UserRole("42", "The characteristics of someone or something"));
        assertTrue(userRoleStore.exists(new UserRole("42", "The characteristics of someone or something")));
    }
}

