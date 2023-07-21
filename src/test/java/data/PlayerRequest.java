package data;

import java.util.Objects;

public class PlayerRequest {

    String currency_code;

    String id;

    String username;

    String email;

    String password_change;

    String password_repeat;

    String name;

    String surname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword_change() {
        return password_change;
    }

    public String getPassword_repeat() {
        return password_repeat;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }


    public String getName() {
        return name;
    }

    public String getCurrency_code() {
        return currency_code;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PlayerRequest otherPlayerRequest = (PlayerRequest) obj;
        return Objects.equals(username, otherPlayerRequest.username) &&
                Objects.equals(email, otherPlayerRequest.email) &&
                Objects.equals(name, otherPlayerRequest.name) &&
                Objects.equals(surname, otherPlayerRequest.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, name, surname);
    }
}
