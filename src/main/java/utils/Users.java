package utils;

import entities.User;

public final class Users {

    private static final String PROPERTY_FILE = "users.properties";
    private final String domain;

    Users(String domain) {
        this.domain = domain;
    }

    private static String readProperty(String key) {
        return PropertyReader.getInstance().getProperty(key, PROPERTY_FILE);
    }

    public User getAdmin() {
        return new User(
                readProperty("admin.login"),
                readProperty("admin.pass"),
                ""
        );
    }
}
