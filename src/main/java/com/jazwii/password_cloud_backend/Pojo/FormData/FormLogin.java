package com.jazwii.password_cloud_backend.Pojo.FormData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jazwii.password_cloud_backend.Singleton.MessageConstants;

import javax.validation.constraints.NotNull;

public class FormLogin {
    @NotNull(message = MessageConstants.INVALID_LOGIN_NAME)
    @JsonProperty("login_name")
    private String loginName;

    @NotNull(message = MessageConstants.INVALID_LOGIN_WEBSITE)
    @JsonProperty("website")
    private String website;

    @JsonProperty("email")
    private String email;

    @NotNull(message = MessageConstants.INVALID_LOGIN_USERNAME)
    @JsonProperty("username")
    private String username;

    @NotNull(message = MessageConstants.INVALID_LOGIN_PASSWORD)
    @JsonProperty("password")
    private String password;

    @JsonProperty("use_digits")
    private boolean useDigits;

    @JsonProperty("use_lower_case")
    private boolean useLowerCase;

    @JsonProperty("use_upper_case")
    private boolean useUpperCase;

    @JsonProperty("length")
    private int length;

    public FormLogin() {
    }

    public FormLogin(String loginName, String website, String email, String username, String password) {
        this.loginName = loginName;
        this.website = website;
        this.email = email;
        this.username = username;
        this.password = password;
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

    public boolean isUseDigits() {
        return useDigits;
    }

    public void setUseDigits(boolean useDigits) {
        this.useDigits = useDigits;
    }

    public boolean isUseLowerCase() {
        return useLowerCase;
    }

    public void setUseLowerCase(boolean useLowerCase) {
        this.useLowerCase = useLowerCase;
    }

    public boolean isUseUpperCase() {
        return useUpperCase;
    }

    public void setUseUpperCase(boolean useUpperCase) {
        this.useUpperCase = useUpperCase;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
