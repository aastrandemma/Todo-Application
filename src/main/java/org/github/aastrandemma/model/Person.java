package org.github.aastrandemma.model;

import java.util.Objects;

public class Person {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Person(String firstName, String lastName, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    public void setFirstName(String firstName) {
        Objects.requireNonNull(firstName, "First name can't be null.");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        Objects.requireNonNull(lastName, "Last name can't be null.");
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        Objects.requireNonNull(email, "E-mail can't be null.");
        this.email = email;
    }

    private int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getSummary(){
        return "PersonInfo {id: " + id + ", name: " + firstName + " " + lastName + ", email: " + email + "}";
    }
}