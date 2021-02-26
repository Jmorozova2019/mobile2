import Utils.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class AssertTitle_Ex6
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

    @Test
    public void testAssertElementPresent()
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

        safeAction.click(
            new Locator("//*[@text='Object-oriented programming language']"),
            "Cannot find article 'Object-oriented programming language'"
        );
        //считаем что статья открыта, если есть кнопка Найти (лупа)
        waitUtils.waitForElementPresent(
            new Locator("//*[@resource-id='org.wikipedia:id/menu_page_search']"),
            "Cannot open article"
        );

        checker.assertElementsPresentNowByXpath(
            "//*[contains(@resource-id, 'view_page_title_text')]",
            "In the article not found title"
        );
    }
}
