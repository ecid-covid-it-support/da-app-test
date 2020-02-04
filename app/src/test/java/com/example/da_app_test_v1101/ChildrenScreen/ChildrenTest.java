package com.example.da_app_test_v1101.ChildrenScreen;

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
 * Screen: Children
 * id screen: SC2
 *
 * <p>
 * List ID:
 * search button: br.edu.uepb.nutes.ocariot:id/search_button
 * action sort: br.edu.uepb.nutes.ocariot:id/action_sort
 * gender: br.edu.uepb.nutes.ocariot:id/gender_img
 * Fitbit status: br.edu.uepb.nutes.ocariot:id/fitbit_status_cb
 * <p>
 * children list: br.edu.uepb.nutes.ocariot:id/children_list
 *
 * <p>
 * TEST CASES:
 * TC011: Icon and images visibility
 * TC012: Habilitated Fitbit - Redirecting to the physical activities, sleep, weight and heart rate
 * TC013: Desabilitated Fitbit - Redirecting to the welcome screen
 * TC014: The list is sorted by children id
 * TC015: The list is sorted by synchronized
 * TC016: Correct search - Try typing in a children id
 * TC017: There is no data
 * TC018: Redirecting activities, sleep, weight and heart rate screen
 * TC019: Redirects to the correct data
 *
 */

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
import java.util.Iterator;
import java.util.List;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

@RunWith(Parameterized.class)
public class ChildrenTest {

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

    public ChildrenTest(
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

    private void childrenScreen() throws InterruptedException {
        /* Username and passsword valid */
        User.login(driver, this.validUsername, this.validPassword);
        Thread.sleep(7000);
        /* Check children screen */
        MobileElement toolbar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/toolbar");
        Assert.assertEquals("Children", toolbar.findElement(By.className("android.widget.TextView")).getText());
    }

    @Test
    /* TC011 */
    public void iconAndimagesVisivility() throws InterruptedException {
        childrenScreen();
        /* Search icon */
        Assert.assertTrue(driver.findElementById("br.edu.uepb.nutes.ocariot:id/search_button").isDisplayed());
        /* sort icon */
        Assert.assertTrue(driver.findElementById("br.edu.uepb.nutes.ocariot:id/action_sort").isDisplayed());
        /* gender icon */
        List<MobileElement> gender = driver.findElements(By.id("br.edu.uepb.nutes.ocariot:id/gender_img"));
        Iterator<MobileElement> gender_child = gender.iterator();
        while (gender_child.hasNext()) {
            Assert.assertTrue(gender_child.next().isDisplayed());
        }
        /* Fitbit status */
        List<MobileElement> status = driver.findElements(By.id("br.edu.uepb.nutes.ocariot:id/fitbit_status_cb"));
        Iterator<MobileElement> fitbit_status = status.iterator();
        while (fitbit_status.hasNext()) {
            Assert.assertTrue(fitbit_status.next().isDisplayed());
        }
    }

    @Test
    /* TC012, TC013, TC018 and TC019 */
    public void habilitatedFitbit() throws InterruptedException {
        childrenScreen();
        /* Children list */
        MobileElement children_list = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/children_list");
        /* List children */
        List<MobileElement> children = children_list.findElements(By.className("android.widget.RelativeLayout"));
        /* List status Fitbit */
        List<MobileElement> fitbit_status = driver.findElements(By.id("br.edu.uepb.nutes.ocariot:id/fitbit_status_cb"));

        List<MobileElement> name_child = driver.findElements(By.id("br.edu.uepb.nutes.ocariot:id/name_child"));
        String nameChild = name_child.get(0).getText();

        if (this.validUsername.equals(BuildConfig.USERNAME_FM)) {
            /* */
            children.get(0).click();
            Thread.sleep(2000);
            MobileElement toolbar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/toolbar");
            Assert.assertEquals("Physical activities", toolbar.findElements(By.className("android.widget.TextView")).get(0).getText());

        } else if (fitbit_status.get(0).getAttribute("checked").equals("true")) {
            children.get(0).click();
            Thread.sleep(2000);
            MobileElement toolbar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/toolbar");
            Assert.assertEquals("Physical activities", toolbar.findElements(By.className("android.widget.TextView")).get(0).getText());
        } else {
            children.get(0).click();
            Thread.sleep(2000);
            MobileElement toolbar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/toolbar");
            Assert.assertEquals("OCARIoT DA", toolbar.findElements(By.className("android.widget.TextView")).get(0).getText());
        }
        MobileElement toolbar = (MobileElement) driver.findElementById("br.edu.uepb.nutes.ocariot:id/toolbar");
        Assert.assertEquals(nameChild, toolbar.findElements(By.className("android.widget.TextView")).get(1).getText());
    }

    @Test
    /* TC013 */
    public void desabilitatedFitbit() {

    }

    @Test
    /* TC014 */
    public void sortUsername() {

    }

    @Test
    /* TC015 */
    public void sortSynchronized() {

    }

    @Test
    /* TC016 */
    public void searchButton() {

    }

    @Test
    /* TC017 */
    public void noData() {

    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
