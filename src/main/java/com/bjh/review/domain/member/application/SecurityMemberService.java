package com.bjh.review.domain.member.application;

import com.bjh.review.domain.member.domain.Member;
import com.bjh.review.domain.member.domain.SecurityMember;
import com.bjh.review.domain.member.dto.MemberResponse;
import com.bjh.review.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityMemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findMemberByEmail(email);

        if(member != null){
            MemberResponse.SignInDTO sign = MemberResponse.SignInDTO.builder().
                    email(member.getEmail()).
                    password(member.getPassword()).
                    status(member.getStatus()).
                    role(String.valueOf(member.getRole())).build();

            return new SecurityMember(sign);
        }

        return null;
    }
}
