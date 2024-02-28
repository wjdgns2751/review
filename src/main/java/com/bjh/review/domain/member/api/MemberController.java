package com.bjh.review.domain.member.api;

import com.bjh.review.domain.member.application.MemberService;
import com.bjh.review.domain.member.dto.MemberRequest;
import com.bjh.review.domain.member.dto.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signUpProc")
    public String signUp(MemberRequest.SignUpDTO signUpDTO) throws BadRequestException {
        MemberResponse.SignUpDTO sign = memberService.signUp(signUpDTO);
        log.info("sign {}",sign);
        return "redirect:/login";
    }

}
