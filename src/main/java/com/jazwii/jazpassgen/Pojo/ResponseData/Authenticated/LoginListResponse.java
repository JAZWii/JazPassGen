package com.jazwii.jazpassgen.Pojo.ResponseData.Authenticated;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.jazpassgen.Entity.Model.Login;
import com.jazwii.jazpassgen.Singleton.RestViews;

import java.util.List;

public class LoginListResponse {
    @JsonProperty("logins")
    @JsonView(RestViews.LoginPublicMinimal.class)
    private List<Login> logins;

    public LoginListResponse() {
    }

    public LoginListResponse(List<Login> logins) {
        this.logins = logins;
    }

    public List<Login> getLogins() {
        return logins;
    }

    public void setLogins(List<Login> logins) {
        this.logins = logins;
    }
}
