package nl.adacademie.drankbuddy.entity;

import java.util.Objects;

/**
 * Het hoofd entity van de applicatie. (Aggregate root)
 * <p>
 * Met een instantie van een account kan je alle andere entities ophalen.
 */
public class Account {

    private int id;
    private String username;
    private String password;
    private String name;

    public Account() { }

    public Account(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public Account(int id, String username, String password, String name) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Account{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", name='" + name + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass())
            return false;

        Account account = (Account) other;

        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
