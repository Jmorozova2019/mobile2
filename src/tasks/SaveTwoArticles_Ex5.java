package tasks;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

import lib.utils.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;


public class SaveTwoArticles_Ex5
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
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testSaveTwoArticleAndDeleteOne() throws InterruptedException {
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

        waitUtils.waitForElementPresent(
            new Locator("id", "org.wikipedia:id/view_page_title_text"),
            "Cannot find article title",
            15
        );

        safeAction.click(
            new Locator("//android.widget.ImageView[@content-desc='More options']"),
            "Cannot find button to open article options"
        );

        //чтобы не открывалось окно смены языка слип тоже успешно решает эту проблему
        waitUtils.waitForElementNotPresent(
            new Locator("//*[@resource-id='org.wikipedia:id/search_lang_button']"),
            "Find button change language"
        );

        safeAction.click(
            new Locator("xpath", "//*[@text='Add to reading list']"),
            "Cannot find option to add article to reading list"
        );

        safeAction.click(
            new Locator("id", "org.wikipedia:id/onboarding_button"),
            "Cannot find 'GOT IT' tip overlay"
        );

        safeAction.clear(
            new Locator("id", "org.wikipedia:id/text_input"),
            "Cannot find input to set name of articles folder"
        );

        String name_of_folder = "Learning programming";

        safeAction.sendKeysWithoutPaste(
            new Locator("id", "org.wikipedia:id/text_input"),
            "Cannot put text into articles folder input",
            name_of_folder
        );

        safeAction.click(
            new Locator("//*[@text='OK']"),
            "Cannot press OK button"
        );//add first article
//------------------------------------
        safeAction.click(
            new Locator("//android.widget.ImageButton[@content-desc='Navigate up']"),
            "Cannot close article, cannot find X link"
        );
//----
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
            new Locator("//*[@text='Programming language']"),
            "Cannot find article 'Programming language'"
        );

        waitUtils.waitForElementPresent(
            new Locator("id", "org.wikipedia:id/view_page_title_text"),
            "Cannot find article title",
            15
        );

        safeAction.click(
            new Locator("//android.widget.ImageView[@content-desc='More options']"),
            "Cannot find button to open article options"
        );

        //чтобы не открывалось окно смены языка
        waitUtils.waitForElementNotPresent(
            new Locator("//*[@resource-id='org.wikipedia:id/search_lang_button']"),
            "Find button change language"
        );

        safeAction.click(
            new Locator("xpath", "//*[@text='Add to reading list']"),
            "Cannot find option to add article to reading list"
        );

        safeAction.click(
            new Locator("//*[@resource-id='org.wikipedia:id/item_title' and @text='" + name_of_folder +"']"),
            "Cannot folder " + name_of_folder + " to list folders"
        );
//------------
        safeAction.click(
            new Locator("//android.widget.ImageButton[@content-desc='Navigate up']"),
            "Cannot close article, cannot find X link"
        );

        safeAction.click(
            new Locator("//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView"),
            "Cannot find navigation button to My list"
        );

        safeAction.click(
            new Locator("//*[@text='" + name_of_folder + "']/../preceding-sibling::android.widget.LinearLayout"),
            "Cannot find created folder"
        );

        swipe.swipeElementToLeft(
            new Locator("//*[@text='Java (programming language)']"),
            "Cannot find saved article"
        );

        waitUtils.waitForElementNotPresent(
            new Locator("//*[@text='Java (programming language)']"),
            "Cannot delete saved article"
        );

        safeAction.click(
            new Locator("//*[@text='JavaScript']"),
            "Cannot find saved article"
        );

        checker.assertElementHasText(
            new Locator("//*[@resource-id='org.wikipedia:id/view_page_title_text']"),
            "JavaScript",
            "Cannot find Javascript article"
        );
    }
}
