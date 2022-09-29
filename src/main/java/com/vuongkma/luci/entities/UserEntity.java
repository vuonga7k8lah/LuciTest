package com.vuongkma.luci.entities;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserEntity {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String name;

    @Column
    private String phone;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private Date created_date_gmt;

    @PrePersist
    protected void onCreate() {
        created_date_gmt = new Date();
    }

    private Date updated_date_gmt;

    @PreUpdate
    protected void onUpdate() {
        updated_date_gmt = new Date();
    }
}
