package Utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils
{
    private WebDriver driver;

    public WaitUtils(WebDriver driver)
    {
        this.driver = driver;
    }
    public WebDriver getDriver(){return  driver;}

    public WebElement waitForElementPresent(Locator locator, String error_message, int timeoutInSecond)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");

        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator.getBy())
        );
    }

    public WebElement waitForElementPresent(Locator locator, String error_message)
    {
        return waitForElementPresent(locator, error_message, 5);
    }

    //retest
    public void waitForElementNotPresent(Locator locator, String error_message, int timeoutInSecond)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSecond);
        wait.withMessage(error_message + "\n");

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(locator.getBy())
        );
    }

    public void waitForElementNotPresent(Locator locator, String error_message)
    {
        waitForElementNotPresent(locator, error_message, 5);
    }
}
