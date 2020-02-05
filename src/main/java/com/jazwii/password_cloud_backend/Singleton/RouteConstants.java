package com.jazwii.password_cloud_backend.Singleton;

import java.io.Serializable;

public class RouteConstants implements Serializable {
    public static final String PREFIX = "/v1";
    public static final String ANONYMOUS_PREFIX = PREFIX + "/anonymous";
    public static final String AUTHENTICATED_PREFIX = PREFIX + "/authenticated";
    public static final String ADMINISTRATOR_PREFIX = PREFIX + "/administrator";

    public static final String ANONYMOUS_CUSTOMER_ACCOUNT = ANONYMOUS_PREFIX + "/account";

    public static final String AUTHENTICATED_LOGIN = AUTHENTICATED_PREFIX + "/login";
    public static final String AUTHENTICATED_ADDRESS = AUTHENTICATED_PREFIX + "/address";

    public static final String ADMINISTRATOR_LOGIN = ADMINISTRATOR_PREFIX + "/login";
    public static final String ADMINISTRATOR_ADDRESS = ADMINISTRATOR_PREFIX + "/address";
}
