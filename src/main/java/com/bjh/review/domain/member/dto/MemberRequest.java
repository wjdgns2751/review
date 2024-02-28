package com.bjh.review.domain.member.dto;

import com.bjh.review.domain.member.domain.Member;
import com.bjh.review.domain.member.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberRequest {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class SignUpDTO {
        private String name;
        private String password;
        private String email;
        private String addr;
        private Role role;

        public Member toMemberEntity() {
            return Member.builder().
                    name(name).
                    email(email).
                    password(password).
                    addr(addr).
                    role(Role.ROLE_ADMIN).
                    build();
        }
    }


}