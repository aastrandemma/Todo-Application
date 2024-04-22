package org.github.aastrandemma.data.impl;

import org.github.aastrandemma.JSONReader;
import org.github.aastrandemma.JSONWriter;
import org.github.aastrandemma.data.IPersonDAO;
import org.github.aastrandemma.model.Person;
import org.github.aastrandemma.data.sequencer.PersonIdSequencer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonDAOCollection implements IPersonDAO {
    private List<Person> storage = new ArrayList<>();
    private static PersonDAOCollection instance;

    private static final Logger logger = Logger.getLogger(PersonDAOCollection.class.getName());
    public static void initializeLogger(Logger parentLogger) {
        logger.setLevel(Level.INFO);
        logger.setParent(parentLogger);
    }

    private PersonDAOCollection() {
    }

    public static PersonDAOCollection getInstance() {
        if (instance == null) { //Singleton pattern, makes sure only one instance exist
            instance = new PersonDAOCollection();
        }
        return instance;
    }

    public void initializePerson() {
        storage = JSONReader.readToList("people");
    }

    public void shutDownPerson() {
        JSONWriter.writeFromListToJson(storage, "people");
    }

    /**
     * Persists a new person to the storage.
     * This method adds a new person to the storage if it does not already exist. If the provided person has an ID of 0,
     * it assigns a new unique ID to the person.
     *
     * @param person The person to persist.
     * @return The newly persisted person.
     * @throws IllegalArgumentException If the provided person is null or if a person with the same ID already exist.
     */
    @Override
    public Person persist(Person person) { // add a new Person.class object to a collection
        if (person == null) throw new IllegalArgumentException("Person cannot be null.");
        Person exist = findById(person.getId());
        if (exist != null) throw new IllegalArgumentException("A person with the same ID already exists.");
        if (person.getId() == 0) {
            person.setId(PersonIdSequencer.getInstance().nextId());
        } else {
            storage.forEach(p -> {
                if (p.getId() == person.getId()) {
                    logger.warning("Person ID already exists. Changing ID to a unique value.");
                    person.setId(PersonIdSequencer.getInstance().nextId());
                }
            });
        }
        storage.add(person);
        return person;
    }

    @Override
    public List<Person> findAll() { // returns all Person.class objects
        return new ArrayList<>(storage);
    }

    @Override
    public Person findById(int id) { // returns single Person.class object
        return storage.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Person findByEmail(String email) { // returns single Person.class object
        return storage.stream()
                .filter(person -> person.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void remove(int id) { // remove one Person.class object from a collection
        storage.removeIf(person -> person.getId() == id);
    }
}