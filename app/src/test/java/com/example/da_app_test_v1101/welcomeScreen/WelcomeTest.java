package com.example.da_app_test_v1101.welcomeScreen;

/**
 * OCARIoT DATA ACQUISITION APP
 * version: v1.10.1
 * APK: https://github.com/ocariot/da-app/releases
 * Dashboard: https://ocariot-nutes-dashboard.firebaseapp.com/
 * https://www.ocariot.com/
 * https://www.ocariot.com.br/
 *
 * <p>
 * Tester: DIEGO MUNIZ
 * Graduanting Computer Science, UEPB.
 *
 * <p>
 * Screen: Welcome
 * id screen: SC3
 *
 * <p>
 * List ID:
 * children button: br.edu.uepb.nutes.ocariot:id/action_child
 * settings button: br.edu.uepb.nutes.ocariot:id/action_settings
 * fitbit button: br.edu.uepb.nutes.ocariot:id/fitbit_button
 * do not use fitbit: br.edu.uepb.nutes.ocariot:id/do_not_login_fitbit_button
 * toolbar: br.edu.uepb.nutes.ocariot:id/toolbar
 * action bar: br.edu.uepb.nutes.ocariot:id/action_bar
 * <p>
 * TEST CASES:
 * TC020: Redirecting to children screen
 * TC021: Redirecting to settings screen
 * TC022: Valid login Fitbit
 * TC023: Invalid login Fitbit
 * TC024: Allow all
 * TC025: Allow activity and exercise
 * TC026: Allow sleep
 * TC027: Allow heart rate
 * TC028: Allow weight
 * TC029: Redirecting to the Fitbit login page
 * TC030: Do not currently use Fitbit
 * TC031: Icons and images visibility
 **/

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
import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import okhttp3.MultipartBody;

@RunWith(Parameterized.class)
public class WelcomeTest {

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

    User user;

    public WelcomeTest(
            String validUsername,
            String validPassword
    ) {
        this.validUsername = validUsername;
        this.validPassword = validPassword;
    }

    @Before
    public void initialize() {
        user = new User();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                /*{
                    1° valid username,
                    2° valid password
                  }*/
                {BuildConfig.USERNAME_ED, BuildConfig.PASSWORD, BuildConfig.FITBIT_USERNAME, BuildConfig.FITBIT_PASSWORD},
                {BuildConfig.USERNAME_HP, BuildConfig.PASSWORD, BuildConfig.FITBIT_USERNAME, BuildConfig.FITBIT_PASSWORD}
        });
    }

    private void welcomeScreen() throws InterruptedException {
        User.login(driver, this.validUsername, this.validPassword);
        Thread.sleep(7000);
        /* Children list */
        MobileElement children_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/children_list");
        /* List children */
        List<MobileElement> children = children_list.findElements(By.className("android.widget.RelativeLayout"));
        /* List status Fitbit */
        children.get(2).click();
        Thread.sleep(2000);
    }

    @Test
    /* TC020 */
    public void childrenButton() throws InterruptedException {
        welcomeScreen();
        MobileElement children_button = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/action_child");
        children_button.click();
        Thread.sleep(2000);
        MobileElement toolbar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/toolbar");
        Assert.assertEquals("Children", toolbar.findElement(By.className("android.widget.TextView")).getText());
    }

    @Test
    /* TC021 */
    public void settingsButtons() throws InterruptedException {
        welcomeScreen();
        MobileElement settings_button = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/action_settings");
        settings_button.click();
        Thread.sleep(4000);
        MobileElement action_bar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/action_bar");
        Assert.assertEquals("Settings", action_bar.findElements(By.className("android.widget.TextView")).get(0).getText());
    }

    @Test
    /* TC022 */
    public void provideAccess_validLogin() throws InterruptedException {
        welcomeScreen();
        /* click provide Fitbit */
        MobileElement provide_fitbit = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/fitbit_button");
        provide_fitbit.click();
        /* Redirect Fitbit account*/

    }

    @Test
    /* TC023 */
    public void provideAccess_invalidLogin() {

    }

    @Test
    /* TC024 */
    public void allowAll() {

    }

    @Test
    /* TC025 */
    public void allowActivityAndExercise() {

    }

    @Test
    /* TC026 */
    public void allowSleep() {

    }

    @Test
    /* TC027 */
    public void allowHeartRate() {

    }

    @Test
    /* TC028 */
    public void allowWeight() {

    }

    @Test
    /* TC029 */
    public void fitbitLoginPage() {

    }

    @Test
    /* TC030 */
    public void doNotCurrentlyUseFitbit() throws InterruptedException {
        welcomeScreen();
        /* Do not user Fitbit */
        MobileElement do_not_fitbit = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/do_not_login_fitbit_button");
        do_not_fitbit.click();
        /* checks whether you have been redirected to the physical activity screen  */
        MobileElement toolbar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/toolbar");
        Assert.assertEquals("Physical activities", toolbar.findElements(By.className("android.widget.TextView")).get(0).getText());
    }

    @Test
    /* TC031 */
    public void iconAndImageVisibility() {

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
