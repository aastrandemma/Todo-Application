package org.github.aastrandemma.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppUserTest {
    private AppUser appUserCreated;

    @BeforeEach
    public void setup() {
        appUserCreated = new AppUser("createdAppUser", "createdPassword", AppRole.ROLE_APP_USER);
    }

    @AfterEach
    void tearDown() {
        appUserCreated = null;
    }

    @Test
    public void testCreateAppUser() {
        AppUser actualValue = new AppUser("testAppUser", "testPassword", AppRole.ROLE_APP_USER);
        assertEquals("testAppUser", actualValue.getUsername());
    }

    @Test
    public void testCreateAdminUser() {
        AppUser actualValue = new AppUser("testAppAdmin", "testAdminPassword", AppRole.ROLE_APP_ADMIN);
        assertEquals("testAppAdmin", actualValue.getUsername());
    }

    @Test
    public void testGetUsername() {
        assertEquals("createdAppUser", appUserCreated.getUsername());
    }

    @Test
    public void testSetUsername() {
        appUserCreated.setUsername("changedUserName");
        assertEquals("changedUserName", appUserCreated.getUsername());
    }

    @Test
    public void testGetRole() {
        assertEquals(AppRole.ROLE_APP_USER, appUserCreated.getRole());
    }
    @Test
    public void testSetRole() {
        appUserCreated.setRole(AppRole.ROLE_APP_ADMIN);
        assertEquals(AppRole.ROLE_APP_ADMIN, appUserCreated.getRole());
    }
}