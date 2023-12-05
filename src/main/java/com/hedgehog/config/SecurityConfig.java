package com.hedgehog.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*@Configuration
@EnableWebSecurity*/
public class SecurityConfig {
/*    *//*취향에 맞게 만든 실패했을 경우의 Handler는 우선 생략*//*

    *//*패스워드 인코딩후 저장할 예정*//*
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    *//*정적 리소스 설정 제외. /static 아래에 있는 /css 아래 전체, /js 아래 전체, /images 아래 전체*//*
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests( // 페이지 권한 설정
                        //SUPER_ADMIN("SUPER_ADMIN"), ADMIN("ADMIN"), MEMBER("MEMBER"), GUEST("GUEST")
                        authorizationManagerRequestMatcherRegistry -> {
//                            authorizationManagerRequestMatcherRegistry.requestMatchers("/").
                                    *//*admin은 수정사항이 없다고 보는 입장.*//*
                        })
                .formLogin( // 로그인 설정
                        httpSecurityFormLoginConfigurer -> {

                        })
                .logout( // 로그아웃 설정
                        httpSecurityLogoutConfigurer -> {

                        })
                .sessionManagement( // 세션설정
                        httpSecuritySessionManagementConfigurer -> {

                        })
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());

        return http.build();
    }*/
}
