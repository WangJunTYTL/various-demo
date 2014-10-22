package com.peaceful.util;

import com.peaceful.util.impl.AppConfigsUtils;
import org.junit.Test;

public class AppConfigsUtilsTest {

    @Test
    public void testGetMyAppConfigs() throws Exception {

        AppConfigs myAppConfigs = AppConfigsUtils.getMyAppConfigs("auth.properties");
        Util.report(myAppConfigs.getString("auth.app.id"));


    }
}