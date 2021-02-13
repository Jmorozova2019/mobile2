package Utils;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class AssertUtils
{
    WaitUtils waitUtils;

    public AssertUtils(WaitUtils waitUtils)
    {
        this.waitUtils = waitUtils;
    }

    public void assertElementHasText(Locator locator, String expected_text, String error_message) {
        String error_find_message = "Cannot web element by " + locator.getStrategy() + " " + locator.getLocator();
        WebElement element = waitUtils.waitForElementPresent(locator, error_find_message);
        Assert.assertTrue(error_message, element.getText().equals(expected_text));
    }
}
