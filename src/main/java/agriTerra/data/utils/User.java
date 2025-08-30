package agriterra.data.utils;

public class User {
    private final String username;
    private final String password;
    private final Rule ruolo;

    public User(String username, String password, Rule ruolo) {
        this.username = username;
        this.password = password;
        this.ruolo = ruolo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Rule getRuolo() {
        return ruolo;
    }

}
