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
 *
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
 * TC028: Aloow weight
 * TC029: Redirecting to the Fitbit login page
 * TC030: Do not currently use Fitbit
 * TC031: Icons and images visibility
 **/

import com.example.da_app_test_v1101.BuildConfig;
import com.example.da_app_test_v1101.Config;
import com.example.da_app_test_v1101.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import io.appium.java_client.android.AndroidDriver;

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
                {BuildConfig.USERNAME_ED, BuildConfig.PASSWORD},
                {BuildConfig.USERNAME_HP, BuildConfig.PASSWORD}
        });
    }

    private void welcomeScreen() {

    }

    @Test
    /* TC020 */
    public void childrenButton() {

    }

    @Test
    /* TC021 */
    public void settingsButtons() {

    }

    @Test
    /* TC022 */
    public void provideAccess() {

    }

    @Test
    /* TC023 */
    public void denyAccess() {

    }

    @Test
    /* TC024 */
    public void iconAndImageVisibility() {

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}