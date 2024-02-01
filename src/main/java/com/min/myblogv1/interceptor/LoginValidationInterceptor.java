package com.min.myblogv1.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;

@Slf4j
public class LoginValidationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if(session == null){
            log.info("비회원 요청 입니다");
            response.sendRedirect("/login?redirectURL="+request.getRequestURI().replaceFirst("update","view"));
            return false;
        }
            log.info("URI={}",request.getRequestURI());
            log.info("URL={}",request.getRequestURL());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HttpSession session = request.getSession();
        log.info("postHandle sessionId={}",session.getId());
        log.info("postHandle session login={}",session.getAttribute("login"));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HttpSession session = request.getSession();
        log.info("afterCompletion sessionId={}",session.getId());
        log.info("afterCompletion session login={}",session.getAttribute("login"));
    }
}
