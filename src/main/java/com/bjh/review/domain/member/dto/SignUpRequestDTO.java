package com.bjh.review.domain.member.dto;

import com.bjh.review.domain.member.domain.Member;
import lombok.Builder;

@Builder
public record SignUpRequestDTO(
        String name,
        String email,
        String password,
        String addr

) {
    public Member toMemberEntity(SignUpRequestDTO sign){
       return Member.builder().
                name(sign.name).
                email(sign.email).
                password(sign.password).
                addr(sign.addr).
                build();
 }
}
