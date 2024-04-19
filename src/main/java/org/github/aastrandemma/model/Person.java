package org.github.aastrandemma.model;

import java.util.Objects;

import static java.util.Objects.hash;
import java.io.Serializable;

public class Person implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private AppUser credentials;

    public Person(String firstName, String lastName, String email) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }
    public Person(int id, String firstName, String lastName, String email) {
        this(firstName, lastName, email);
        this.id = id;
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

    public void setCredentials(AppUser credentials) {
        this.credentials = credentials;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
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

    public AppUser getCredentials() {
        return credentials;
    }

    @Override
    public int hashCode() {
        return hash(id,firstName,lastName,email);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Person)) return false;
        return id == ((Person) obj).getId() && Objects.equals(firstName, ((Person) obj).getFirstName())
                && Objects.equals(lastName, ((Person) obj).getLastName()) && Objects.equals(email, ((Person) obj).getEmail());
    }

    @Override
    public String toString() {
        return "PersonInfo {id: " + getId() + ", name: " + getFirstName() + " " + getLastName() + ", email: " + getEmail() + "}";
    }
}