package com.example.da_app_test_v1101.iotScreen;

/*******************************************************
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
 * Screen: IoT
 * id screen: SC6
 *
 * List ID:
 * No data: br.edu.uepb.nutes.ocariot:id/box_no_data
 * Alert enable bluetooth: br.edu.uepb.nutes.ocariot:id/alert_enable_bluetooth
 * allow bluetooth: android:id/button1
 * heart rate: br.edu.uepb.nutes.ocariot:id/hr_tv
 * heart rate min: br.edu.uepb.nutes.ocariot:id/hr_min_tv
 * heart rate max: br.edu.uepb.nutes.ocariot:id/hr_max_tv
 * heart rate average: br.edu.uepb.nutes.ocariot:id/hr_avg_tv
 * heart rate img: br.edu.uepb.nutes.ocariot:id/hr_img
 * heart rate chat: br.edu.uepb.nutes.ocariot:id/hr_chart
 *
 * TEST CASES:
 * TC042: Verify if is greater than or equal zero
 * TC043: Verify if heart rate greater than zero
 * TC044: Graph - Verify if the graph the minimum, maximum and average heart rate greater than zero
 * TC045: There is no data - IoT
 * TC046: Disable Bluetooth - Display show alert message if Bluetooth off
 * TC047: Enable Bluetooth - Clicking alert message enable bluetooth
 * TC048: Graph visibility
 *
 *
 ******************************************************/

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
public class IotTest {
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

    public IotTest(
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
                {BuildConfig.USERNAME_FM, BuildConfig.PASSWORD_FM}
        });
    }

    private void iotScreen() throws InterruptedException {
        User.login(driver, this.validUsername, this.validPassword);
        Thread.sleep(3000);
        /* Children list */
        MobileElement children_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/children_list");
        /* List children */
        List<MobileElement> children = children_list.findElements(By.className("android.widget.RelativeLayout"));
        children.get(1).click();
        Thread.sleep(2000);
        if (!this.validUsername.equals(BuildConfig.USERNAME_FM)) {
            /* Do not currently use Fitbit */
            MobileElement do_not_fitbit = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/do_not_login_fitbit_button");
            do_not_fitbit.click();
        }
        /* Sleep screen */
        MobileElement sleep_navigation = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/navigation_iot");
        sleep_navigation.click();
    }

    @Test
    /* TC042 */
    public void isGreaterOrEqualZero() throws InterruptedException {
        iotScreen();
    }

    @Test
    /* TC043 */
    public void heartRateData() throws InterruptedException {
        iotScreen();
    }

    @Test
    /* TC044 */
    public void MinMaxAverageHeartRate() throws InterruptedException {
        iotScreen();
    }

    @Test
    /* TC045 */
    public void thereIsNoData() throws InterruptedException {
        iotScreen();

        MobileElement there_is_no_data = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/box_no_data");
        Assert.assertTrue(there_is_no_data.isDisplayed());

    }

    @Test
    /* TC046 */
    public void disableBluetooth() throws InterruptedException {
        iotScreen();
        MobileElement alert_bluetooth = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/alert_enable_bluetooth");
        Assert.assertTrue(alert_bluetooth.isDisplayed());
    }

    @Test
    /* TC047 */
    public void enableBluetooth() throws InterruptedException {
        iotScreen();
        MobileElement alert_bluetooth = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/alert_enable_bluetooth");
        Assert.assertTrue(alert_bluetooth.isDisplayed());
        alert_bluetooth.click();
        Thread.sleep(2000);
        MobileElement allow_bluetooth = (MobileElement) driver.findElementById("android:id/button1");
        allow_bluetooth.click();
        Thread.sleep(2000);
        /*MobileElement allow_location = (MobileElement) driver.findElementById("android:id/button1");
        allow_location.click();*/
        Thread.sleep(5000);
        Assert.assertFalse(alert_bluetooth.isDisplayed());
    }

    @Test
    /* TC048 */
    public void graphVisibility() throws InterruptedException {
        iotScreen();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
