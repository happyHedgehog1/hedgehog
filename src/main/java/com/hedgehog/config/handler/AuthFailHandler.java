package com.hedgehog.config.handler;

import com.hedgehog.common.common.exception.UserWithdrawCancelException;
import com.hedgehog.common.common.exception.UserWithdrawException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

/*
 * user 로그인 실패 시 실패요청을 커스텀 하는 Handler
 * 원래는 AuthenticationFailureHandler 라는 인터페이스를 구현해야 하지만.
 * 어느정도 작성이 되어 있는 SimpleUrl... 클래스를 활용한다.
 * */
@Configuration
public class AuthFailHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        /*로그인 실패시 어떠한 메시지를 응답할 건지. 물론 기본적으로 부모의 메서드를 사용하지만 거기에 추가기능을 넣은 개념*/
        String errorMessage;

        if (exception instanceof BadCredentialsException) {
            errorMessage = "아이디가 존재하지 않거나 비밀번호가 일치하지 않습니다.";
        } else if (exception instanceof InternalAuthenticationServiceException) {
            errorMessage = "서버에서 오류가 발생되었습니다.\n관리자에게 문의해주세요.";
        } else if (exception instanceof UsernameNotFoundException) {
            errorMessage = "존재하지 않는 아이디입니다.\n아이디를 확인해주세요.";
        } else if (exception instanceof AuthenticationCredentialsNotFoundException) {
            errorMessage = "인증요청이 거부되었습니다.\n관리자에게 문의해주세요.";
        } else if (exception instanceof UserWithdrawException) {
            errorMessage = "탈퇴한 계정입니다.\n로그인 화면으로 돌아갑니다.";
        } else if (exception instanceof UserWithdrawCancelException) {
            errorMessage = "withdrawCancel";
        } else {
            errorMessage = "알수없는 오류가 발생했습니다.\n관리자에게 문의해주세요.";
        }
        errorMessage = URLEncoder.encode(errorMessage, "UTF-8");

        setDefaultFailureUrl("/auth/fail?message=" + errorMessage);
        super.onAuthenticationFailure(request, response, exception);
    }
}
