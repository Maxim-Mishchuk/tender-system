package com.labs.tenderservice.entity.user;

import com.labs.tenderservice.entity.ID;
import com.labs.tenderservice.entity.IDEntity;

public class User extends IDEntity {
    private final String username;

    public User(ID id, String username) {
        super(id);
        this.username = username;
    }

    public ID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
