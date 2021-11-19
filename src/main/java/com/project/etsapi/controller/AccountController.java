package com.project.etsapi.controller;

import com.project.etsapi.entity.Account;
import com.project.etsapi.entity.Course;
import com.project.etsapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date 2021/11/19
 * @Time 19:46
 **/

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AccountService accountService;

    // 根据账号ID获得账户
    // http://localhost:8888/account/get?account_ID=10100
    @GetMapping( "/get")
    @ResponseBody
    public Account getAccount(@RequestParam("account_ID") String account_ID){
        return accountService.getAccountById(account_ID);
    }

    // 验证账号密码是否正确
    // http://localhost:8888/account/idMatchPassword?account_ID=10100&password=000000
    @PostMapping( "/idMatchPassword")
    @ResponseBody
    public Boolean idMatchPassword(@RequestParam("account_ID") String account_ID, @RequestParam("password") String password){
        return accountService.idMatchPassword(account_ID,password);
    }

}
