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
 * Screen: Physical Activities
 * id screen: SC4
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
 * <p>
 * do not use fitbit button: br.edu.uepb.nutes.ocariot:id/do_not_login_fitbit_button
 *
 * <p>
 * TEST CASES:
 * TC032: Checks whether the activity list data matches the details
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

    private String stringNumber(String idElement) {
        MobileElement text = (MobileElement) driver.findElementById(idElement);
        return text.getText().replaceAll("[\\D]", "");
    }

    private void isgreaterThanOrEqualsZero(String textElement) {
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


    @SuppressWarnings("unchecked")
    @Test
    /* TC032 */
    public void dataList() throws InterruptedException {
        activityScreen();
        /* Takes data from the list of the first activity */
        List<MobileElement> duration_list = driver.findElements(By.id("br.edu.uepb.nutes.ocariot:id/duration_tv"));
        List<MobileElement> calories_list = driver.findElements(By.id("br.edu.uepb.nutes.ocariot:id/calories_tv"));
        List<MobileElement> distance_list = driver.findElements(By.id("br.edu.uepb.nutes.ocariot:id/distance_tv"));
        /* data list greater than zero */
        isgreaterThanOrEqualsZero(duration_list.get(0).getText());
        isgreaterThanOrEqualsZero(calories_list.get(0).getText());
        isgreaterThanOrEqualsZero(distance_list.get(0).getText());

        /* Saved to a string to use on the next screen */
        String duration = duration_list.get(0).getText();
        String calories = calories_list.get(0).getText();
        String distance = distance_list.get(0).getText();

        /* Click on the first activity */
        MobileElement activities_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/activities_list");
        List<MobileElement> list_activitites = activities_list.findElements(By.className("android.widget.RelativeLayout"));
        list_activitites.get(0).click();

        Thread.sleep(2000);
        /* Checks whether the data in the list matches the details */
        Assert.assertEquals(duration, stringNumber("br.edu.uepb.nutes.ocariot:id/activity_duration_tv"));
        Assert.assertEquals(calories, stringNumber("br.edu.uepb.nutes.ocariot:id/activity_calories_tv"));
        Assert.assertEquals(distance.replaceAll("[\\D]", ""), stringNumber("br.edu.uepb.nutes.ocariot:id/activity_distance_tv"));
    }

    @Test
    /* TC033 */
    public void greaterThanZero() throws InterruptedException {
        activityScreen();
        /* Activities list */
        MobileElement activities_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/activities_list");
        /* List activities */
        List<MobileElement> activities = activities_list.findElements(By.className("android.widget.RelativeLayout"));
        /* click fist activity */
        activities.get(0).click();

        Thread.sleep(2000);

        isgreaterThanOrEqualsZero(driver.findElementById("br.edu.uepb.nutes.ocariot:id/activity_steps_tv").getText());
        isgreaterThanOrEqualsZero(driver.findElementById("br.edu.uepb.nutes.ocariot:id/activity_calories_tv").getText());
        isgreaterThanOrEqualsZero(driver.findElementById("br.edu.uepb.nutes.ocariot:id/activity_distance_tv").getText());
        isgreaterThanOrEqualsZero(driver.findElementById("br.edu.uepb.nutes.ocariot:id/activity_calories_min_tv").getText());
    }

    /* element.isDisplayerd() */
    private void isVisible(String idElement) {
        Assert.assertTrue(driver.findElementById(idElement).isDisplayed());
    }

    @Test
    /* TC034 */
    public void levelsBarVisible() throws InterruptedException {

        /** levels bar:
         * br.edu.uepb.nutes.ocariot:id/sedentary_Level_tv
         * br.edu.uepb.nutes.ocariot:id/lightly_Level_tv
         * br.edu.uepb.nutes.ocariot:id/fairly_Level_tv
         * br.edu.uepb.nutes.ocariot:id/very_Level_tv
         **/
        activityScreen();
        /* Activities list */
        MobileElement activities_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/activities_list");
        /* List activities */
        List<MobileElement> activities = activities_list.findElements(By.className("android.widget.RelativeLayout"));
        /* click fist activity */
        activities.get(0).click();

        Thread.sleep(2000);

        isVisible("br.edu.uepb.nutes.ocariot:id/sedentary_Level_tv");
        isVisible("br.edu.uepb.nutes.ocariot:id/lightly_Level_tv");
        isVisible("br.edu.uepb.nutes.ocariot:id/fairly_Level_tv");
        isVisible("br.edu.uepb.nutes.ocariot:id/very_Level_tv");

    }

    @Test
    /* TC035 */
    public void levelsBarThanOrEqualsZero() throws InterruptedException {
        activityScreen();
        /* Activities list */
        MobileElement activities_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/activities_list");
        /* List activities */
        List<MobileElement> activities = activities_list.findElements(By.className("android.widget.RelativeLayout"));
        /* click fist activity */
        activities.get(0).click();
        Thread.sleep(2000);

        /* Check if greater than or equals to zero */
        isgreaterThanOrEqualsZero(stringNumber("br.edu.uepb.nutes.ocariot:id/sedentary_Level_value_tv"));
        isgreaterThanOrEqualsZero(stringNumber("br.edu.uepb.nutes.ocariot:id/lightly_Level_value_tv"));
        isgreaterThanOrEqualsZero(stringNumber("br.edu.uepb.nutes.ocariot:id/fairly_Level_value_tv"));
        isgreaterThanOrEqualsZero(stringNumber("br.edu.uepb.nutes.ocariot:id/very_Level_value_tv"));
    }

    @Test
    /* TC036 */
    public void thereIsNoData() throws InterruptedException {
        /* Valid login */
        User.login(driver, this.validUsername, this.validPassword);
        Thread.sleep(3000);
        /* Child list */
        MobileElement child_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/children_list");
        List<MobileElement> list_child = child_list.findElements(By.className("android.widget.RelativeLayout"));
        list_child.get(1).click();
        Thread.sleep(2000);
        /* br.edu.uepb.nutes.ocariot:id/do_not_login_fitbit_button */
        if (this.validUsername.equals(BuildConfig.USERNAME_ED) || this.validUsername.equals(BuildConfig.USERNAME_HP)) {
            /* Do not use Fitbit */
            driver.findElementById("br.edu.uepb.nutes.ocariot:id/do_not_login_fitbit_button").click();
        }
        Thread.sleep(2000);
        /* Redirect to physical activity screen */
        MobileElement box_no_data = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/box_no_data");
        Assert.assertTrue(box_no_data.isDisplayed());
    }

    @Test
    /* TC037 */
    public void iconDetailsVisibility() throws InterruptedException {
        activityScreen();
        Thread.sleep(2000);
        MobileElement children_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/children_list");
        List<MobileElement> list_children = children_list.findElements(By.className("android.widget.RelativeLayout"));
        list_children.get(0).click();

        MobileElement activity_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/activities_list");
        List<MobileElement> list_activity = activity_list.findElements(By.className("android.widget.RelativeLayout"));
        list_activity.get(0).click();
    }

    @Test
    /* TC038 */
    public void iconList() throws InterruptedException {
        activityScreen();
        MobileElement activities_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/activities_list");
        List<MobileElement> img_activity = activities_list.findElements(By.id("br.edu.uepb.nutes.ocariot:id/activity_img"));
        for (MobileElement mobileElement : img_activity) {
            Assert.assertTrue(mobileElement.isDisplayed());
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
