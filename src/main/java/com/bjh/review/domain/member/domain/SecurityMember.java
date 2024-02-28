package com.bjh.review.domain.member.domain;

import com.bjh.review.domain.member.dto.MemberResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


@RequiredArgsConstructor
@Getter
public class SecurityMember implements UserDetails {
    private final MemberResponse.SignInDTO member;
    //권한
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(()-> String.valueOf(member.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    // false일 경우 인증을 허용하지 않고 사용자는 권한을 얻지못해 엑세스를 못함
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있는지 확인
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 유효기간 -> 변경 요청
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 / 시큐리티는 사용자가 인증하도록 허용
    @Override
    public boolean isEnabled() {
        return member.getStatus();
    }
}
