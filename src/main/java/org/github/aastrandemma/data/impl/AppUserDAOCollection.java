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
        storage = JSONReader.getInstance().readToList("appUser");
    }

    public void shutDownAppUser() {
        JSONWriter.getInstance().writeFromListToJson(storage, "appUser");
    }

    @Override
    public AppUser persist(AppUser appUser) {
        if (appUser == null) throw new IllegalArgumentException("AppUser can't be null.");
        AppUser exist = findByUsername(appUser.getUsername());
        if (exist != null) throw new IllegalArgumentException("Username already exist.");
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