package com.example.proto.domain.Admin.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "admins")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Admin {
    @Id
    @Column(name = "admin_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "reg_at")
    private LocalDateTime regAt;

    @Column(name = "upd_at")
    private LocalDateTime updAt;

    @PrePersist
    public void prePersist() {
        this.regAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {this.updAt = LocalDateTime.now();}
}
