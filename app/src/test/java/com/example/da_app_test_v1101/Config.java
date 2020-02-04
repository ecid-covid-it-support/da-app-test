package com.example.da_app_test_v1101;

/**
 * OCARIoT DATA ACQUISITION APP
 * version: v1.10.1
 * APK: https://github.com/ocariot/da-app/releases
 * Dashboard: https://ocariot-nutes-dashboard.firebaseapp.com/
 * https://www.ocariot.com/
 * https://www.ocariot.com.br/
 *
 * Tester: DIEGO MUNIZ
 * Graduanting Computer Science, UEPB.
 *
 * APPIUM SETTINGS
 *
 * */


import org.openqa.selenium.remote.DesiredCapabilities;

public class Config {
    /* APPIUM Configuration */

    public static String url = "http://localhost:4723/wd/hub";

    public static void configAppium(DesiredCapabilities config) {
        /* Android emulator or device */
        config.setCapability("deviceName", "0040766769");
        /* Test platform */
        config.setCapability("platformName", "Android");
        /* Windows */
//        config.setCapability("app", "C:\\Users\\diego\\Downloads\\ocariot_da_1.9.4_debug.apk");
        /* Linux */
        config.setCapability("app", "/home/diego/Documentos/OCARIoT_APK/OCARIoT_DA_APP_v.1.10.1/ocariot_da_v1.10.1-debug.apk");
        /*URL remoteUrl = new URL(url);*/
    }
}
