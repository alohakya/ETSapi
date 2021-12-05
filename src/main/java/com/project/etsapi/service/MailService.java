package com.project.etsapi.service;

import com.project.etsapi.entity.Account;
import com.project.etsapi.vo.RegisterInfo;

import javax.servlet.http.HttpSession;

public interface MailService {

    public String sendSimpleMail(String account_ID,String email, HttpSession session);

    public String register(RegisterInfo registerInfo, HttpSession session);
}
