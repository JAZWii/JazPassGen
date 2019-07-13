package com.jazwii.jazpassgen.Pojo.ResponseData.Authenticated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.jazpassgen.Classes.Model.Account;
import com.jazwii.jazpassgen.Singleton.RestViews;

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
