package org.github.aastrandemma.data.impl;

import org.github.aastrandemma.JSONReader;
import org.github.aastrandemma.JSONWriter;
import org.github.aastrandemma.data.IAppUserDAO;
import org.github.aastrandemma.model.AppUser;

import java.util.ArrayList;
import java.util.List;

public class AppUserDAOCollection implements IAppUserDAO {
    private List<AppUser> storage = new ArrayList<>();
    private static AppUserDAOCollection instance;

    private AppUserDAOCollection() {
    }

    public static AppUserDAOCollection getInstance() {
        if (instance == null) {
            instance = new AppUserDAOCollection();
        }
        return instance;
    }

    public void initializeAppUser() {
        storage = JSONReader.readToList("appUser");
    }

    public void shutDownAppUser() {
        JSONWriter.writeFromListToJson(storage, "appUser");
    }

    /**
     * Persists a new application user to the storage.
     * This method adds a new application user to the storage if it does not already exist.
     *
     * @param appUser The appUser to persist.
     * @return The newly persisted application user.
     * @throws IllegalArgumentException If the provided appUser is null or
     * if an application user with the same username already exist.
     */
    @Override
    public AppUser persist(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("Application user cannot be null.");
        AppUser exist = findByUsername(appUser.getUsername());
        if (exist != null) throw new IllegalArgumentException("Application username already exist.");
        storage.add(appUser);
        return appUser;
    }

    @Override
    public List<AppUser> findAll() {
        return new ArrayList<>(storage);
    }

    @Override
    public AppUser findByUsername(String username) {
        return storage.stream()
                .filter(appUser -> appUser.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void remove(String username) {
        storage.removeIf(appUser -> appUser.getUsername().equals(username));
    }
}