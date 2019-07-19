package com.jazwii.jazpassgen.Singleton;

import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.List;

public class MessageConstants implements Serializable {
    public static final String INVALID_OBJECT = "invalid_object";
    public static final String INVALID_FORM = "invalid_form";
    public static final String LOGICAL_ERROR = "logical_error";
    public static final String LOGICAL_UNEXPECTED_ERROR = "logical_unexpected_error";
    public static final String SERVICE_ERROR = "service_error";

    public static final String INVALID_PHONE_NUMBER = "invalid_server_form_invalid_phone_number";
    public static final String INVALID_EMAIL_ADDRESS = "invalid_server_form_invalid_email_address";
    public static final String INVALID_PASSWORD_POLICY = "invalid_server_form_invalid_password_policy";
    public static final String INVALID_SMS_CODE = "invalid_server_form_invalid_sms_code";
    public static final String ERROR_ACCOUNT_IS_ALREADY_ACTIVATED = "error_server_account_is_already_activated";

    public static final String ERROR_ACCOUNT_NEEDS_ACTIVATION = "error_server_account_needs_activation";
    public static final String ERROR_ACCOUNT_EMAIL_ALREADY_EXISTS = "error_server_email_already_exists";
    public static final String ERROR_ACCOUNT_PHONE_NUMBER_ALREADY_EXISTS = "error_server_phone_already_exists";

    public static final String INVALID_FIRST_NAME = "invalid_server_form_invalid_first_name";
    public static final String INVALID_LAST_NAME = "invalid_server_form_invalid_last_name";
    public static final String INVALID_LOGIN = "invalid_login_form_invalid";
    public static final String DUPLICATE_LOGIN ="invalid_login_form_invalid_duplicate" ;
}
