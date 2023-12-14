package com.labs.tenderservice.entity.user.dto;

import com.labs.tenderservice.entity.tender.dto.TenderDTO;
import com.labs.tenderservice.entity.user.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.util.List;


@Data
public class UserDTO {

    @PositiveOrZero
    private final long id;
    @NotBlank
    private final String username;
    private final List<TenderDTO> tenderList;

    public static UserDTO getDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                TenderDTO.getList(user.getTenders())
        );
    }

    public static UserDTO getBasicDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                null
        );
    }

    public static List<UserDTO> getList(List<User> userList) {
        return userList.stream()
                .map(UserDTO::getBasicDTO)
                .toList();
    }
}
