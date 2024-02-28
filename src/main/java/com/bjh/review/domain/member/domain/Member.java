package com.bjh.review.domain.member.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
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

}
