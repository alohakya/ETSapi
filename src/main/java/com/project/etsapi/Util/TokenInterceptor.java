package com.project.etsapi.Util;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if(token == null || token.equals("")){
            response.sendError(401);
            return false;
        }
        JwtUtil jwtUtil = new JwtUtil();
        return jwtUtil.verifyToken(token);
    }
}
