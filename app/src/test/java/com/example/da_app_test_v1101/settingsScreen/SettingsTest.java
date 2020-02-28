package com.example.da_app_test_v1101.settingsScreen;

/******************************************************
 *
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
 * <p>
 * Screen: Settings
 * id screen: SC7
 *
 * <p>
 * List ID:
 * android:id/list
 * android.widget.LinearLayout[2] - Fitbit
 * android.widget.LinearLayout[3] - sync
 * android.widget.LinearLayout[5] - children
 * android.widget.LinearLayout[6] - log out
 * android.widget.LinearLayout[8] - about
 *
 *
 * TEST CASES:
 * TC049 - Fitbit - valid login
 * TC050 - Fitbit - Invalid login
 * TC051 - permissions: Allow all
 * TC052 - permissions: Activity and exercise
 * TC053 - permissions: sleep
 * TC054 - permissions: heart rate
 * TC055 - permissions: weight
 * TC056 - redirecting to the Fitbit login page
 * TC057 - revoke Fitbit - test if revocation is being successful
 * TC058 - status Fitbit - Checking Fitbit status
 * TC059 - send request to update data - test updates the data
 * TC060 - send request with the device's internet turned off
 * TC061 - correct screen - test if you are redirecting to the children screen
 * T0062 - log out success - test if you are redirecting to the login screen
 * TC063 - redirecting to OCARIoT DA APP Privacy Policy
 * TC064 - Icons are visible
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
public class SettingsTest {

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
    private String fitbitUsername;
    private String fitbitPassword;

    User user;

    public SettingsTest(
            String validUsername,
            String validPassword,
            String fitbitUsername,
            String fitbitPassword
    ) {
        this.validUsername = validUsername;
        this.validPassword = validPassword;
        this.fitbitUsername = fitbitUsername;
        this.fitbitPassword = fitbitPassword;
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
                {BuildConfig.USERNAME_HP, BuildConfig.PASSWORD, BuildConfig.FITBIT_USERNAME, BuildConfig.FITBIT_PASSWORD},
                {BuildConfig.USERNAME_FM, BuildConfig.PASSWORD_FM, BuildConfig.FITBIT_USERNAME, BuildConfig.FITBIT_PASSWORD}
        });
    }

    private void settingsScreen() throws InterruptedException {
        User.login(driver, this.validUsername, this.validPassword);
        Thread.sleep(3000);
        /* Children list */
        MobileElement children_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/children_list");
        /* List children */
        List<MobileElement> children = children_list.findElements(By.className("android.widget.RelativeLayout"));
        /* List status Fitbit */
        children.get(0).click();
        Thread.sleep(1300);
        MobileElement settings_action = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/action_settings");
        settings_action.click();
        Thread.sleep(1300);
    }

    private void list(int index) {
        MobileElement settings = (MobileElement) driver.findElementById("android:id/list");
        /* List options */
        List<MobileElement> options = settings.findElements(By.className("android.widget.LinearLayout"));
        /* click */
        options.get(index).click();
    }

    /**
     * FITBIT LOGIN
     */

    @Test
    /* TC049 */
    public void validLoginFitbit() throws InterruptedException {
        this.settingsScreen();
        /*click provide Fitbit*/
        MobileElement provide_fitbit = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/fitbit_button");
        provide_fitbit.click();
        /*Redirect Fitbit account*/
        Thread.sleep(10000);
        User.login_fitbit(driver, this.fitbitUsername, this.fitbitPassword);
        Thread.sleep(7000);
        Assert.assertTrue(driver.findElementById("com.android.chrome:id/compositor_view_holder").isDisplayed());
    }

    @Test
    /* TC050 */
    public void invalidLoginFitbit() throws InterruptedException {
        this.settingsScreen();
        /*click provide Fitbit*/
        MobileElement provide_fitbit = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/fitbit_button");
        provide_fitbit.click();
        /*Redirect Fitbit account*/
        Thread.sleep(10000);
        User.login_fitbit(driver, "invalid@gmail.com", "passwordfitbit");
        Thread.sleep(7000);
        MobileElement invalid_login = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView/android.view.View/android.view.View[2]/android.view.View[3]/android.view.View/android.view.View[1]");
        Assert.assertTrue(invalid_login.isDisplayed());
    }

    /**
     * PERMISSIONS
     */
    private void access_fitbit() throws InterruptedException {
        this.settingsScreen();
        /* click provide Fitbit */
        MobileElement provide_fitbit = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/fitbit_button");
        provide_fitbit.click();
        /* Redirect Fitbit account */
        Thread.sleep(10000);
        /* login valid fitbit */
        User.login_fitbit(driver, this.fitbitUsername, this.fitbitPassword);
        Thread.sleep(7000);
    }

    private void responsePermissions(String Xpath) throws InterruptedException {
        this.access_fitbit();
        driver.findElementByXPath(Xpath).click();
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView/android.view.View[1]/android.view.View[3]/android.widget.Button[2]").click();
        Thread.sleep(10000);
        MobileElement alert = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/flAlertBackground");
        Assert.assertTrue(alert.isDisplayed());
        MobileElement alert_message = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/tvTitle");
        String message_alert = alert_message.getText();
        if (message_alert.equals("Success!")) {
            Assert.assertEquals("Success!", message_alert);
        } else {
            Assert.assertEquals("Error!", message_alert);
        }
    }

    @Test
    /* TC051 */
    public void allowAll() throws InterruptedException {
        /* allow all */
        this.responsePermissions("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView/android.view.View[1]/android.widget.ListView/android.view.View[1]/android.widget.CheckBox");
    }

    @Test
    /* TC052 */
    public void activity() throws InterruptedException {
        /* allow activity and exercise */
        this.responsePermissions("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView/android.view.View[1]/android.widget.ListView/android.view.View[2]/android.widget.CheckBox");
    }

    @Test
    /* TC053 */
    public void sleep() throws InterruptedException {
        /* allow sleep */
        this.responsePermissions("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView/android.view.View[1]/android.widget.ListView/android.view.View[3]/android.widget.CheckBox");
    }

    @Test
    /* TC054 */
    public void heartRate() throws InterruptedException {
        /* allow heart rate */
        this.responsePermissions("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView/android.view.View[1]/android.widget.ListView/android.view.View[4]/android.widget.CheckBox");
    }

    @Test
    /* TC055 */
    public void weight() throws InterruptedException {
        /* allow weight */
        this.responsePermissions("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout[1]/android.webkit.WebView/android.view.View[1]/android.widget.ListView/android.view.View[5]/android.widget.CheckBox");
    }

    @Test
    /* TC056 */
    public void fitbitPage() throws InterruptedException {
        this.settingsScreen();
        /* Redirect Fitbit account */
        Thread.sleep(10000);
        MobileElement page_fitbit = (MobileElement) driver.findElementById("com.android.chrome:id/compositor_view_holder");
        Assert.assertTrue(page_fitbit.isDisplayed());
        MobileElement url_fitbit = (MobileElement) driver.findElementById("com.android.chrome:id/url_bar");
        Assert.assertEquals("accounts.fitbit.com", url_fitbit.getText());
    }

    @Test
    /* TC057 */
    public void revokeFitbit() throws InterruptedException {
        this.settingsScreen();
        if (this.validUsername.equals(BuildConfig.USERNAME_FM)) {
            driver.quit();
        } else {
            list(1);
        }

        MobileElement yes = (MobileElement) driver.findElementById("android:id/button1");
        yes.click();

        Thread.sleep(7000);
        MobileElement alert = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/flAlertBackground");
        Assert.assertTrue(alert.isDisplayed());
        MobileElement alert_message = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/tvTitle");
        String message_alert = alert_message.getText();
        if (message_alert.equals("Success!")) {
            Assert.assertEquals("Success!", message_alert);
        } else {
            Assert.assertEquals("Error!", message_alert);
        }
    }

    @Test
    /* TC058 */
    public void statusFitbit() throws InterruptedException {
        this.settingsScreen();
        MobileElement status = (MobileElement) driver.findElementById("android:id/switch_widget");
        Assert.assertTrue(status.isDisplayed());
        String status_fitbit = status.getAttribute("checked");
        if (status_fitbit.equals("true")) {
            Assert.assertEquals("true", status_fitbit);
        } else {
            Assert.assertEquals("false", status_fitbit);
        }
    }

    @Test
    /* TC059 */
    public void sendRequestUpdate() throws InterruptedException {
        this.settingsScreen();
        if (this.validUsername.equals(BuildConfig.USERNAME_FM)) {
            list(1);
        } else {
            list(3);
        }
        Thread.sleep(1300);
        /* alert */
        MobileElement alert = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/rlContainer");
        Assert.assertTrue(alert.isDisplayed());
        /* alert text container */
        MobileElement alert_text = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/llAlertTextContainer");
        Assert.assertTrue(alert_text.isDisplayed());
        Thread.sleep(10000);
        /* Message alert */
        MobileElement message = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/tvTitle");
        if (message.getText().equals("Error!")) {
            Assert.assertEquals("Error!", message.getText());
        } else if (message.getText().equals("An error occurred while trying to regain access to Fitbit!")) {
            Assert.assertEquals("An error occurred while trying to regain access to Fitbit!", message.getText());
        } else {
            Assert.assertEquals("Success!", message.getText());
        }
    }

    @Test
    /* TC060 */
    public void sendRequestInternetOff() throws InterruptedException {
        this.settingsScreen();
        driver.toggleWifi();
        Thread.sleep(4000);
        if (this.validUsername.equals(BuildConfig.USERNAME_FM)) {
            list(0);
        } else {
            list(3);
        }
        /* alert */
        MobileElement alert = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/rlContainer");
        Assert.assertTrue(alert.isDisplayed());
        /* alert text container */
        MobileElement alert_text = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/llAlertTextContainer");
        Assert.assertTrue(alert_text.isDisplayed());
        Thread.sleep(10000);
        /* Message alert */
        MobileElement message = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/tvTitle");
        if (message.getText().equals("Connection error!")) {
            Assert.assertEquals("Connection error!", message.getText());
        } else {
            Assert.assertEquals("An error occurred while trying to regain access to Fitbit!", message.getText());
        }

        driver.toggleWifi();
        Thread.sleep(2000);
    }

    @Test
    /* TC061 */
    public void childrenScreen() throws InterruptedException {
        this.settingsScreen();
        if (this.validUsername.equals(BuildConfig.USERNAME_FM)) {
            list(1);
        } else {
            list(4);
        }
        Thread.sleep(2000);
        MobileElement toolbar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/toolbar");
        Assert.assertEquals("Children", toolbar.findElements(By.className("android.widget.TextView")).get(0).getText());
    }

    @Test
    /* TC062 */
    public void logOut() throws InterruptedException {
        this.settingsScreen();
        if (this.validUsername.equals(BuildConfig.USERNAME_FM)) {
            list(2);
        } else {
            list(5);
        }
        MobileElement logout = (MobileElement) driver.findElementById("android:id/button1");
        logout.click();
        Thread.sleep(1300);
        MobileElement loginScreen = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/action_bar_root");
        Assert.assertTrue(loginScreen.isDisplayed());

    }

    @Test
    /* TC063 */
    public void ocariotPrivacyPolicy() throws InterruptedException {
        this.settingsScreen();
        if (this.validUsername.equals(BuildConfig.USERNAME_FM)) {
            list(3);
        } else {
            list(6);
        }
        Thread.sleep(4000);
        MobileElement url_google = (MobileElement) driver.findElementById("com.android.chrome:id/url_bar");
        Assert.assertEquals("sites.google.com/view/ocariot-privacy/", url_google.getText());
    }

    @Test
    /* TC064 */
    public void iconsAreVisible() throws InterruptedException {
        this.settingsScreen();
        if (this.validUsername.equals(BuildConfig.USERNAME_FM)) {
            driver.quit();
        } else {
            MobileElement icon_fitbit = (MobileElement) driver.findElementById("android:id/icon");
            Assert.assertTrue(icon_fitbit.isDisplayed());
            MobileElement switch_fitbit = (MobileElement) driver.findElementById("android:id/switch_widget");
            Assert.assertTrue(switch_fitbit.isDisplayed());
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
