package com.jazwii.jazpassgen.Pojo.ResponseData.Authenticated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.jazpassgen.Entity.Model.Login;
import com.jazwii.jazpassgen.Singleton.RestViews;

public class LoginResponse {
    @JsonProperty("login")
    @JsonView(RestViews.LoginPublicDetailed.class)
    private Login login;

    public LoginResponse() {
    }

    public LoginResponse(Login login) {
        this.login = login;
    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}