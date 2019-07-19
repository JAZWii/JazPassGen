package com.jazwii.jazpassgen.Singleton;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class ConfigConstants implements Serializable {
    public static int RECOVER_PASSWORD_CODE_LENGTH;
    public static int RECOVER_PASSWORD_CODE_MAX_TRIALS;
    public static int RECOVER_PASSWORD_CODE_VALID_HOURS;
    public static String GOOGLE_MAPS_KEY;
    public static String UPLOAD_DIRECTORY;
    public static String TESTS_DIRECTORY;
    public static String TEMPLATES_DIRECTORY;
    public static boolean DEBUG_MODE = false;

    public static String RECOVER_PASSWORD_CODE_CHARACTERSET;
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Value("${jazpassgen.recover.settings.verification.email.recover.password.length:8}")
    public void setRecoverPasswordCodeLength(int value) {
        RECOVER_PASSWORD_CODE_LENGTH = value;
    }

    @Value("${jazpassgen.recover.settings.verification.email.recover.password.max:3}")
    public void setRecoverPasswordCodeMaxTrials(int value) {
        RECOVER_PASSWORD_CODE_MAX_TRIALS = value;
    }

    @Value("${jazpassgen.recover.settings.verification.email.recover.password.valid:48}")
    public void setRecoverPasswordCodeValidHours(int value) {
        RECOVER_PASSWORD_CODE_VALID_HOURS = value;
    }

    @Value("${jazpassgen.recover.settings.verification.email.recover.password.characterset}")
    public void setRecoverPasswordCodeCharacterset(String value) {
        RECOVER_PASSWORD_CODE_CHARACTERSET = value;
    }

    @Value("${jazpassgen.google.maps.key:ab}")
    public void setGoogleMapsKey(String value) {
        GOOGLE_MAPS_KEY = value;
    }
/*
    @Value("${jazpassgen.upload.settings.directory}")
    public void setUploadDirectory(String value) {
        UPLOAD_DIRECTORY = value;
    }

    @Value("${jazpassgen.tests.settings.directory}")
    public void setTestsDirectory(String value) {
        TESTS_DIRECTORY = value;
    }

    @Value("${jazpassgen.settings.excel.templates.directory}")
    public void setTemplatesDirectory(String value) {
        TEMPLATES_DIRECTORY = value;
    }

    @Value("${jazpassgen.settings.debug:false}")
    public void setDebugMode(boolean value) {
        DEBUG_MODE = value;
    }
 */
}
