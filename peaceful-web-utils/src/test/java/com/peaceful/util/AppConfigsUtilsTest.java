package com.peaceful.common.util;

import com.peaceful.common.util.impl.AppConfigsUtils;
import org.junit.Test;

public class AppConfigsUtilsTest {

    @Test
    public void testGetMyAppConfigs() throws Exception {

        AppConfigs myAppConfigs = AppConfigsUtils.getMyAppConfigs("auth.properties");
        Util.report(myAppConfigs.getString("auth.app.id"));


    }
}