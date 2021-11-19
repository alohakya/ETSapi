package com.project.etsapi.service.Implment;

import com.project.etsapi.entity.Account;
import com.project.etsapi.mapper.AccountMapper;
import com.project.etsapi.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return account.getPassword().equals(password);
    }
}
