package com.project.etsapi.controller;

import com.project.etsapi.entity.Account;
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

    /**
     * @description: 根据账号ID获得账户
     * @path: "/account/get"
     * @param: account_ID
     * @return: com.project.etsapi.entity.Account
     * @date: 2021/11/26 15:35
     */
    @PostMapping( "/get")
    @ResponseBody
    public Account getAccount(@RequestParam("account_ID") String account_ID){
        return accountService.getAccountById(account_ID);
    }


    /**
     * @description: 验证账号密码是否正确
     * @path: "/account/idMatchPassword
     * @param: account_ID
     * @param: password
     * @return: java.lang.String
     * @date: 2021/11/26 15:37
     */
    @PostMapping( "/login")
    public String idMatchPassword(@RequestParam("account_ID") String account_ID, @RequestParam("password") String password){
        return String.valueOf(accountService.idMatchPassword(account_ID,password));
    }

}
