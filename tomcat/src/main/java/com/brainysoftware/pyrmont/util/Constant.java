package com.brainysoftware.pyrmont.util;

import java.io.File;

public interface Constant {

    String CATALINA_BASE = System.getProperty("user.dir") + File.separator + "tomcat";

    String WEB_ROOT = CATALINA_BASE + File.separator + "webapps\\context";

    String Package = "ex03.connector.http";
    int DEFAULT_CONNECTION_TIMEOUT = 60000;
    int PROCESSOR_IDLE = 0;
    int PROCESSOR_ACTIVE = 1;
}
