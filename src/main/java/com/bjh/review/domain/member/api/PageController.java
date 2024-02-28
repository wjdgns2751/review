package com.bjh.review.domain.member.api;

import com.bjh.review.domain.member.application.MemberService;
import com.bjh.review.domain.member.dto.MemberResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PageController {
    private final MemberService memberService;

    @GetMapping("/")
    public String mainPage(Model model){
        MemberResponse.SignInDTO member = memberService.sessionCheck();
        model.addAttribute("email", member.getEmail());
        model.addAttribute("role", member.getRole());
        return "main";
    }

    @GetMapping("/signIn")
    public String signInPage(){
        return "signin";
    }

    @GetMapping("/signUp")
    public String signupPage(){
        return "signup";
    }

    @GetMapping("/signOut")
    public String signOutPage(HttpServletRequest req, HttpServletResponse res){
        memberService.signOut(req,res);
        return "redirect:/";
    }


}
