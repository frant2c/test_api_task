package data;

import java.util.Objects;

public class PlayerResponse {

    String id;

    String username;

    String email;

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

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PlayerResponse otherPlayerRequest = (PlayerResponse) obj;
        return Objects.equals(id, otherPlayerRequest.id) &&
                Objects.equals(username, otherPlayerRequest.username) &&
                Objects.equals(email, otherPlayerRequest.email) &&
                Objects.equals(name, otherPlayerRequest.name) &&
                Objects.equals(surname, otherPlayerRequest.surname);
    }

    public int hashCode() {
        return Objects.hash(username, email, name, surname);
    }
}
