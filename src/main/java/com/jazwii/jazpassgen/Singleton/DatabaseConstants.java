package com.jazwii.jazpassgen.Singleton;

import java.io.Serializable;

public class DatabaseConstants implements Serializable {
    public static final String TABLE_ACCOUNT = "accounts";
    public static final String TABLE_PASSWORD_RECOVERY_ATTEMPT = "password_recovery_attempts";
    public static final String TABLE_ROLE = "roles";
    public static final String TABLE_OAUTH_ACCESS_TOKEN = "oauth_access_token";
    public static final String TABLE_OAUTH_REFRESH_TOKEN = "oauth_refresh_token";
}
