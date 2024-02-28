package com.bjh.review.domain.member.dto;

import com.bjh.review.domain.common.DateUtils;
import com.bjh.review.domain.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;


public class MemberResponse{

    @AllArgsConstructor
    @Builder
    @Getter
    public static class SignUpDTO {
        private String name;
        private String role;
        private String createdAt;

        public SignUpDTO(Member member) {
            this.name = member.getName();
            this.role = member.getRole().toString();
            this.createdAt = DateUtils.toStringFormat(member.getCreatedAt());
        }

    }

    @AllArgsConstructor
    @Builder
    @Getter
    public static class SignInDTO implements Serializable {
        private String email;
        private String password;
        private String role;
        private Boolean status;

        public SignInDTO(String email, String role) {
            this.email = email;
            this.role = role;
        }
    }




}

