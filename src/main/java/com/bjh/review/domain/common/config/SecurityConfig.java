package com.bjh.review.domain.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public RoleHierarchy roleHierarchy() {

        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();

        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_MANAGER\n" +
                "ROLE_MANAGER > ROLE_MEMBER");

        return hierarchy;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity hs) throws Exception{
        //1개의 세션만 사용
        final int maximumSessionCnt = 1;

//        hs.csrf(AbstractHttpConfigurer::disable);

//        Form 형태의 로그인
        hs.formLogin((auth) -> auth.loginPage("/signIn")
                        .loginProcessingUrl("/signInProc")
                        .permitAll());

        hs.httpBasic(AbstractHttpConfigurer::disable);
        hs.authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/", "/signUp","/signIn","/signInProc","/signUpProc").permitAll()
                        .requestMatchers("/user/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated());

        hs.sessionManagement((auth) -> auth
                    .sessionFixation() // 세션 고정 공격을 보호
                    .changeSessionId() // 로그인 시 동일한 세션에 대한 id 변경
                    .maximumSessions(maximumSessionCnt)
                    .maxSessionsPreventsLogin(true) // - true : 초과시 새로운 로그인 차단 | false : 초과시 기존 세션 하나 삭제
        );



        hs.logout((auth) -> auth.logoutUrl("/signOut")
                        .logoutSuccessUrl("/"));



        return hs.build();
    }


}
