package com.jazwii.jazpassgen.Services;

import com.google.common.collect.Lists;
import com.jazwii.jazpassgen.Entity.Model.Account;
import com.jazwii.jazpassgen.Entity.Model.Login;
import com.jazwii.jazpassgen.Entity.Repository.LoginRepository;
import com.jazwii.jazpassgen.Exception.Exception.CommonException;
import com.jazwii.jazpassgen.PasswordGenerator;
import com.jazwii.jazpassgen.Pojo.FormData.FormLogin;
import com.jazwii.jazpassgen.Singleton.MessageConstants;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Primary
@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public List<Login> getLogins(Account account) {
        return loginRepository.findAllByAccountAndRemovedIsFalse(account);
    }

    public List<Login> getLoginsAdmin(Account account) {
        return loginRepository.findAllByAccount(account);
    }

    public List<Login> getLoginsAll() {
        return Lists.newArrayList(loginRepository.findAll().iterator());
    }

    public Login getLoginById(int loginId) {
        Login login = loginRepository.findByIdAndRemovedIsFalse(loginId);

        //throw an exception if login was not found
        if (login == null) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    MessageConstants.INVALID_LOGIN
            );
        }

        return login;
    }

    public Login updateLogin(Login login, FormLogin form, Account account) {
        //check if code already exist
        Login duplicateLogin = loginRepository.findFirstByLoginName(form.getLoginName());
        if (duplicateLogin != null && login.getId() != duplicateLogin.getId()) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    MessageConstants.DUPLICATE_LOGIN
            );
        }
        save(login, true, true);
        loginRepository.refresh(login);
        return login;
    }

    public Login createLogin(FormLogin form, Account account) {
        //create an empty login and update it by uy passing the form to update login method
        return createLogin(form, account, true);
    }

    public Login createLoginNewPassword(FormLogin form, Account account, boolean useDigits, boolean useLowerCase, boolean useUpperCase, int length) {
        //create an empty login and update it by uy passing the form to update login method
        return updatePassword(form, account, useDigits, useLowerCase, useUpperCase, length);
    }

    public Login createLogin(FormLogin form, Account account, boolean save) {
        //create an empty report and update it by uy passing the form to update report method
        return updateLogin(new Login(), form, account, save);
    }

    public Login updateLogin(Login login, FormLogin form, Account account, boolean save) {
        //check if code already exist
        Login duplicateReport = loginRepository.findFirstByLoginName(form.getLoginName());
        if (save && duplicateReport != null && login.getId() != duplicateReport.getId()) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    MessageConstants.DUPLICATE_LOGIN
            );
        }

        //update login's information
        login.setAccount(account);
        login.setLoginName(form.getLoginName());
        login.setEmail(form.getEmail());
        login.setPassword(form.getPassword());
        login.setUsername(form.getUsername());
        login.setWebsite(form.getWebsite());

        //save login and return it
        if (save) {
            save(login, true, true);
            loginRepository.refresh(login);
        }
        return login;
    }

    public Login removeLogin(int loginId, Account account) {
        Login login = loginRepository.findByIdAndRemovedIsFalse(loginId);
        return removeLogin(login, account);
    }

    public Login restoreLogin(int loginId, Account account) {
        Login login = loginRepository.findById(loginId);

        //show exception if target login does not exist or is removed already
        if (login == null) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    MessageConstants.INVALID_LOGIN
            );
        }

        //update login's information and store its current code to former
        //storing former code is helpful to avoid duplicates in case a new record is added
        //with the same code of a removed login and to restore login later if needed
        login.setLoginName(login.getFormerLoginName());
        login.setFormerLoginName(null);

        //set removed flag and remove data and the remover account
        login.setRemoved(false);
        login.setRemovedBy(null);
        login.setRemoveDate(null);

        //save login and return it
        save(login);
        return login;
    }

    public void save(Login login) {
        save(login, false, true);
    }

    public void save(Login login, boolean recursiveSave, boolean deleteDependant) {
        loginRepository.save(login);
    }

    public Login updatePassword(FormLogin form, Account account, boolean useDigits, boolean useLowerCase, boolean useUpperCase, int length){
        String password = generatePassword(useDigits,useLowerCase,useUpperCase,length);
        form.setPassword(password);
        return createLogin(form, account, true);
    }

    private String generatePassword(boolean useDigits, boolean useLowerCase, boolean useUpperCase){
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(useDigits)
                .useLower(useLowerCase)
                .useUpper(useUpperCase)
                .build();
        return passwordGenerator.generate(8);
    }

    private String generatePassword(boolean useDigits, boolean useLowerCase, boolean useUpperCase, int length){
        PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
                .useDigits(useDigits)
                .useLower(useLowerCase)
                .useUpper(useUpperCase)
                .build();
        return passwordGenerator.generate(length);
    }

    private Login removeLogin(Login login, Account account) {
        String generatedString = RandomStringUtils.randomAlphanumeric(150);

        //show exception if target login does not exist or is removed already
        if (login == null) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    MessageConstants.INVALID_LOGIN
            );
        }
        //update login's information and store its current code to former
        //storing former code is helpful to avoid duplicates in case a new record is added
        //with the same code of a removed login and to restore login later if needed
        login.setFormerLoginName(login.getLoginName());
        login.setLoginName(generatedString);

        //set removed flag and remove data and the remover account
        login.setRemoved(true);
        login.setRemovedBy(account);
        login.setRemoveDate(new Date());

        //save login and return it
        save(login);
        return login;
    }

    private Login removeLoginIfExists(String LoginName, Account account) {
        Login login = loginRepository.findFirstByLoginName(LoginName);
        if (login != null)
            return removeLogin(login, account);
        return null;
    }

}
