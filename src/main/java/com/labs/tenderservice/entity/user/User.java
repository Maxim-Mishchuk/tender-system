package com.labs.tenderservice.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labs.tenderservice.entity.tender.Tender;
import jakarta.persistence.*;
import lombok.Getter;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String username;
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL )
    List<Tender>tenders;

}
