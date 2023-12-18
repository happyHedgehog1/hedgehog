package com.hedgehog.config.handler;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("login SuccessHandler가 오는게 맞냐 여기.");
        String userId = authentication.getName();
        String saveId = request.getParameter("saveId");
        if ("on".equals(saveId)) {
            Cookie userIdCookie = new Cookie("userId", userId);
            userIdCookie.setMaxAge(60 * 60 * 24 * 7);
            userIdCookie.setPath("/");
            response.addCookie(userIdCookie);
        } else {
            Cookie userIdCookie = new Cookie("userId", null);
            userIdCookie.setMaxAge(0);
            userIdCookie.setPath("/");
            response.addCookie(userIdCookie);
        }

        response.sendRedirect("/");
    }
}
