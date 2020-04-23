package com.main.letstalk.component;

import com.main.letstalk.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    private String[] res = {".js", ".css", ".htm", "/LetsTalk", "/register","/save" , "/error", "jpg", "gif", "png"};

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        for (String s : res) {
            if (path.endsWith(s)) {
                return HandlerInterceptor.super.preHandle(request, response, handler);
            }
        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getUserId() <= 0) {
            //转向登录页面
            response.sendRedirect("LetsTalk");
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
