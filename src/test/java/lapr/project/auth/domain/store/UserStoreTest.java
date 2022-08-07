package lapr.project.auth.domain.store;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import lapr.project.auth.domain.model.Email;
import lapr.project.auth.domain.model.Password;
import lapr.project.auth.domain.model.User;
import org.junit.jupiter.api.Test;

class UserStoreTest {
    @Test
    void testCreate() {
        User actualCreateResult = (new UserStore()).create("Name", "jane.doe@example.org", "iloveyou");
        assertEquals("jane.doe@example.org - Name", actualCreateResult.toString());
        assertEquals("Name", actualCreateResult.getName());
        assertTrue(actualCreateResult.getRoles().isEmpty());
        assertEquals("jane.doe@example.org", actualCreateResult.getId().getString());
    }

    @Test
    void testAdd() {
        UserStore userStore = new UserStore();
        Email id = new Email("jane.doe@example.org");
        assertTrue(userStore.add(new User(id, new Password("iloveyou"), "Name")));
    }

    @Test
    void testAdd2() {
        UserStore userStore = new UserStore();
        Email id = new Email("jane.doe@example.org");
        userStore.add(new User(id, new Password("iloveyou"), "Name"));
        Email id1 = new Email("jane.doe@example.org");
        assertFalse(userStore.add(new User(id1, new Password("iloveyou"), "Name")));
    }

    @Test
    void testAdd3() {
        assertFalse((new UserStore()).add(null));
    }

    @Test
    void testRemove() {
        UserStore userStore = new UserStore();
        Email id = new Email("jane.doe@example.org");
        assertFalse(userStore.remove(new User(id, new Password("iloveyou"), "Name")));
    }

    @Test
    void testRemove2() {
        UserStore userStore = new UserStore();
        Email id = new Email("jane.doe@example.org");
        userStore.add(new User(id, new Password("iloveyou"), "Name"));
        Email id1 = new Email("jane.doe@example.org");
        assertTrue(userStore.remove(new User(id1, new Password("iloveyou"), "Name")));
    }

    @Test
    void testRemove3() {
        assertFalse((new UserStore()).remove(null));
    }

    @Test
    void testGetById() {
        assertFalse((new UserStore()).getById("jane.doe@example.org").isPresent());
    }

    @Test
    void testGetById2() {
        UserStore userStore = new UserStore();
        Email id = new Email("jane.doe@example.org");
        userStore.add(new User(id, new Password("iloveyou"), "Name"));
        assertTrue(userStore.getById("jane.doe@example.org").isPresent());
    }

    @Test
    void testGetById3() {
        UserStore userStore = new UserStore();
        Email id = new Email("U.U.U@U.U.U.UUUU");
        userStore.add(new User(id, new Password("iloveyou"), "Name"));
        assertFalse(userStore.getById("jane.doe@example.org").isPresent());
    }

    @Test
    void testGetById4() {
        UserStore userStore = new UserStore();
        assertFalse(userStore.getById(new Email("jane.doe@example.org")).isPresent());
    }

    @Test
    void testGetById5() {
        UserStore userStore = new UserStore();
        Email id = new Email("jane.doe@example.org");
        userStore.add(new User(id, new Password("iloveyou"), "Name"));
        assertTrue(userStore.getById(new Email("jane.doe@example.org")).isPresent());
    }

    @Test
    void testGetById6() {
        UserStore userStore = new UserStore();
        Email id = new Email("jane.doe@example.org");
        userStore.add(new User(id, new Password("iloveyou"), "Name"));
        assertFalse(userStore.getById((Email) null).isPresent());
    }

    @Test
    void testExists() {
        assertFalse((new UserStore()).exists("jane.doe@example.org"));
        assertFalse((new UserStore()).exists((User) null));
    }

    @Test
    void testExists2() {
        UserStore userStore = new UserStore();
        Email id = new Email("jane.doe@example.org");
        userStore.add(new User(id, new Password("iloveyou"), "Name"));
        assertTrue(userStore.exists("jane.doe@example.org"));
    }

    @Test
    void testExists3() {
        UserStore userStore = new UserStore();
        Email id = new Email("U.U.U@U.U.U.UUUU");
        userStore.add(new User(id, new Password("iloveyou"), "Name"));
        assertFalse(userStore.exists("jane.doe@example.org"));
    }

    @Test
    void testExists4() {
        UserStore userStore = new UserStore();
        assertFalse(userStore.exists(new Email("jane.doe@example.org")));
    }

    @Test
    void testExists5() {
        UserStore userStore = new UserStore();
        Email id = new Email("jane.doe@example.org");
        userStore.add(new User(id, new Password("iloveyou"), "Name"));
        assertTrue(userStore.exists(new Email("jane.doe@example.org")));
    }

    @Test
    void testExists6() {
        UserStore userStore = new UserStore();
        Email id = new Email("jane.doe@example.org");
        userStore.add(new User(id, new Password("iloveyou"), "Name"));
        assertFalse(userStore.exists((Email) null));
    }

    @Test
    void testExists7() {
        UserStore userStore = new UserStore();
        Email id = new Email("jane.doe@example.org");
        assertFalse(userStore.exists(new User(id, new Password("iloveyou"), "Name")));
    }

    @Test
    void testExists8() {
        UserStore userStore = new UserStore();
        Email id = new Email("jane.doe@example.org");
        userStore.add(new User(id, new Password("iloveyou"), "Name"));
        Email id1 = new Email("jane.doe@example.org");
        assertTrue(userStore.exists(new User(id1, new Password("iloveyou"), "Name")));
    }
}

