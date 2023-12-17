package com.labs.tenderservice.entity.user;

import com.labs.tenderservice.entity.tender.Tender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    private String username;
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL )
    List<Tender>tenders;

    public User(String username) {
        this.id = System.nanoTime();
        this.username = username;
    }

    public User(long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User() {

    }
}
