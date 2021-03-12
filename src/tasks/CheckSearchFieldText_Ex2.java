package tasks;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import lib.utils.Checker;
import lib.utils.Locator;
import lib.utils.WaitUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

/**
 * Ex2: Создание метода (проверка что поле ввода поиска статьи содержит текст "Search Wikipedia")
 */
public class CheckSearchFieldText_Ex2 {

    private AppiumDriver driver;
    private Checker checker;

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

        checker = new Checker(new WaitUtils(driver));
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void fieldSearchContainsSearch_Ex2()
    {
        String search_field_xpath = "//*[contains(@resource-id, 'search_container')]/android.widget.TextView";
        Locator loc = new Locator("xpath", search_field_xpath);

        String expectedText = "Search Wikipedia";
        String errorMessage = "Cannot find search input";

        checker.assertElementHasText(loc, expectedText, errorMessage);
    }
}

