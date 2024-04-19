package org.github.aastrandemma.data.impl;

import org.github.aastrandemma.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PersonDAOCollectionTest {
    PersonDAOCollection testObject;
    Person createdPerson1;
    Person createdPerson2;

    @BeforeEach
    public void setup() {
        testObject = PersonDAOCollection.getInstance();
        createdPerson1 = testObject.persist(new Person("Jane", "Doe", "jane@doe.com"));
        createdPerson2 = testObject.persist(new Person("John", "Doe", "john@doe.com"));
    }

    @AfterEach
    public void tearDown() {
        testObject = null;
        createdPerson1 = null;
        createdPerson2 = null;
    }

    @Test
    public void testPersonInstance() {
        assertNotNull(PersonDAOCollection.getInstance());
    }

    @Test
    public void testCreateNonExistingPersonWithNonGeneratedId() { //Test persist()
        Person expectedValue = new Person(10, "Jane", "Smith", "jane@smith.com");
        Person actualValue = testObject.persist(expectedValue);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testCreateNonExistingPersonWithGeneratedId() { //Test persist()
        Person expectedValue = new Person("Jane", "Smith", "jane@smith.com");
        Person actualValue = testObject.persist(expectedValue);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindAll() {
        List<Person> expectedValue = new ArrayList<>();
        expectedValue.add(createdPerson1);
        expectedValue.add(createdPerson2);
        List<Person> actualValue = testObject.findAll();

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindById() {
        Person expectedValue = createdPerson1;
        Person actualValue = testObject.findById(1);

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindByEmail() {
        Person expectedValue = createdPerson2;
        Person actualValue = testObject.findByEmail("john@doe.com");

        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testRemove() {
        testObject.remove(1);
        assertNull(testObject.findById(1));
    }
}