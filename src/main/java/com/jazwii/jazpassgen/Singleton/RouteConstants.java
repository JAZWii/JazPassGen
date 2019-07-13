package com.jazwii.jazpassgen.Singleton;

import java.io.Serializable;

public class RouteConstants implements Serializable {
    public static final String PREFIX = "/v1";
    public static final String ANONYMOUS_PREFIX = PREFIX + "/anonymous";
    public static final String AUTHENTICATED_PREFIX = PREFIX + "/authenticated";
    public static final String ADMINISTRATOR_PREFIX = PREFIX + "/administrator";

    public static final String ANONYMOUS_CUSTOMER_ACCOUNT = ANONYMOUS_PREFIX + "/account";
}
