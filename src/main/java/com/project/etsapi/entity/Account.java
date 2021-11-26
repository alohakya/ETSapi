package com.project.etsapi.entity;

import lombok.Data;

@Data
public class Account {
    private String account_ID;
    private String password;
    private String email;
    private String is_active;

    Account(){

    }

    public Account(String account_ID, String password, String email, String is_active) {
        this.account_ID = account_ID;
        this.password = password;
        this.email = email;
        this.is_active = is_active;
    }

    public String getAccount_ID() {
        return account_ID;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getIs_active() {
        return is_active;
    }

    public void setAccount_ID(String account_ID) {
        this.account_ID = account_ID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
}
