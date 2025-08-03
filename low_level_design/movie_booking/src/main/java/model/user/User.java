package model.user;

import java.util.UUID;

public class User {
    private String id;
    private String name;
    private UserStatus status;

    public User(String id, String name, UserStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserStatus getStatus() {
        return status;
    }
}
