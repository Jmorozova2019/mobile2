package lib.ui;

import lib.utils.Locator;
import io.appium.java_client.AppiumDriver;


public class NavigationUI extends MainPageObject{

    private static final String
        MY_LIST_LINK = "//android.widget.FrameLayout[@content-desc='My lists']/android.widget.ImageView";

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyList() {
        safeAction.click(
                new Locator(MY_LIST_LINK),
                "Cannot find navigation button to My list"
        );
    }
}
