package org.github.aastrandemma.data.impl;

import org.github.aastrandemma.model.AppRole;
import org.github.aastrandemma.model.AppUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AppUserDAOCollectionTest {
    AppUserDAOCollection testObject;
    AppUser createdAppUser;
    AppUser createdAppAdmin;

    @BeforeEach
    public void setup() {
        testObject = AppUserDAOCollection.getInstance();
        createdAppUser = testObject.persist(new AppUser("JaneDoe007", "secretPassword", AppRole.ROLE_APP_USER));
        createdAppAdmin = testObject.persist(new AppUser("SmithAdmin", "adminPassword", AppRole.ROLE_APP_ADMIN));
    }

    @AfterEach
    public void tearDown() {
        testObject.remove("JaneSmith01");
        testObject.remove(createdAppAdmin.getUsername());
        testObject.remove(createdAppUser.getUsername());
    }

    @Test
    public void testAppUserInstance() {
        assertNotNull(AppUserDAOCollection.getInstance());
    }

    @Test
    public void testCreateAppUser() { //Testing persist()
        AppUser expectedValue = new AppUser("JaneSmith01", "testPassword", AppRole.ROLE_APP_USER);
        AppUser actualValue = testObject.persist(expectedValue);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCreateExistingAppUserShouldThrowIllegalArgumentException() { //Testing persist()
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> testObject.persist(createdAppUser),
                "We are expecting an error message regarding USERNAME"
        );

        assertTrue(thrown.getMessage().contains("Username already exist."));
    }

    @Test
    public void testFindAllUsersIncludingAdmins() { //Testing findAll()
        List<AppUser> expectedValue = new ArrayList<>();
        expectedValue.add(createdAppUser);
        expectedValue.add(createdAppAdmin);
        List<AppUser> actualValue = testObject.findAll();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindByUsername() {
        AppUser expectedValue = createdAppUser;
        AppUser actualValue = testObject.findByUsername(expectedValue.getUsername());

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testRemoveAppUser() {
        testObject.remove(createdAppUser.getUsername());

        assertNull(testObject.findByUsername(createdAppUser.getUsername()));
    }
}