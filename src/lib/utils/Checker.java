package lib.utils;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Checker
{
    WaitUtils waitUtils;
    WebDriver driver;

    public Checker(WaitUtils waitUtils)
    {
        this.waitUtils = waitUtils;
        this.driver = waitUtils.getDriver();
    }

    public void assertElementHasText(Locator locator, String expected_text, String error_message) {
        String error_find_message = "Cannot web element by " + locator.getStrategy() + " " + locator.getLocator();
        WebElement element = waitUtils.waitForElementPresent(locator, error_find_message);
        Assert.assertTrue(error_message, element.getText().equals(expected_text));
    }

    public void assertElementNotPresent(Locator locator, String error_message) {
        int amount_of_elements = locator.getAmountOfElements(driver);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + locator.getBy().toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
    }

    public void assertElementsPresentNowByXpath(String locatorString, String error_message)
    {
        Assert.assertTrue(error_message, driver.findElements(By.xpath(locatorString)).size() != 0);
    }
}
