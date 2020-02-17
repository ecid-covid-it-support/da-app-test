package com.example.da_app_test_v1101.settingsScreen;

/******************************************************
 *
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
 * <p>
 * Screen: Settings
 * id screen: SC7
 *
 * <p>
 * List ID:
 * android:id/list
 * android.widget.LinearLayout[1] - Firbit
 * android.widget.LinearLayout[2] - sync
 * android.widget.LinearLayout[4] - children
 * android.widget.LinearLayout[5] - log out
 * android.widget.LinearLayout[7] - about
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

    User user;

    public SettingsTest(
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

    private void settingsScreen() throws InterruptedException {
        User.login(driver, this.validUsername, this.validPassword);
        Thread.sleep(3000);
        /* Children list */
        MobileElement children_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/children_list");
        /* List children */
        List<MobileElement> children = children_list.findElements(By.className("android.widget.RelativeLayout"));
        /* List status Fitbit */
        children.get(0).click();
        Thread.sleep(2000);
        MobileElement settings_action = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/action_settings");
        settings_action.click();
        Thread.sleep(2000);
    }



    @Test
    /* TC049 */
    public void validLoginFitbit() {

    }

    @Test
    /* TC050 */
    public void invalidLoginFitbit() {

    }

    /**
     * PERMISSIONS
     * */

    @Test
    /* TC051 */
    public void allowAll() {

    }

    @Test
    /* TC052 */
    public void activity() {

    }

    @Test
    /* TC053 */
    public void sleep() {

    }

    @Test
    /* TC054 */
    public void heartRate(){

    }

    @Test
    /* TC055 */
    public void weight(){

    }

    @Test
    /* TC056 */
    public void fitbitPage(){

    }

    @Test
    /* TC057 */
    public void revokeFitbit(){

    }

    @Test
    /* TC058 */
    public void statusFitbit(){

    }

    @Test
    /* TC059 */
    public void  sendRequestUpdate() {

    }

    @Test
    /* TC060 */
    public void sendRequestInternetOff() {

    }

    @Test
    /* TC061 */
    public void childrenScreen() {

    }

    @Test
    /* TC062 */
    public void logOut() {

    }

    @Test
    /* TC063 */
    public void ocariotPrivacyPolicy() {

    }

    @Test
    /* TC064 */
    public void iconsAreVisible() {

    }

}
