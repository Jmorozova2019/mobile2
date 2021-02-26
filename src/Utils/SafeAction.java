package Utils;

import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebElement;


public class SafeAction {
    private  WaitUtils waitUtils;

    public SafeAction(WaitUtils waitUtils)
    {
        this.waitUtils = waitUtils;
    }

    public WebElement click(Locator locator, String error_message, int timeoutInSecond)
    {
        WebElement element = waitUtils.waitForElementPresent(locator, error_message, timeoutInSecond);
        element.click();

        return element;
    }

    public WebElement click(Locator locator, String error_message)
    {
        return click(locator, error_message, 5);
    }

    public WebElement sendKeys(Locator locator, String value, String error_message, int timeoutInSecond)
    {
        WebElement element = waitUtils.waitForElementPresent(locator, error_message, timeoutInSecond);
        element.sendKeys(value);

        return element;
    }

    public WebElement sendKeys(Locator locator, String value, String error_message)
    {
        return sendKeys(locator,value,error_message,5);
    }

    public WebElement sendKeysWithoutPaste(Locator locator, String error_message, String value)
    {
        WebElement element = waitUtils.waitForElementPresent(locator, error_message);
        ((AndroidElement)element).setValue(value);
        /*TouchAction action = new TouchAction((MobileDriver) waitUtils.getDriver());
        action.longPress(element).waitAction(2000).perform();
        element.sendKeys(value);
        */
        return element;
    }

    public WebElement clear(Locator locator, String error_message, int timeoutInSecond)
    {
        WebElement element = waitUtils.waitForElementPresent(locator, error_message, timeoutInSecond);
        element.clear();

        return element;
    }

    public WebElement clear(Locator locator, String error_message)
    {
        return clear(locator, error_message, 5);
    }

    public String getAttribute(Locator locator, String attribute_name, String error_message, int timeoutInSecond)
    {
        WebElement element = waitUtils.waitForElementPresent(locator, error_message, timeoutInSecond);
        return element.getAttribute(attribute_name);
    }

    public String getAttribute(Locator locator, String attribute_name, String error_message)
    {
        return getAttribute(locator, attribute_name, error_message, 5);
    }
}
