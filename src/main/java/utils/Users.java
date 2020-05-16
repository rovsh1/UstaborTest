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

    private User getUser(String key) {
        return new User(
                readProperty(domain + "." + key + ".login"),
                readProperty(domain + "." + key + ".pass"),
                readProperty(domain + "." + key + ".phone")
        );
    }

    public User getDefaultMaster() {
        return getUser("defaultMaster");
    }

    public User getAdmin() {
        return getUser("admin");
    }
}
