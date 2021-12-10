package com.project.etsapi.controller;

import com.project.etsapi.entity.Account;
import com.project.etsapi.service.AccountService;
import com.project.etsapi.service.MailService;
import com.project.etsapi.vo.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;
import java.util.concurrent.ScheduledExecutorService;

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
    private ScheduledExecutorService scheduledExecutorService;

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
     * 返回1：成功
     * 返回-1：账号或密码错误
     * @date: 2021/11/26 15:37
     */
    @PostMapping( "/login")
    public String idMatchPassword(@RequestParam("account_ID") String account_ID, @RequestParam("password") String password){
        return String.valueOf(accountService.idMatchPassword(account_ID,password));
    }


    /**
     * @description: 发送邮件
     * @type: post
     * @path: "/account/sendEmail"
     * @param: account_ID 传入学号或工号account_ID，以及邮箱email
     * @param: email
     * @param: request
     * @return: java.lang.String
     * 返回1：发送成功
     * 返回-1：account_ID不存在
     * 返回-2：发送失败
     * @date: 2021/12/5 15:01
     */
    @PostMapping("/sendEmail")
    public String sendEmail(String account_ID, String email, HttpServletRequest request){
        HttpSession session = request.getSession();
        String title = "注册验证码";
        String code = String.valueOf(new Random().nextInt(899999) + 100000);
        String content = "您的验证码为：" + code + "，仅可使用一次，请尽快使用。（这是一封自动发送的邮件，请勿直接回复）";
        String result = mailService.sendSimpleMail(account_ID,email,title,content);
        //发送成功则记录
        if(result.equals("1")){
            session.setAttribute("account_ID",account_ID);
            session.setAttribute("code",code);
            session.setAttribute("email", email);
        }
        return result;
    }


    /**
     * @description: 核对注册信息、验证码
     * @type: post
     * @path: "/account/register"
     * @param: registerInfo 传入学号或工号account_ID、邮箱email、密码password、验证码code
     * @param: session
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：id与之前填写的不一样
     * 返回-2：email与之前填写的不一样
     * 返回-3：验证码错误
     * 返回-4：数据库操作出错，总之就是失败了
     * @date: 2021/12/5 15:02
     */
    @PostMapping("/register")
    public String register(RegisterInfo registerInfo, HttpServletRequest request){
        HttpSession session = request.getSession();
        String result =  mailService.register(registerInfo,session);
        if(result.equals("1")){
            session.removeAttribute("account_ID");
            session.removeAttribute("code");
            session.removeAttribute("email");
        }
        return result;
    }
}
