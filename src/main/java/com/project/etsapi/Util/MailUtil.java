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

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    public String sendCode(String account_ID, String email, String title, String content){
        if(accountMapper.getAccountById(account_ID) != null)
            return "-1";
        if(studentMapper.getStudent(account_ID) == null && teacherMapper.getTeacher(account_ID) == null)
            return "-2";
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
            return "-3";
        }
    }

    public String verifyCode(RegisterInfo registerInfo, HttpSession session) {
        //更新数据库
        try{
            String account_ID = (String) session.getAttribute("account_ID");
            String email = (String) session.getAttribute("email");
            String code = (String) session.getAttribute("code");
            //id与之前填写的不一样
            if(!account_ID.equals(registerInfo.getAccount_ID())){
                return "-1";
            }
            //邮箱与之前填写的不一样
            if(!email.equals(registerInfo.getEmail())){
                return "-2";
            }
            //验证码与之前填写的不一样
            if(!code.equals(registerInfo.getCode())){
                return "-3";
            }
            return "1";
        }
        catch (Exception e){
            e.printStackTrace();
            return "-4";
        }
    }
}
