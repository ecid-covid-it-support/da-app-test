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
 * List ID:
 * username: br.edu.uepb.nutes.ocariot:id/username
 * password: br.edu.uepb.nutes.ocariot:id/password
 * login button: br.edu.uepb.nutes.ocariot:id/sign_in_button
 *
 * alert title: br.edu.uepb.nutes.ocariot:id/tvTitle
 * */

import org.junit.Assert;

import io.appium.java_client.android.AndroidDriver;

public class User {

    private String validUsername;
    private String validPassword;
    private String invalidUsername;
    private String invalidPassword;
    private String caseInsensitiveUsername;
    private String caseInsensitivePassword;

    public static void login(AndroidDriver driver, String username, String password) {
        /* username */
        driver.findElementById("br.edu.uepb.nutes.ocariot:id/username").sendKeys(username);
        /* password */
        driver.findElementById("br.edu.uepb.nutes.ocariot:id/password").sendKeys(password);
        /* login button */
        driver.findElementById("br.edu.uepb.nutes.ocariot:id/sign_in_button").click();
    }

    public static void authenticationFailed(AndroidDriver driver) {
        Assert.assertEquals("Authentication failed!", driver.findElementById("br.edu.uepb.nutes.ocariot:id/tvTitle").getText());
    }

}
