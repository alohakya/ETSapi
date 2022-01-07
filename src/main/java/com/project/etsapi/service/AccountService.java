package com.project.etsapi.service;

import com.project.etsapi.entity.Account;
import com.project.etsapi.vo.AccountInfo;
import com.project.etsapi.vo.Privacy;

import java.util.List;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date 2021/11/19
 * @Time 19:41
 **/


public interface AccountService {
    Account getAccountById(String account_ID);

    Boolean idMatchPassword(String account_ID, String password);

    List<AccountInfo> getAllTeacherAccount();

    List<AccountInfo> getAllStudentAccount();

    String banAccount(String account_ID);

    String activeAccount(String account_ID);

    String deleteAccount(String account_ID);

    String resetPassword(String account_ID);

    void addAccount(Account account);

    Privacy getPrivacy(String account_ID);

    boolean checkRegisterInfo(String account_ID, String ID_number);
}
