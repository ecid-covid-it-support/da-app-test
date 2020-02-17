package com.example.da_app_test_v1101.loginScreen;

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
 * Screen: Login
 * id screen: SC1
 *
 * List ID:
 * brazil radio button: br.edu.uepb.nutes.ocariot:id/brazil_radioButton
 * europe radio button: br.edu.uepb.nutes.ocariot:id/europe_radioButton
 * toolbar: br.edu.uepb.nutes.ocariot:id/toolbar
 * password visibility: br.edu.uepb.nutes.ocariot:id/text_input_password_toggle
 *
 * TEST CASES:
 * TC001: Valid login
 * TC002: Access app with case-insensitive username and password valid
 * TC003: Access app with case-insensitive password and username valid
 * TC004: Valid username and invalid password
 * TC005: invalid username and valid password
 * TC006: Device internet turned off
 * TC007: Login with a valid Brazilian user on the European pilot
 * TC008: Username and password empty field
 * TC009: Password visibility
 * TC010: Icon and images visibility
 *
 */

import com.example.da_app_test_v1101.BuildConfig;
import com.example.da_app_test_v1101.Config;
import com.example.da_app_test_v1101.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

@RunWith(Parameterized.class)
public class LoginTest {

    private AndroidDriver driver;
    private DesiredCapabilities dc = new DesiredCapabilities();

    @Before
    public void setUp() throws MalformedURLException {
        Config.configAppium(dc);
        URL remoteUrl = new URL(Config.url);
        driver = new AndroidDriver(remoteUrl, dc);
    }

    private String validUsername;
    private String validPassword;
    private String invalidUsername;
    private String invalidPassword;
    private String caseInsensitiveUsername;
    private String caseInsensitivePassword;

    User user;

    public LoginTest(
            String validUsername,
            String validPassword,
            String invalidUsername,
            String invalidPassword,
            String caseInsensitiveUsername,
            String caseInsensitivePassword
    ) {
        this.validUsername = validUsername;
        this.validPassword = validPassword;
        this.invalidUsername = invalidUsername;
        this.invalidPassword = invalidPassword;
        this.caseInsensitiveUsername = caseInsensitiveUsername;
        this.caseInsensitivePassword = caseInsensitivePassword;
    }

    @Before
    public void initialize() {
        user = new User();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                /*
                  {
                  1° valid username,
                  2° valid password,
                  3° invalid username,
                  4° invalid password
                  5° invalid case insensitive username,
                  6° invalid case insensitive password,
                  }
                  */
                {BuildConfig.USERNAME_ED, BuildConfig.PASSWORD, "invalidUsernameED", "12345", BuildConfig.CI_USERNAME_ED, BuildConfig.CI_PASSWORD},
                {BuildConfig.USERNAME_HP, BuildConfig.PASSWORD, "invalidUsernameHP", "12345", BuildConfig.CI_USERNAME_HP, BuildConfig.CI_PASSWORD},
                {BuildConfig.USERNAME_FM, BuildConfig.PASSWORD, "invalidUsernameFM", "12345", BuildConfig.CI_USERNAME_FM, BuildConfig.CI_PASSWORD}
        });
    }

    private void brazilianPilot() {
        MobileElement brazilPilot = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/brazil_radioButton");
        Assert.assertEquals("true", brazilPilot.getAttribute("checked"));
    }

    @Test
    /* TC001 */
    public void validLogin() throws InterruptedException {
        /* Check the pilot */
        brazilianPilot();
        /* Username and password valid */
        User.login(driver, this.validUsername, this.validPassword);
        Thread.sleep(7000);
        /* Check children screen */
        MobileElement toolbar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/toolbar");
        Assert.assertEquals("Children", toolbar.findElement(By.className("android.widget.TextView")).getText());
    }

    @Test
    /* TC002 */
    public void ciUsername() {
        /* Check the pilot */
        brazilianPilot();
        /* Case-insensitive username and password valid */
        User.login(driver, this.caseInsensitiveUsername, this.validPassword);
        /* Message alert */
        User.authenticationFailed(driver);
    }

    @Test
    /* TC003 */
    public void ciPassword() {
        /* Check the pilot */
        brazilianPilot();
        /* Case-insensitive password and username valid */
        User.login(driver, this.validUsername, this.caseInsensitivePassword);
        /* Message alert */
        User.authenticationFailed(driver);
    }

    @Test
    /* TC004 */
    public void invalidPassword() {
        /* Check the pilot */
        brazilianPilot();
        /* valid username and invalid password */
        User.login(driver, this.validUsername, this.invalidPassword);
        /* Message alert */
        User.authenticationFailed(driver);
    }

    @Test
    /* TC005 */
    public void invalidUsername() {
        /* Check the pilot */
        brazilianPilot();
        /* Invalid username and valid password */
        User.login(driver, this.invalidUsername, this.validPassword);
        /* Message alert */
        User.authenticationFailed(driver);
    }

    @Test
    /* TC006 */
    public void internetOff() throws InterruptedException {
        /* Disconnects the internet from the device */
        driver.toggleWifi();
        Thread.sleep(5000);
        /* Check the pilot */
        brazilianPilot();
        /* username and password valid */
        User.login(driver, this.validUsername, this.validPassword);
        Assert.assertEquals("Connection error!", driver.findElementById("br.edu.uepb.nutes.ocariot:id/tvTitle").getText());
        /* Connects the internet from the device */
        driver.toggleWifi();
        Thread.sleep(5000);
    }

    @Test
    /* TC007 */
    public void userBRonEUpilot() {
        /* Choose europe pilot */
        MobileElement europe_radioButton = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/europe_radioButton");
        europe_radioButton.click();
        Assert.assertEquals("true", europe_radioButton.getAttribute("checked"));
        Assert.assertEquals("European pilot", driver.findElementById("br.edu.uepb.nutes.ocariot:id/locale_hint_text").getAttribute("text"));
        /* Brazilian user valid */
        User.login(driver, this.validUsername, this.validPassword);
        User.authenticationFailed(driver);
    }

    @Test
    /* TC008 */
    public void emptyField() {

    }

    @Test
    /* TC009 */
    public void passwordVisibility() throws InterruptedException {
        MobileElement password_visibility = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/text_input_password_toggle");
        Assert.assertEquals("false", password_visibility.getAttribute("checked"));
        password_visibility.click();
        driver.findElementById("br.edu.uepb.nutes.ocariot:id/password").sendKeys("visiblePassword");
        Assert.assertEquals("true", password_visibility.getAttribute("checked"));
        Thread.sleep(4000);
        password_visibility.click();
        Assert.assertEquals("false", password_visibility.getAttribute("checked"));
        Thread.sleep(3000);
    }

    @Test
    /* TC010 */
    public void iconAndimagesVisivility() {
        /* OCARIoT logo */
        Assert.assertTrue(driver.findElementById("br.edu.uepb.nutes.ocariot:id/logo").isDisplayed());
        /* Brazil icon */
        Assert.assertTrue(driver.findElementById("br.edu.uepb.nutes.ocariot:id/brazil_radioButton").isDisplayed());
        /* Europe icon */
        Assert.assertTrue(driver.findElementById("br.edu.uepb.nutes.ocariot:id/europe_radioButton").isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
