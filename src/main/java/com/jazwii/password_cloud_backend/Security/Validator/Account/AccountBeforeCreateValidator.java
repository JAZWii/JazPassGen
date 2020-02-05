package com.jazwii.password_cloud_backend.Security.Validator.Account;

import com.jazwii.password_cloud_backend.Entity.Model.Account;
import com.jazwii.password_cloud_backend.Services.AccountService;
import com.jazwii.password_cloud_backend.Singleton.MessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component("beforeCreateAccountValidator")
public class AccountBeforeCreateValidator implements Validator {
    @Autowired
    private AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account = (Account) target;
        Account foundByEmail = accountService.getAccountByEmail(account.getEmail());

        //if provided phone number and email exist for a user who has not activated yet
        if (foundByEmail != null && foundByEmail.getEnabled() == 0) {
            errors.reject(MessageConstants.ERROR_ACCOUNT_NEEDS_ACTIVATION, MessageConstants.ERROR_ACCOUNT_NEEDS_ACTIVATION);
            return;
        }

        //if email already exists
        if (foundByEmail != null) {
            errors.reject(MessageConstants.ERROR_ACCOUNT_EMAIL_ALREADY_EXISTS, MessageConstants.ERROR_ACCOUNT_EMAIL_ALREADY_EXISTS);
            return;
        }
    }
}

