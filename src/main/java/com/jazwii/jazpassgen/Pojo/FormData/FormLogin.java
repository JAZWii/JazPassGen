package com.jazwii.jazpassgen.Pojo.FormData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jazwii.jazpassgen.Entity.Model.Account;

import javax.persistence.*;

public class FormLogin {
    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    })
    private Account account;

    @JsonProperty("login_name")
    private String loginName;

    @JsonProperty("website")
    private String website;

    @JsonProperty("email")
    private String email;

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    public FormLogin() {
    }

    public FormLogin(Account account, String loginName, String website, String email, String username, String password) {
        this.account = account;
        this.loginName = loginName;
        this.website = website;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
