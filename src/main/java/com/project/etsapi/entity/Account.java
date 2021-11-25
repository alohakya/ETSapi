package com.project.etsapi.entity;

import lombok.Data;

@Data
public class Account {
    private String account_ID;
    private String password;
    private String email;
    private String isActive;

    Account(){

    }

    public Account(String account_ID, String password, String email, String isActive) {
        this.account_ID = account_ID;
        this.password = password;
        this.email = email;
        this.isActive = isActive;
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

    public String getIsActive() {
        return isActive;
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

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
}
