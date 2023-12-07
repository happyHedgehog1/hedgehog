package com.hedgehog.config;

import com.hedgehog.config.handler.AuthFailHandler;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final AuthFailHandler authFailHandler;

    public SecurityConfig(AuthFailHandler authFailHandler) {
        this.authFailHandler = authFailHandler;
    }

    // 패스워드 인코딩후 저장할 예정
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 정적 리소스 설정 제외. /static 아래에 있는 /css 아래 전체, /js 아래 전체, /images 아래 전체
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests( // 페이지 권한 설정
                        //SUPER_ADMIN("SUPER_ADMIN"), ADMIN("ADMIN"), MEMBER("MEMBER"), GUEST("GUEST")
                        auth -> {
//                            auth.requestMatchers("/event/*",
//                                    "/adminmain/*",
//                                    "/adminManagement/*",
//                                    "/member/*",
//                                    "/order/*",
//                                    "/point/*",
//                                    "/category/*",
//                                    "/product/*",
//                                    "/Service/*",
//                                    "/autoMailModify/*",
//                                    "/autoMailViewport/*",
//                                    "/statistics/**").hasAnyAuthority(UserRole.ADMIN.getRole(), UserRole.SUPER_ADMIN.getRole());
//                            auth.requestMatchers("/").hasAnyAuthority(UserRole.MEMBER.getRole());
                            auth.requestMatchers("/**").permitAll();
//                            auth.anyRequest().authenticated();
                        })
                .formLogin( // 로그인 설정
                        login -> {
                            login.loginPage("/auth/login");
                            login.usernameParameter("userId");
                            login.passwordParameter("userPwd");
                            login.defaultSuccessUrl("/");
                            login.failureHandler(authFailHandler);
                        })
                .logout( // 로그아웃 설정
                        logout -> {
                            logout.deleteCookies("JSESSIONID");
                            logout.invalidateHttpSession(true);
                            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));
                            logout.logoutSuccessUrl("/");
                        })
                .sessionManagement( // 세션설정
                        session -> {
                            session.maximumSessions(1);
                            session.invalidSessionUrl("/");
                        })
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
