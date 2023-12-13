package com.labs.tenderservice.entity.user.dto;

import com.labs.tenderservice.entity.tender.Tender;
import com.labs.tenderservice.entity.tender.dto.TenderDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data

public class UserDTO implements Serializable {
    private final long id;
    private final String username;
    private final List<Tender> tenderList;

    public UserDTO(long id, String username, List<Tender> tenderList) {
        this.id = id;
        this.username = username;
        this.tenderList = tenderList;
    }
}
