package tasks;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.utils.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CheckRotate_Ex7
{
    private AppiumDriver driver;
    private Checker checker;
    private WaitUtils waitUtils;
    private SafeAction safeAction;
    private Swipe swipe;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Nexus6");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app",
                "/Users/Zhanna/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        waitUtils = new WaitUtils(driver);
        checker = new Checker(waitUtils);
        safeAction = new SafeAction(waitUtils);
        swipe = new Swipe(driver, waitUtils);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void rotaiteToPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    @Test
    public void testCheckRotate_1()
    {
        safeAction.click(
            new Locator("//*[contains(@text,'Search Wikipedia')]"),
            "Cannot find 'Search Wikipedia' input"
        );

        safeAction.sendKeys(
            new Locator("//*[contains(@text,'Search…')]"),
            "Java",
            "Cannot find Search input",
            5
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);
    }


}
