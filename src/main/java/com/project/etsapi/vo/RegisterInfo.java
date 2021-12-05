package com.project.etsapi.vo;

import com.project.etsapi.entity.Account;
import lombok.Data;

@Data
public class RegisterInfo {
    private String account_ID;
    private String password;
    private String email;
    private String code;

    public Account toAccount(){
        Account account = new Account();
        account.setAccount_ID(this.account_ID);
        account.setEmail(this.getEmail());
        account.setPassword(this.password);
        account.setIs_active("1");
        return account;
    }

    public String getAccount_ID() {
        return account_ID;
    }

    public String getCode() {
        return code;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setAccount_ID(String account_ID) {
        this.account_ID = account_ID;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
