package com.project.etsapi.controller;

import com.project.etsapi.Util.JwtUtil;
import com.project.etsapi.Util.MailUtil;
import com.project.etsapi.entity.Account;
import com.project.etsapi.service.AccountService;
import com.project.etsapi.vo.AccountInfo;
import com.project.etsapi.vo.Privacy;
import com.project.etsapi.vo.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    MailUtil mailUtil;

    JwtUtil jwtUtil = new JwtUtil();

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

    @PostMapping("/getPrivacy")
    @ResponseBody
    public Privacy getPrivacy(String account_ID){
        return accountService.getPrivacy(account_ID);
    }

    /**
     * @description: 验证账号密码是否正确
     * @type: post
     * @path: "/account/login"
     * @param: account_ID
     * @param: password
     * @return: java.lang.String
     * 成功：返回account_ID 和 token
     * 失败：返回null
     * @date: 2021/11/26 15:37
     */
    @PostMapping( "/login")
    public List<String> idMatchPassword(String account_ID,String password, HttpServletResponse response){
        if (accountService.idMatchPassword(account_ID,password)){
            String token = jwtUtil.createToken(account_ID,password);
            response.setHeader("token",token);
            List<String> result = new ArrayList<>();
            result.add(account_ID);
            result.add(token);
            return result;
        };
        return null;
    }

    /**
     * @description: 删除账号
     * @path: "/account/delete"
     * @type: post
     * @param: account_ID
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：没有这个account_ID
     * @date: 2021/12/14 19:06
     */
    @PostMapping("/delete")
    public String deleteAccount(String account_ID){
        return accountService.deleteAccount(account_ID);
    }

    /**
     * @description: 修改邮箱
     * @path: "/account/changeEmail"
     * @type:
     * @param: account_ID
     * @param: email
     * @return: java.lang.String
     * @date: 2022/1/7 23:07
     */
    @PostMapping("/changeEmail")
    public String changeEmail(String account_ID,String email){
        if(accountService.changeEmail(account_ID,email) == 1){
            return email;
        }
        return "-1";
    }

    /**
     * @description: 修改密码
     * @path: "/account/changeEmail"
     * @type:
     * @param: account_ID
     * @param: password
     * @return: java.lang.String
     * @date: 2022/1/7 23:07
     */
    @PostMapping("/changePassword")
    public String changePassword(String account_ID,String password,HttpServletResponse response){
        if(accountService.changePassword(account_ID,password) == 1){
            String token = jwtUtil.createToken(account_ID,password);
            response.setHeader("token",token);
            return token;
        }
        return "-1";
    }

    /**
     * @description: 重置密码
     * @path: "/account/resetPassword"
     * @type: post
     * @param: account_ID
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：没有这个account_ID
     * @date: 2021/12/14 19:11
     */
    @PostMapping("/resetPassword")
    public String resetPassword(String account_ID){
        return String.valueOf(accountService.changePassword(account_ID,"111111"));
    }

    /**
     * @description: 获得所有老师账号
     * @path:
     * @type:
     * @param:
     * @return: java.util.List<com.project.etsapi.vo.AccountInfo>
     * @date: 2021/12/14 18:33
     */
    @GetMapping("/getAllTeacherAccount")
    public List<AccountInfo> getAllTeacherAccount(){
        return accountService.getAllTeacherAccount();
    }

    /**
     * @description: 获得所有学生账号
     * @path: "/account/"
     * @type:
     * @param:
     * @return: java.util.List<com.project.etsapi.vo.AccountInfo>
     * @date: 2021/12/14 18:33
     */
    @GetMapping("/getAllStudentAccount")
    public List<AccountInfo> getAllStudentAccount(){
        return accountService.getAllStudentAccount();
    }

    /**
     * @description: 封禁账户
     * @path: "/account/ban"
     * @type: post
     * @param: account_ID
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：没有这个account_ID
     * @date: 2021/12/14 18:40
     */
    @PostMapping("/ban")
    public String banAccount(String account_ID){
        return accountService.banAccount(account_ID);
    }

    /**
     * @description: 激活账户
     * @path: "/account/active"
     * @type: post
     * @param: account_ID
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：没有这个account_ID
     * @date: 2021/12/14 18:40
     */
    @PostMapping("/active")
    public String activeAccount(String account_ID){
        return accountService.activeAccount(account_ID);
    }

    /**
     * @description: 发送邮件
     * @type: post
     * @path: "/account/sendEmail"
     * @param: account_ID 传入学号或工号account_ID，邮箱email,身份证号ID_number
     * @param: email
     * @param: request
     * @return: java.lang.String
     * 返回1：发送成功
     * 返回-1：填写信息错误或账号已注册
     * 返回-2：邮件发送失败
     * @date: 2021/12/5 15:01
     */
    @PostMapping("/sendEmail")
    public String sendEmail(HttpServletRequest request){
        String ID_number = request.getParameter("ID_number");
        String account_ID = request.getParameter("account_ID");
        String email = request.getParameter("email");
        //检验账号信息
        if(!accountService.checkRegisterInfo(account_ID,ID_number)){
            return "-1";
        }
        //发送验证码
        HttpSession session = request.getSession();
        String title = "注册验证码";
        String code = String.valueOf(new Random().nextInt(899999) + 100000);
        String content = "您的验证码为：" + code + "，仅可使用一次，请尽快使用。（这是一封自动发送的邮件，请勿直接回复）";
        String result = mailUtil.sendCode(email,title,content);
        //发送成功则记录
        if(result.equals("1")){
            session.setAttribute("ID_number",ID_number);
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
     * @param: registerInfo 传入学号或工号account_ID，邮箱email,身份证号ID_number，密码password，验证码code
     * @param: session
     * @return: java.lang.String
     * 返回1：成功
     * 返回-1：验证码错误
     * 返回-2：注册信息与之前填写的不一样
     * 返回-3：没有发
     * @date: 2021/12/5 15:02
     */
    @PostMapping("/register")
    public String register(RegisterInfo registerInfo, HttpServletRequest request){
        HttpSession session = request.getSession();
        String result =  mailUtil.verifyCode(registerInfo,session);
        if(result.equals("1")){
            accountService.addAccount(registerInfo.toAccount());
            session.removeAttribute("ID_number");
            session.removeAttribute("account_ID");
            session.removeAttribute("code");
            session.removeAttribute("email");
        }
        return result;
    }
}
