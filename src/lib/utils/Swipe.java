package lib.utils;

import io.appium.java_client.MobileDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Dimension;

import io.appium.java_client.TouchAction;


public class Swipe {
    private WebDriver driver;
    private WaitUtils waitUtils;

    public Swipe(WebDriver driver, WaitUtils waitUtils)
    {
        this.driver = driver;
        this.waitUtils = waitUtils;
    }

    public void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction((MobileDriver) driver);
        Dimension size = driver.manage().window().getSize();

        int x = size.width/2;
        int start_y = (int) (size.height*0.8);
        int end_y = (int) (size.height*0.2);

        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    public void swipeUpQuick()
    {
        swipeUp(200);
    }


    public void swipeUpToFindElement(Locator locator, String error_message, int max_swipe)
    {
        int already_swiped = 0;
        while (driver.findElements(locator.getBy()).size() == 0)
        {
            if (already_swiped > max_swipe)
            {
                waitUtils.waitForElementPresent(
                        locator,
                        "Cannot find element by swiing up. \n" + error_message);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }

    public void swipeElementToLeft(Locator locator, String error_message)
    {
        WebElement element = waitUtils.waitForElementPresent(locator, error_message, 5);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;

        TouchAction action = new TouchAction((MobileDriver) driver);
        action.press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();
    }
}
