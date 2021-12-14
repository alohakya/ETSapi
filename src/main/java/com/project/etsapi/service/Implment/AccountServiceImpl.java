package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Account;
import com.project.etsapi.mapper.AccountMapper;
import com.project.etsapi.service.AccountService;
import com.project.etsapi.vo.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Created by Intellij IDEA
 * @Description
 * @Author Li Lijun
 * @Date 2021/11/19
 * @Time 19:43
 **/

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountMapper accountMapper;

    @Override
    public Account getAccountById(String account_ID) {
        return accountMapper.getAccountById(account_ID);
    }

    @Override
    public Boolean idMatchPassword(String account_ID, String password) {
        Account account = accountMapper.getAccountById(account_ID);
        if(account == null){
            // 账号不存在，返回-1（账号或密码错误！）
            return Boolean.FALSE;
        }
        return account.getPassword().equals(password);
    }

    @Override
    public List<AccountInfo> getAllTeacherAccount() {
        return accountMapper.getAllTeacherAccount();
    }

    @Override
    public List<AccountInfo> getAllStudentAccount() {
        return accountMapper.getAllStudentAccount();
    }

    @Override
    public String banAccount(String account_ID) {
        if(accountMapper.getAccountById(account_ID) == null){
            return "-1";
        }
        return String.valueOf(accountMapper.updateActive(account_ID,"0"));
    }

    @Override
    public String activeAccount(String account_ID) {
        if(accountMapper.getAccountById(account_ID) == null){
            return "-1";
        }
        return String.valueOf(accountMapper.updateActive(account_ID,"1"));
    }

    @Override
    public String deleteAccount(String account_ID) {
        if(accountMapper.getAccountById(account_ID) == null){
            return "-1";
        }
        return String.valueOf(accountMapper.deleteAccount(account_ID));
    }

    @Override
    public String resetPassword(String account_ID) {
        if(accountMapper.getAccountById(account_ID) == null){
            return "-1";
        }
        return String.valueOf(accountMapper.updatePassword(account_ID,"111111"));
    }
}
