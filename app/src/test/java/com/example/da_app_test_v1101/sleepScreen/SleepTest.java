package com.example.da_app_test_v1101.sleepScreen;

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
 * Screen: Sleep
 * id screen: SC5
 * <p>
 * List ID:
 * sleep list: br.edu.uepb.nutes.ocariot:id/sleep_list
 * efficiency list and details: br.edu.uepb.nutes.ocariot:id/sleep_efficiency_tv
 * sleep chat: br.edu.uepb.nutes.ocariot:id/sleep_chart
 * sleep period: br.edu.uepb.nutes.ocariot:id/sleep_period_tv
 * sleep period start: br.edu.uepb.nutes.ocariot:id/sleep_date_start_graph_tv
 * sleep period end: br.edu.uepb.nutes.ocariot:id/sleep_date_end_graph_tv
 * box classic: br.edu.uepb.nutes.ocariot:id/box_type_classic
 * box stages: br.edu.uepb.nutes.ocariot:id/box_type_stages
 * asleep: br.edu.uepb.nutes.ocariot:id/asleep_duration_classic_tv
 * restless: br.edu.uepb.nutes.ocariot:id/restless_duration_classic_tv
 * awake: br.edu.uepb.nutes.ocariot:id/awake_duration_classic_tv
 * awake + restless: br.edu.uepb.nutes.ocariot:id/restless_awake_duration_classic_tv
 * <p>
 * TEST CASES:
 * TC039: Verify is sleep efficiency is greater than or equal zero
 * TC040: The graph is visible
 * TC041: There is no data
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
public class SleepTest {

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

    public SleepTest(
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
                {BuildConfig.USERNAME_HP, BuildConfig.PASSWORD}/*,
                {BuildConfig.USERNAME_FM, BuildConfig.PASSWORD}*/
        });
    }

    private void sleepScreen() throws InterruptedException {
        User.login(driver, this.validUsername, this.validPassword);
        Thread.sleep(7000);
        /* Children list */
        MobileElement children_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/children_list");
        /* List children */
        List<MobileElement> children = children_list.findElements(By.className("android.widget.RelativeLayout"));
        children.get(0).click();
        Thread.sleep(2000);
        /* sleep screen */
        MobileElement sleep_navigation = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/navigation_sleep");
        sleep_navigation.click();
        Thread.sleep(2000);
    }

    /* checks if it is greater than zero */
    private void testGreaterOrEqualsZero(String number) {
        float verify_number = Float.parseFloat(number);
        Assert.assertTrue(verify_number > 0);
    }

    /* taking only number from a string */
    private String takeNumber(String element) {
        return element.replaceAll("[\\D]", "");
    }

    /* test sleep data */
    private void testSleepData(String id_Element) {
        String text_element = driver.findElementById(id_Element).getText();
        String number = takeNumber(text_element);
        testGreaterOrEqualsZero(number);
    }

    /* TC039 */
    private void detailsSleepClassic() throws InterruptedException {
//      list_sleep.get(4).click();
        /* asleep */
        testSleepData("br.edu.uepb.nutes.ocariot:id/asleep_duration_classic_tv");
        /* restless */
        testSleepData("br.edu.uepb.nutes.ocariot:id/restless_duration_classic_tv");
        /* awake */
        testSleepData("br.edu.uepb.nutes.ocariot:id/awake_duration_classic_tv");
        /* awake + restless */
        testSleepData("br.edu.uepb.nutes.ocariot:id/restless_awake_duration_classic_tv");
    }

    /* TC039 */
    private void detailsSleepStages() throws InterruptedException {
//      list_sleep.get(1).click();
        /* awake */
        testSleepData("br.edu.uepb.nutes.ocariot:id/awake_duration_stages_tv");
        /* REM */
        testSleepData("br.edu.uepb.nutes.ocariot:id/rem_duration_stages_tv");
        /* Light */
        testSleepData("br.edu.uepb.nutes.ocariot:id/light_duration_stages_tv");
        /* Deep */
        testSleepData("br.edu.uepb.nutes.ocariot:id/deep_duration_stages_tv");
    }

    @SuppressWarnings("unchecked")
    @Test
    /* TC039 */
    public void detailsSleep() throws InterruptedException {
        sleepScreen();
        MobileElement sleep_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/sleep_list");
        List<MobileElement> list_sleep = sleep_list.findElements(By.className("android.widget.RelativeLayout"));
        list_sleep.get(4).click();
        Thread.sleep(2000);
        /* Efficiency */
        String efficiency = takeNumber(driver.findElementById("br.edu.uepb.nutes.ocariot:id/sleep_efficiency_tv").getText());
        testGreaterOrEqualsZero(efficiency);
        /* Sleep period */
        MobileElement sleep_period_tv = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/sleep_period_tv");
        sleep_period_tv.isDisplayed();
        /* Sleep start */
        MobileElement sleep_date_start_tv = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/sleep_date_start_graph_tv");
        sleep_date_start_tv.isDisplayed();
        /* Sleep end */
        MobileElement sleep_date_end_tv = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/sleep_date_end_graph_tv");
        sleep_date_end_tv.isDisplayed();
        /* Compares the sleep period */
        Assert.assertEquals(sleep_period_tv.getText(), sleep_date_start_tv.getText() + " - " + sleep_date_end_tv.getText());
        /* Chart */
        Assert.assertTrue(driver.findElementById("br.edu.uepb.nutes.ocariot:id/sleep_chart").isDisplayed());
        List<MobileElement> details_sleep = driver.findElements(By.id("br.edu.uepb.nutes.ocariot:id/box_type_classic"));
        if (details_sleep.isEmpty()) {
            detailsSleepStages();
        } else {
            detailsSleepClassic();
        }
    }

    @Test
    /* TC040 */
    public void graph() throws InterruptedException {
        sleepScreen();
        MobileElement sleep_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/sleep_list");
        List<MobileElement> list_sleep = sleep_list.findElements(By.className("android.widget.RelativeLayout"));
        list_sleep.get(0).click();
        Thread.sleep(2000);

        MobileElement sleep_chat = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/sleep_chart");
        Assert.assertTrue(sleep_chat.isDisplayed());

    }

    @Test
    /* TC041 */
    public void thereIsNoData() throws InterruptedException {
        sleepScreen();
    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
