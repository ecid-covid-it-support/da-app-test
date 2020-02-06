package com.example.da_app_test_v1101.physicalActivitiesScreen;

/**
 * OCARIoT DATA ACQUISITION APP
 * version: v1.10.1
 * APK: https://github.com/ocariot/da-app/releases
 * Dashboard: https://ocariot-nutes-dashboard.firebaseapp.com/
 * https://www.ocariot.com/
 * https://www.ocariot.com.br/
 * <p>
 * Tester: DIEGO MUNIZ
 * Graduanting Computer Science, UEPB.
 * <p>
 * Screen: Welcome
 * id screen: SC3
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
 * TC032: Checks wheter the activity list data matches the details
 * TC033: Verify if data is greater than zero
 * TC034: levels bar is visible
 * TC035: levels bar is greater than zero
 * TC036: There is no data
 * TC037: Icons details activities visibility
 * TC038: Icons activities list visibility
 **/

import com.example.da_app_test_v1101.BuildConfig;
import com.example.da_app_test_v1101.Config;
import com.example.da_app_test_v1101.User;

import org.junit.After;
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

@RunWith(Parameterized.class)
public class PhysicalActivitiesTest {

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

    public PhysicalActivitiesTest(
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
                {BuildConfig.USERNAME_ED, BuildConfig.PASSWORD},
                {BuildConfig.USERNAME_HP, BuildConfig.PASSWORD},
                {BuildConfig.USERNAME_FM, BuildConfig.PASSWORD}
        });
    }

    private void activityScreen() throws InterruptedException {
        User.login(driver, this.validUsername, this.validPassword);
        Thread.sleep(7000);
        /* Children list */
        MobileElement children_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/children_list");
        /* List children */
        List<MobileElement> children = children_list.findElements(By.className("android.widget.RelativeLayout"));
        /* List status Fitbit */
        children.get(0).click();
        Thread.sleep(2000);
    }

    @Test
    /* TC032 */
    public void dataList() {

    }

    @Test
    /* TC033 */
    public void greaterThanZero() {

    }

    @Test
    /* TC034 */
    public void levelsBarVisible() {

    }

    @Test
    /* TC035 */
    public void levelsBarThanZero() {

    }

    @Test
    /* TC036 */
    public void thereIsNoData() {

    }

    @Test
    /* TC037 */
    public void iconDetailsVisibility() {

    }

    @Test
    /* TC038 */
    public void iconList() {

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
