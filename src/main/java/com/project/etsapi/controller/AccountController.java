package com.project.etsapi.controller;

import com.project.etsapi.entity.Account;
import com.project.etsapi.service.AccountService;
import com.project.etsapi.service.MailService;
import com.project.etsapi.vo.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

    @Autowired
    MailService mailService;

    /**
     * @description: 根据账号ID获得账户
     * @type: post
     * @path: "/account/get"
     * @param: account_ID
     * @return: com.project.etsapi.entity.Account
     * @date: 2021/11/26 15:35
     */
    @PostMapping( "/get")
    @ResponseBody
    public Account getAccount(
           @RequestParam("account_ID") String account_ID){
        return accountService.getAccountById(account_ID);
    }

    /**
     * @description: 验证账号密码是否正确
     * @type: post
     * @path: "/account/login"
     * @param: account_ID
     * @param: password
     * @return: java.lang.String
     * @date: 2021/11/26 15:37
     */
    @PostMapping( "/login")
    public String idMatchPassword(@RequestParam("account_ID") String account_ID, @RequestParam("password") String password){
        return String.valueOf(accountService.idMatchPassword(account_ID,password));
    }


    /**
     * @description: 发送邮件
     * @path: "/account/sendEmail"
     * @param: account_ID
     * @param: email
     * @param: session
     * @return: java.lang.String
     * 返回1：发送成功
     * 返回-1：account_ID不存在
     * 返回-2：发送失败
     * @date: 2021/12/5 15:01
     */
    @PostMapping("/sendEmail")
    public String sendEmail(String account_ID,String email, HttpSession session){
        return mailService.sendSimpleMail(account_ID,email,session);
    }

    /**
     * @description: 核对注册信息、验证码
     * @path: "/account/register"
     * @param: registerInfo
     * @param: session
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：id与之前填写的不一样
     * 返回-2：email与之前填写的不一样
     * 返回-3：验证码错误
     * 返回-4：数据库出错，总之就是失败了
     * @date: 2021/12/5 15:02
     */
    @PostMapping("/register")
    public String register(RegisterInfo registerInfo, HttpSession session){
        return mailService.register(registerInfo,session);
    }
}
