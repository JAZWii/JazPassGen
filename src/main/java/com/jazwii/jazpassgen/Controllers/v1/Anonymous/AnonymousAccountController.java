package com.jazwii.jazpassgen.Controllers.v1.Anonymous;

import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.jazpassgen.Exception.Exception.CommonException;
import com.jazwii.jazpassgen.Pojo.FormData.FormAccount;
import com.jazwii.jazpassgen.Pojo.ResponseData.Authenticated.AccountResponse;
import com.jazwii.jazpassgen.Services.AccountService;
import com.jazwii.jazpassgen.Singleton.RestViews;
import com.jazwii.jazpassgen.Singleton.RouteConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = RouteConstants.ANONYMOUS_CUSTOMER_ACCOUNT)
public class AnonymousAccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/account/authenticated", name = "create_authenticated_test_account")
    @JsonView(RestViews.AccountPublicMinimal.class)
    public AccountResponse createAuthenticatedTestAccount(@Valid @RequestBody FormAccount form, Errors errors) {
        //validate create account form sent by client
        if (errors.hasErrors()) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    errors
            );
        }

        return new AccountResponse(accountService.createAccount(form));
    }

    @PostMapping(value = "/account/admin", name = "create_admin_test_account")
    @JsonView(RestViews.AccountPublicMinimal.class)
    public AccountResponse createAdminTestAccount(@Valid @RequestBody FormAccount form, Errors errors) {
        //validate create account form sent by client
        if (errors.hasErrors()) {
            throw new CommonException(
                    HttpStatus.BAD_REQUEST,
                    CommonException.Type.COMMON_EXCEPTION_INVALID_FORM,
                    errors
            );
        }

        return new AccountResponse(accountService.createAdminAccount(form));
    }
}
