package com.project.etsapi.controller;

import com.project.etsapi.Util.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/test")
public class Test {
    @PostMapping("/1")
    public void test1(HttpServletRequest request, HttpServletResponse response){

        String account_ID = request.getParameter("account_ID");
        String password = request.getParameter("password");
//        System.out.println(account_ID);
//        System.out.println(password);
        JwtUtil jwtUtil = new JwtUtil();
//        System.out.println(jwtUtil);
        response.addHeader("token",jwtUtil.createToken(account_ID,password));
    }

    @PostMapping("/2")
    public void test2(HttpServletRequest request){
        System.out.println(request.getRequestURI());
        if(request.getHeader("token") != null){
            System.out.println(request.getHeader("token"));
        }
        else {
            System.out.println("无");
        }
    }
}
