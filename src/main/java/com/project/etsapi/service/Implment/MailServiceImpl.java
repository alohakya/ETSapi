package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Account;
import com.project.etsapi.mapper.AccountMapper;
import com.project.etsapi.mapper.StudentMapper;
import com.project.etsapi.mapper.TeacherMapper;
import com.project.etsapi.service.MailService;
import com.project.etsapi.vo.RegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class MailServiceImpl implements MailService {

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

    @Override
    public String sendSimpleMail(String account_ID,String email, HttpSession session){
        if(studentMapper.getStudent(account_ID) == null && teacherMapper.getTeacher(account_ID) == null)
            return "-1";
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            String code = String.valueOf(new Random().nextInt(899999) + 100000);
            session.setAttribute("account_ID",account_ID);
            session.setAttribute("code",code);
            session.setAttribute("email", email);
            message.setFrom(from);
            message.setTo(email);
            message.setSubject("验证码邮件");
            message.setText("您的验证码为：" + code + "，十分钟内有效！");
            mailSender.send(message);
            System.out.println("邮件发送成功");
            return "1";
        }
        catch (Exception e){
            e.printStackTrace();
            return "-2";
        }
    }

    @Override
    public String register(RegisterInfo registerInfo, HttpSession session) {
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

        //更新数据库
        try{
            return accountMapper.addAccount(registerInfo.toAccount()) == 1? "1" : "-4";
        }
        catch (Exception e){
            e.printStackTrace();
            return "-4";
        }
    }
}
