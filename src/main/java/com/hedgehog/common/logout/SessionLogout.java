package com.hedgehog.common.logout;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

/*로그아웃 구현. 모든 위치에서 로그아웃 버튼이 활성화 되어 있으므로 common 부분에서 구현한다.*/
public class SessionLogout {
    public static void invalidSession(HttpServletRequest req, HttpServletResponse res) {
        boolean isSecure = false; // 의미는?
        String contextPath = null; // 의미는?
        if (req != null) {
            HttpSession session = req.getSession(false); // false의 의미는 req의 세션이 없으면 null을 반환한다. true라면 새로운 세션을 반환하므로 false가 맞다.
            if (session != null) {
                // 가져온 서블렛request의 세션이 null이 아니면.
                session.invalidate();
            }
            isSecure = req.isSecure();
            contextPath = req.getContextPath();
        }
        SecurityContext context = SecurityContextHolder.getContext(); // 현재 사용자의 보안 정보를 가져온다.
        SecurityContextHolder.clearContext();
        context.setAuthentication(null);
        if (res != null) {
            Cookie cookie = new Cookie("JSESSIONID", null);
            String cookiePath = StringUtils.hasText(contextPath) ? contextPath : "/";
            cookie.setPath(cookiePath);
            cookie.setMaxAge(0);
            cookie.setSecure(isSecure);
            res.addCookie(cookie);
        }
    }
}
