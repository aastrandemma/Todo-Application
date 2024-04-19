package org.github.aastrandemma.data;

import org.github.aastrandemma.model.Person;

public interface IPersonDAO extends IBaseByObjectDAO<Person> {
    Person findById(int id);
    Person findByEmail(String email);
    void remove(int id);
}