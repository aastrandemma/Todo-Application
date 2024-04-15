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

    public String getUsername() {
        return username;
    }

    private String getPassword() {
        return password;
    }

    public AppRole getRole() {
        return role;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username can't be null or empty.");
        }
        this.username =  username;
    }

    public void setPassword(String password){
        if (password.trim().isEmpty()) {
            throw new IllegalArgumentException("Username can't be null or empty.");
        }
        this.password =  password;
    }

    public void setRole(AppRole role) {
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