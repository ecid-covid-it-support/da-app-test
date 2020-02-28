package com.example.da_app_test_v1101;

/**
 * OCARIoT DATA ACQUISITION APP
 * version: v1.10.1
 * version: v1.10.2
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
        config.setCapability("app", "/home/diego/Downloads/app-debug.apk");
        /*URL remoteUrl = new URL(url);*/
    }
}
