package org.github.aastrandemma.data.impl;

import org.github.aastrandemma.JSONReader;
import org.github.aastrandemma.JSONWriter;
import org.github.aastrandemma.data.IPersonDAO;
import org.github.aastrandemma.model.Person;
import org.github.aastrandemma.data.sequencer.PersonIdSequencer;

import java.util.ArrayList;
import java.util.List;

public class PersonDAOCollection implements IPersonDAO {
    private static List<Person> storage = new ArrayList<>();
    private static PersonDAOCollection instance;

    private PersonDAOCollection() {
    }

    public static PersonDAOCollection getInstance() {
        if (instance == null) { //Singleton pattern, makes sure only one instance exist
            instance = new PersonDAOCollection();
        }
        return instance;
    }

    public void initializePerson() {
        storage = JSONReader.getInstance().readToList("people");
    }

    public void shutDownPerson() {
        JSONWriter.getInstance().writeFromListToJson(storage, "people");
    }

    @Override
    public Person persist(Person person) { // add a new Person.class object to a collection
        if (person == null) throw new IllegalArgumentException("Person can't be null.");
        Person exist = findById(person.getId());
        if (exist != null) throw new IllegalArgumentException("Person already exist.");
        if (person.getId() == 0) {
            person.setId(PersonIdSequencer.getInstance().nextId());
        } else {
            storage.forEach(p -> {
                if (p.getId() == person.getId()) {
                    System.out.println("Person ID already exist");
                    System.out.println("Changing ID to unique");
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