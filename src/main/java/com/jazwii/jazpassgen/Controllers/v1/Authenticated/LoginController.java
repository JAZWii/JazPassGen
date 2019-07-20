package com.jazwii.jazpassgen.Controllers.v1.Authenticated;

import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.jazpassgen.Entity.Model.Account;
import com.jazwii.jazpassgen.Entity.Model.Login;
import com.jazwii.jazpassgen.Exception.Exception.CommonException;
import com.jazwii.jazpassgen.Pojo.FormData.FormLogin;
import com.jazwii.jazpassgen.Pojo.ResponseData.Authenticated.LoginResponse;
import com.jazwii.jazpassgen.Pojo.ResponseData.Authenticated.LoginListResponse;
import com.jazwii.jazpassgen.Services.LoginService;
import com.jazwii.jazpassgen.Singleton.RestViews;
import com.jazwii.jazpassgen.Singleton.RouteConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = RouteConstants.AUTHENTICATED_LOGIN)
public class LoginController {
    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/logins", name = "get_logins")
    @JsonView(RestViews.LoginPublicMinimal.class)
    public LoginListResponse getLogins(@AuthenticationPrincipal Account account) {
        return new LoginListResponse(loginService.getLogins(account));
    }

    @GetMapping(value = "/login/{id}", name = "get_login_information")
    @JsonView(RestViews.LoginPublicDetailed.class)
    public LoginResponse getLoginInformation(@AuthenticationPrincipal Account account, @PathVariable("id") int id) {
        //throws an exception if login does not exist
        Login login = loginService.getLoginById(id);
        return new LoginResponse(login);
    }

    @PostMapping(value = "/login", name = "create_login")
    @JsonView(RestViews.LoginPublicDetailed.class)
    public LoginResponse createLogin(@AuthenticationPrincipal Account account, @Valid @RequestBody FormLogin form, Errors errors) {
        //validate the form sent by client
        if (errors.hasErrors()) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    errors
            );
        }
        //create login, set data and save it
        Login login = loginService.createLogin(form, account);
        return getLoginInformation(account, login.getId());
    }

    @PutMapping(value = "/login/{id}", name = "update_login")
    @JsonView(RestViews.LoginPublicDetailed.class)
    public LoginResponse updateLogin(@AuthenticationPrincipal Account account, @PathVariable("id") int id, @Valid @RequestBody FormLogin form, Errors errors) {
        //validate the form sent by client
        if (errors.hasErrors()) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    errors
            );
        }

        //throws an exception if login does not exist
        Login login = loginService.getLoginById(id);
        Login newLogin;
        //remove the login, and then re-create a new one
        try {
            loginService.removeLogin(login.getId(), account);
            newLogin = loginService.createLogin(form, account);
        } catch (Exception ex) {
            loginService.restoreLogin(login.getId(), account);
            throw ex;
        }
        //update login information and save it
        return getLoginInformation(account, newLogin.getId());
    }

    @DeleteMapping(value = "/login/{id}", name = "delete_login")
    @JsonView(RestViews.LoginPublicDetailed.class)
    public LoginResponse deleteLogin(@AuthenticationPrincipal Account account, @PathVariable("id") int id) {
        //throws an exception if login does not exist
        return new LoginResponse(loginService.removeLogin(id, account));
    }
}