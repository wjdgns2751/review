package com.bjh.review.domain.member.application;
import com.bjh.review.domain.member.domain.Member;
import com.bjh.review.domain.member.domain.Role;
import com.bjh.review.domain.member.dto.MemberResponse;
import com.bjh.review.domain.member.dto.MemberRequest;
import com.bjh.review.domain.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;


@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
    * 회원 가입
    */
    public MemberResponse.SignUpDTO signUp(MemberRequest.SignUpDTO sign) throws BadRequestException {

        if (!isEmailAvailable(sign.getEmail()))
            throw new BadRequestException("이미 사용중 인 아이디 입니다.");

        MemberRequest.SignUpDTO updatedSignUpDTO = MemberRequest.SignUpDTO.builder()
                .name(sign.getName())
                .email(sign.getEmail())
                .password(bCryptPasswordEncoder.encode(sign.getPassword()))
                .addr(sign.getAddr())
                .build();

        Member member =  memberRepository.save(updatedSignUpDTO.toMemberEntity());

        return new MemberResponse.SignUpDTO(member);
    }


    /*
     * 회원 가입 - 아이디 사용 가능 확인
     */
    private boolean isEmailAvailable(String email){
        return memberRepository.findMemberByEmail(email) == null;
    }


    /*
     * 회원 로그인 - 세션 정보 확인
     * */
    public MemberResponse.SignInDTO sessionCheck(){
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
       Iterator<? extends GrantedAuthority> iter = authorities.iterator();
       GrantedAuthority auth = iter.next();

       String id = SecurityContextHolder.getContext().getAuthentication().getName();
       String role = auth.getAuthority();

       return new MemberResponse.SignInDTO(id,role);

    }
    /*
     * 회원 로그아웃
     * */
    public void signOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }
}





