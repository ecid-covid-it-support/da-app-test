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
 * list activities: br.edu.uepb.nutes.ocariot:id/activities_list
 * list duration: br.edu.uepb.nutes.ocariot:id/duration_tv
 * list calories: br.edu.uepb.nutes.ocariot:id/calories_tv
 * list distance: 	br.edu.uepb.nutes.ocariot:id/distance_tv
 * icon activities: br.edu.uepb.nutes.ocariot:id/activity_img
 * <p>
 * clock img: br.edu.uepb.nutes.ocariot:id/clock_img
 * details duration: br.edu.uepb.nutes.ocariot:id/activity_duration_tv
 * details steps: br.edu.uepb.nutes.ocariot:id/activity_steps_tv
 * details calories: br.edu.uepb.nutes.ocariot:id/activity_calories_tv
 * details distance: br.edu.uepb.nutes.ocariot:id/activity_distance_tv
 * details cal/min: br.edu.uepb.nutes.ocariot:id/activity_calories_min_tv
 * <p>
 * action box levels: br.edu.uepb.nutes.ocariot:id/activity_box_levels
 * metrics box:
 * br.edu.uepb.nutes.ocariot:id/box_metrics_1
 * br.edu.uepb.nutes.ocariot:id/box_metrics_2
 * <p>
 * levels bar:
 * br.edu.uepb.nutes.ocariot:id/sedentary_Level_tv
 * br.edu.uepb.nutes.ocariot:id/lightly_Level_tv
 * br.edu.uepb.nutes.ocariot:id/fairly_Level_tv
 * br.edu.uepb.nutes.ocariot:id/very_Level_tv
 * <p>
 * levels value:
 * br.edu.uepb.nutes.ocariot:id/sedentary_Level_value_tv
 * br.edu.uepb.nutes.ocariot:id/lightly_Level_value_tv
 * br.edu.uepb.nutes.ocariot:id/fairly_Level_value_tv
 * br.edu.uepb.nutes.ocariot:id/very_Level_value_tv
 *
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

    private void stringForNumber(String idElement) {
        MobileElement text = (MobileElement) driver.findElementById(idElement);
        String nbr = text.getText().replaceAll("[\\D]", "");
        float number = Float.parseFloat(nbr);
        Assert.assertTrue(number >= 0);
    }

    private void isgreaterThanZero(String textElement) {
        float number = Float.parseFloat(textElement);
        Assert.assertTrue(number >= 0);
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
    public void dataList() throws InterruptedException {
        activityScreen();

        MobileElement duration_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/duration_tv");
        MobileElement calories_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/calories_tv");
        MobileElement distance_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/distance_tv");
        /* data list greater than zero */
        isgreaterThanZero(duration_list.getText());
        isgreaterThanZero(calories_list.getText());
        isgreaterThanZero(distance_list.getText());

        /* Activities list */
        MobileElement activities_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/activities_list");
        /* List activities */
        List<MobileElement> activities = activities_list.findElements(By.className("android.widget.RelativeLayout"));
        /* click fist activity */
        activities.get(0).click();

        Thread.sleep(2000);

        stringForNumber("br.edu.uepb.nutes.ocariot:id/activity_duration_tv");

        isgreaterThanZero(driver.findElementById("br.edu.uepb.nutes.ocariot:id/activity_steps_tv").getAttribute("text"));
        isgreaterThanZero(driver.findElementById("br.edu.uepb.nutes.ocariot:id/activity_calories_tv").getAttribute("text"));
        isgreaterThanZero(driver.findElementById("br.edu.uepb.nutes.ocariot:id/activity_distance_tv").getAttribute("text"));
        isgreaterThanZero(driver.findElementById("br.edu.uepb.nutes.ocariot:id/activity_calories_min_tv").getAttribute("text"));


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
