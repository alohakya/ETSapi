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
    }

    @PostMapping("/2")
    public void test2(HttpServletRequest request){
        System.out.println("hh");
    }
}
