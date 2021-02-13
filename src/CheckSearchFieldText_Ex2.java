import Utils.AssertUtils;
import Utils.Locator;
import Utils.WaitUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Ex2: Создание метода (проверка что поле ввода поиска статьи содержит текст "Search Wikipedia")
 */
public class CheckSearchFieldText_Ex2 {

    private AppiumDriver driver;
    private AssertUtils assertUtils;

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

        assertUtils = new AssertUtils(new WaitUtils(driver));
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

        assertUtils.assertElementHasText(loc, expectedText, errorMessage);
    }
}

