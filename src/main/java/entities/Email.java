package entities;

public class Email {

    private String host;
    private String login;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

    public Email(String host, String email, String password) {
        this.host = host;
        this.login = email;
        this.password = password;
    }
}
