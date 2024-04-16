package org.github.aastrandemma.data;

import org.github.aastrandemma.model.AppUser;

public interface IAppUserDAO extends IBaseByObjectDAO<AppUser> {
    AppUser findByUsername(String username);
    void remove(String username);
}