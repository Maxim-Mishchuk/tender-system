package com.labs.tenderservice.entity.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserCreateDTO {

    @NotBlank
    @Pattern(regexp = "\\w+")
    private  String username;

    public UserCreateDTO() {
    }
}
