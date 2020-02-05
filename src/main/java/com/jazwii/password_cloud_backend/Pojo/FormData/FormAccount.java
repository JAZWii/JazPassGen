package com.jazwii.password_cloud_backend.Pojo.FormData;

import com.jazwii.password_cloud_backend.Singleton.MessageConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class FormAccount {
    @NotNull(message = MessageConstants.INVALID_EMAIL_ADDRESS)
    @Size(min = 4, max = 32, message = MessageConstants.INVALID_EMAIL_ADDRESS)
    @Email(message = MessageConstants.INVALID_EMAIL_ADDRESS)
    @JsonProperty("email")
    private String email;

    @NotNull(message = MessageConstants.INVALID_PASSWORD_POLICY)
    @Size(min = 8, max = 64, message = MessageConstants.INVALID_PASSWORD_POLICY)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = MessageConstants.INVALID_PASSWORD_POLICY)
    @JsonProperty("password")
    private String password;

    public FormAccount() {
    }

    public FormAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
