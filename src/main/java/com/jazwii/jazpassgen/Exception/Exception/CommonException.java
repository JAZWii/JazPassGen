package com.jazwii.jazpassgen.Exception.Exception;

import com.fasterxml.jackson.annotation.JsonView;
import com.jazwii.jazpassgen.Singleton.MessageConstants;
import com.jazwii.jazpassgen.Singleton.RestViews;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.List;

public class CommonException extends RuntimeException {
    public enum Type {
        COMMON_EXCEPTION_INVALID_OBJECT(MessageConstants.INVALID_OBJECT),
        COMMON_EXCEPTION_INVALID_FORM(MessageConstants.INVALID_FORM),
        COMMON_EXCEPTION_LOGICAL_ERROR(MessageConstants.LOGICAL_ERROR),
        //        COMMON_EXCEPTION_UNEXPECTED_LOGICAL_ERROR(MessageConstants.LOGICAL_UNEXPECTED_ERROR),
        COMMON_EXCEPTION_UNEXPECTED_LOGICAL_ERROR(MessageConstants.LOGICAL_ERROR),
        COMMON_EXCEPTION_SERVICE_ERROR(MessageConstants.SERVICE_ERROR);

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    @JsonView(RestViews.PublicMinimal.class)
    private HttpStatus statusCode;

    @JsonView(RestViews.PublicMinimal.class)
    private Type messageType;

    @JsonView(RestViews.PublicMinimal.class)
    private String title;

    @JsonView(RestViews.PublicMinimal.class)
    private String errors[];

    public CommonException(HttpStatus statusCode, Type messageType, String[] errors) {
        super(errors != null && errors.length > 0 ? errors[0] : "");
        this.statusCode = statusCode;
        this.messageType = messageType;
        this.errors = errors;
        this.title = messageType.getValue();
    }

    public CommonException(HttpStatus statusCode, Type messageType, String error) {
        this(statusCode, messageType, new String[]{error});
    }

    public CommonException(HttpStatus statusCode, Type messageType, List<ObjectError> objectErrors) {
        this(statusCode, messageType, CommonException.getErrors(objectErrors));
    }

    public CommonException(HttpStatus statusCode, Type messageType, Errors errors) {
        this(statusCode, messageType, errors.getAllErrors());
    }

    public CommonException(HttpStatus statusCode, Type messageType, BindingResult result) {
        this(statusCode, messageType, result.getAllErrors());
    }

    public CommonException(HttpStatus statusCode, Type messageType) {
        this(statusCode, messageType, new String[0]);
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public Type getMessageType() {
        return messageType;
    }

    public void setMessageType(Type messageType) {
        this.messageType = messageType;
    }

    public String[] getErrors() {
        return errors;
    }

    public void setErrors(String[] errors) {
        this.errors = errors;
    }

    private static String[] getErrors(List<ObjectError> objectErrors){
        String[] errors = new String[objectErrors.size()];

        for (int i = 0; i < objectErrors.size(); i++)
            errors[i] = objectErrors.get(i).getDefaultMessage();

        return errors;
    }
}

