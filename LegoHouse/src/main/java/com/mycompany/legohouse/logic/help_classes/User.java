/*
 */
package com.mycompany.legohouse.logic.help_classes;

/**
 * Help class for the User object. Holds relevance when handling the SQL
 * database user table.
 *
 * @author Simon Asholt Norup
 */
public class User {

    private final String username;
    private final String password;
    private final String email;
    private Boolean admin;

    /**
     * User class constructor without the admin parameter. Implemented for
     * functions where admin status doesn't have any relevance.
     *
     * @param username
     * @param password
     * @param email
     */
    public User(String username, String password, String email) {
        this(username, password, email, false);
    }

    /**
     * User class constructor with the admin parameter. Implemented for
     * functions where admin status has relevance.
     *
     * @param username
     * @param password
     * @param email
     * @param admin
     */
    public User(String username, String password, String email, Boolean admin) {
        if (username == null || password == null || email == null) {
            throw new IllegalArgumentException("There was an issue concerning user object initialization. Please contact support.");
        }
        if (username.length() < 3) {
            throw new IllegalArgumentException("Invalid username. A username must be at least 3 characters long.");
        }
        if (password.isEmpty()) {
            throw new IllegalArgumentException("Please enter a password.");
        }
        if (!email.matches("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$")) {
            throw new IllegalArgumentException("Invalid email. An email must match the pattern '?@?.?'.");
        }
        this.username = username;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", email=" + email + ", admin=" + admin + '}';
    }
}
