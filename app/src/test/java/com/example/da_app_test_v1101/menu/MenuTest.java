package com.example.da_app_test_v1101.menu;

import com.example.da_app_test_v1101.BuildConfig;
import com.example.da_app_test_v1101.Config;
import com.example.da_app_test_v1101.User;
import com.google.common.io.LittleEndianDataInputStream;

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
 * <p>
 * toolbar: br.edu.uepb.nutes.ocariot:id/toolbar - class: android.widget.TextView
 * <p>
 * children button: br.edu.uepb.nutes.ocariot:id/action_child
 * settings button: br.edu.uepb.nutes.ocariot:id/action_settings
 * menu bar: br.edu.uepb.nutes.ocariot:id/navigation
 * physical activities: br.edu.uepb.nutes.ocariot:id/navigation_activities
 * sleep: br.edu.uepb.nutes.ocariot:id/navigation_sleep
 * IoT: br.edu.uepb.nutes.ocariot:id/navigation_iot
 * <p>
 * no data: br.edu.uepb.nutes.ocariot:id/box_no_data
 **/

@RunWith(Parameterized.class)
public class MenuTest {

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

    public MenuTest(
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


    private void menuScreen() throws InterruptedException {
        User.login(driver, this.validUsername, this.validPassword);
        Thread.sleep(7000);
        /* Children list */
        MobileElement children_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/children_list");
        /* List children */
        List<MobileElement> children = children_list.findElements(By.className("android.widget.RelativeLayout"));
        /* List status Fitbit */
        children.get(0).click();
        Thread.sleep(2000);
        MobileElement do_not_fitbit = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/do_not_login_fitbit_button");
        do_not_fitbit.click();
        Thread.sleep(2000);
    }

    @Test
    /* TC065 */
    public void childrenScreen() throws InterruptedException {
        menuScreen();
        MobileElement children_button = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/action_child");
        children_button.click();
        Thread.sleep(2000);
        MobileElement toolbar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/toolbar");
        Assert.assertEquals("Children", toolbar.findElement(By.className("android.widget.TextView")).getText());
    }

    @Test
    /* TC066 */
    public void settingsScreen() throws InterruptedException {
        menuScreen();
        MobileElement settings_button = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/action_settings");
        settings_button.click();
        Thread.sleep(4000);
        MobileElement action_bar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/action_bar");
        Assert.assertEquals("Settings", action_bar.findElements(By.className("android.widget.TextView")).get(0).getText());
    }

    @Test
    /* TC067 */
    public void correctScreenSleep() throws InterruptedException {
        menuScreen();
        MobileElement sleep = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/navigation_sleep");
        MobileElement toolbar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/toolbar");
        List<MobileElement> screen = toolbar.findElements(By.className("android.widget.TextView"));
        sleep.click();
        Assert.assertEquals( "Sleep", screen.get(0).getText());
    }

    @Test
    /* TC067 */
    public void correctScreenIot() throws InterruptedException {
        menuScreen();
        MobileElement iot = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/navigation_iot");
        MobileElement toolbar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/toolbar");
        List<MobileElement> screen = toolbar.findElements(By.className("android.widget.TextView"));
        iot.click();
        Assert.assertEquals( "IoT", screen.get(0).getText());
    }

    @Test
    /* TC067 */
    public void correctScreenPhysicalActivities() throws InterruptedException {
        menuScreen();
        MobileElement activities = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/navigation_activities");
        MobileElement toolbar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/toolbar");
        List<MobileElement> screen = toolbar.findElements(By.className("android.widget.TextView"));
        activities.click();
        Assert.assertEquals( "Physical activities", screen.get(0).getText());
    }

    @SuppressWarnings("unchecked")
    @Test
    /* TC068 */
    public void iconsVisible() throws InterruptedException {
        menuScreen();
        List<MobileElement> icons = driver.findElements(By.id("br.edu.uepb.nutes.ocariot:id/icon"));
        for(MobileElement icon: icons) {
            Assert.assertTrue(icon.isDisplayed());
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
