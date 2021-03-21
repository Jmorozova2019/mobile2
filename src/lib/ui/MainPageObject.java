package lib.ui;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.appium.java_client.AppiumDriver;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;


public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;
    }

    //можно расширить link, className и пр.
    public static By getLocatorByString(String locator_with_type)
    {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"), 2);
        String by_type = exploded_locator[0];
        String locator = exploded_locator[1];

        if (by_type.equals("xpath")){
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }

    public int getAmountOfElements(String locator_with_type) {
        By by = getLocatorByString(locator_with_type);

        List elements = driver.findElements(by);
        return elements.size();
    }
    public WebElement waitForElementPresent(String locator_with_type, String error_message, int timeoutInSecond)
    {
        By by = getLocatorByString(locator_with_type);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    //*****************************waits
    public WebElement waitForElementPresent(String locator_with_type, String error_message)
    {
        return waitForElementPresent(locator_with_type, error_message, 5);
    }

    //retest
    public void waitForElementNotPresent(String locator_with_type, String error_message, int timeoutInSecond)
    {
        By by = getLocatorByString(locator_with_type);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public void waitForElementNotPresent(String locator_with_type, String error_message)
    {
        waitForElementNotPresent(locator_with_type, error_message, 5);
    }

    //******************************************************
    public WebElement click(String locator_with_type, String error_message, int timeoutInSecond)
    {
        WebElement element = waitForElementPresent(locator_with_type, error_message, timeoutInSecond);
        element.click();

        return element;
    }

    public WebElement click(String locator_with_type, String error_message)
    {
        return click(locator_with_type, error_message, 5);
    }

    public WebElement sendKeys(String locator_with_typer, String value, String error_message, int timeoutInSecond)
    {
        WebElement element = waitForElementPresent(locator_with_typer, error_message, timeoutInSecond);
        element.sendKeys(value);

        return element;
    }

    public WebElement sendKeys(String locator_with_type, String value, String error_message)
    {
        return sendKeys(locator_with_type, value, error_message,5);
    }

    public WebElement sendKeysWithoutPaste(String locator_with_type, String error_message, String value)
    {
        WebElement element = waitForElementPresent(locator_with_type, error_message);
        ((AndroidElement)element).setValue(value);
        /*TouchAction action = new TouchAction((MobileDriver) waitUtils.getDriver());
        action.longPress(element).waitAction(2000).perform();
        element.sendKeys(value);
        */
        return element;
    }

    public WebElement clear(String locator_with_type, String error_message, int timeoutInSecond)
    {
        WebElement element = waitForElementPresent(locator_with_type, error_message, timeoutInSecond);
        element.clear();

        return element;
    }

    public WebElement clear(String locator_with_type, String error_message)
    {
        return clear(locator_with_type, error_message, 5);
    }

    public String getAttribute(String locator_with_type, String attribute_name, String error_message, int timeoutInSecond)
    {
        WebElement element = waitForElementPresent(locator_with_type, error_message, timeoutInSecond);
        return element.getAttribute(attribute_name);
    }

    public String getAttribute(String locator_with_type, String attribute_name, String error_message)
    {
        return getAttribute(locator_with_type, attribute_name, error_message, 5);
    }

    //***************************************************************
    public void assertElementHasText(String locator_with_type, String expected_text, String error_message) {
        By by = getLocatorByString(locator_with_type);

        String error_find_message = "Cannot web element by " + by.toString();
        WebElement element = waitForElementPresent(locator_with_type, error_find_message);
        Assert.assertTrue(error_message, element.getText().equals(expected_text));
    }

    public void assertElementNotPresent(String locator_with_type, String error_message) {
        By by = getLocatorByString(locator_with_type);
        int amount_of_elements = getAmountOfElements(locator_with_type);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + by.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public void assertElementsPresentNowByXpath(String locatorString, String error_message)
    {
        Assert.assertTrue(error_message, driver.findElements(By.xpath(locatorString)).size() != 0);
    }

    //**************************************************************
    public void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction((MobileDriver) driver);
        Dimension size = driver.manage().window().getSize();

        int x = size.width/2;
        int start_y = (int) (size.height*0.8);
        int end_y = (int) (size.height*0.2);

    action
        .press(new PointOption().point(x, start_y))
        .waitAction(new WaitOptions().withDuration(Duration.ofSeconds(timeOfSwipe)))
        .moveTo(new PointOption().point(x, end_y))
        .release()
        .perform();
    }

    public void swipeUpQuick()
    {
        swipeUp(200);
    }


    public void swipeUpToFindElement(String locator_with_type, String error_message, int max_swipe)
    {
        int already_swiped = 0;
        By by = getLocatorByString(locator_with_type);
        while (driver.findElements(by).size() == 0)
        {
            if (already_swiped > max_swipe)
            {
                waitForElementPresent(locator_with_type,
                    "Cannot find element by swiing up. \n" + error_message);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeElementToLeft(String locator_with_type, String error_message)
    {
        WebElement element = waitForElementPresent(locator_with_type, error_message, 5);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;

        TouchAction action = new TouchAction((MobileDriver) driver);
        action.press(new PointOption().point(right_x, middle_y))
            .waitAction(new WaitOptions().withDuration(Duration.ofMillis(300)))
            .moveTo(new PointOption().point(left_x, middle_y))
            .release()
            .perform();
    }
}