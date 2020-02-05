package com.jazwii.password_cloud_backend.Pojo.ResponseData.Authenticated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.password_cloud_backend.Entity.Model.Account;
import com.jazwii.password_cloud_backend.Singleton.RestViews;

public class AccountResponse {
    @JsonProperty("account")
    @JsonView(RestViews.PublicMinimal.class)
    private Account account;

    public AccountResponse() {
    }

    public AccountResponse(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
