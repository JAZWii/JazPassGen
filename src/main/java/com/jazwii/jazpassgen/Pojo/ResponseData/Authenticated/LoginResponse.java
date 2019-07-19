package com.jazwii.jazpassgen.Pojo.ResponseData.Authenticated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.jazpassgen.Entity.Model.Login;
import com.jazwii.jazpassgen.Singleton.RestViews;

public class LoginResponse {
    @JsonProperty("login")
    @JsonView(RestViews.PublicMinimal.class)
    private Login login;

    public LoginResponse() {
    }

    public LoginResponse(Login address) {
        this.login = login;
    }

    public Login getAccount() {
        return login;
    }

    public void setAccount(Login account) {
        this.login = login;
    }
}