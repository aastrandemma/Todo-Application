package org.github.aastrandemma.model;

import java.util.Objects;
import static java.util.Objects.*;

public class AppUser{
    private String username;
    private String password;
    private AppRole role;

    public AppUser(String username, String password, AppRole role) {
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    private String getUsername() {
        return username;
    }

    private String getPassword() {
        return password;
    }

    private AppRole getRole() {
        return role;
    }

    private void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username can't be null or empty.");
        }
        this.username =  username;
    }

    private void setPassword(String password){
        if (password.trim().isEmpty()) {
            throw new IllegalArgumentException("Username can't be null or empty.");
        }
        this.password =  password;
    }

    private void setRole(AppRole role) {
        this.role = requireNonNull(role, "Role can't be null.");
    }

    @Override
    public int hashCode() {
        return hash(username, role);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof AppUser)) return false;
        return Objects.equals(((AppUser) obj).username, this.username) && ((AppUser) obj).role == this.role;
    }

    @Override
    public String toString() {
        String roleText = "User";
        if(Objects.equals(getRole().toString(), "ROLE_APP_ADMIN")) {
            roleText = "Admin";
        }
        return "Username: " + getUsername() + ", Role: " + roleText;
    }
}