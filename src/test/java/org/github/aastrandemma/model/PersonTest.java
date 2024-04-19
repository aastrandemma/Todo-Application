package org.github.aastrandemma.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonTest {
    private Person createdPerson;
    private AppUser appUserCreated;

    @BeforeEach
    public void setup() {
        createdPerson = new Person("Jane", "Doe", "jane@doe.se");
        appUserCreated = new AppUser("createdAppUser", "createdPassword", AppRole.ROLE_APP_USER);
    }

    @AfterEach
    public void tearDown() {
        createdPerson = null;
        appUserCreated = null;
    }

    @Test
    public void testCreateNonExistingPerson() {
        Person actualValue = new Person("John", "Doe", "john@doe.se");
        assertEquals("John", actualValue.getFirstName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("jane@doe.se", createdPerson.getEmail());
    }

    @Test
    public void testSetEmail() {
        createdPerson.setEmail("doe@jane.se");
        assertEquals("doe@jane.se", createdPerson.getEmail());
    }
    @Test
    public void testGetLastname() {
        assertEquals("Doe", createdPerson.getLastName());
    }

    @Test
    public void testSetLastName() {
        createdPerson.setLastName("Smith");
        assertEquals("Smith", createdPerson.getLastName());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("Jane", createdPerson.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        createdPerson.setFirstName("Jenna");
        assertEquals("Jenna", createdPerson.getFirstName());
    }

    @Test
    public void testSetAndGetCredentials() {
        createdPerson.setCredentials(appUserCreated);
        assertEquals("createdAppUser", createdPerson.getCredentials().getUsername());
    }
}