package com.bjh.review.domain.member.application;
import com.bjh.review.domain.member.domain.Member;
import com.bjh.review.domain.member.dto.SignUpRequestDTO;
import com.bjh.review.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /*
    * 회원 가입
    */
    public void signUp(SignUpRequestDTO signUpRequestDTO) {

        if (!isEmailAvailable(signUpRequestDTO.email()))
            throw new RuntimeException("이미 사용중 인 아이디 입니다.");

        SignUpRequestDTO updatedSignUpRequestDTO = SignUpRequestDTO.builder()
                .name(signUpRequestDTO.name())
                .email(signUpRequestDTO.email())
                .password(passwordEncoder.encode(signUpRequestDTO.password()))
                .addr(signUpRequestDTO.addr())
                .build();

        Member member = updatedSignUpRequestDTO.toMemberEntity(signUpRequestDTO);
        memberRepository.save(member);

    }


    /*
     * 회원 가입 - 아이디 사용 가능 확인
     */
    public boolean isEmailAvailable(String email){
        return memberRepository.findMemberByEmail(email) == null;
    }



}





