package com.bjh.review.domain.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private String addr;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Boolean status;

    private LocalDateTime createdAt;
    private LocalDateTime updateAt;

    @PrePersist
    public void onCreate(){
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate(){
        this.updateAt = LocalDateTime.now();
    }

    @Builder
    public Member(String name, String email, String password, String addr, Role role, LocalDateTime createdAt,
                  LocalDateTime updateAt) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.addr = addr;
        this.role = role;
        this.status = true;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

}
