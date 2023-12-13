package com.labs.tenderservice.entity.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class UserCreateDTO implements Serializable {
    private  String username;

    public UserCreateDTO() {
    }
}
