package agricola.data.utils;

public class User {
    private String username;
    private String password;
    private Rule ruolo;

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
