package com.project.etsapi.Util;

import com.project.etsapi.mapper.AccountMapper;
import com.project.etsapi.mapper.StudentMapper;
import com.project.etsapi.mapper.TeacherMapper;
import com.project.etsapi.vo.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class MailUtil {

    @Value("${spring.mail.username}")
    public String from;

    @Autowired
    private JavaMailSender mailSender;

    public String sendCode(String email, String title, String content){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(email);
            message.setSubject(title);
            message.setText(content);
            mailSender.send(message);
            System.out.println("邮件发送成功");
            return "1";
        }
        catch (Exception e){
            e.printStackTrace();
            return "-2";
        }
    }

    public String verifyCode(RegisterInfo registerInfo, HttpSession session) {
        //更新数据库
        try{
            String account_ID = (String) session.getAttribute("account_ID");
            String ID_number = (String) session.getAttribute("ID_number");
            String email = (String) session.getAttribute("email");
            String code = (String) session.getAttribute("code");
            //验证码填写错误
            if(!code.equals(registerInfo.getCode())){
                return "-1";
            }
            //注册信息与之前填写的不一样
            if(!account_ID.equals(registerInfo.getAccount_ID())
                    || !email.equals(registerInfo.getEmail())
                    || !ID_number.equals(registerInfo.getID_number())){
                return "-2";
            }
            return "1";
        }
        catch (Exception e){
            e.printStackTrace();
            return "-3";
        }
    }
}
