package lib.ui;

import io.appium.java_client.AppiumDriver;


abstract public class NavigationUI extends MainPageObject{

    protected static String
            MY_LIST_LINK,
            SAVED_BUTTON;

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    public void clickMyList() {
        click(MY_LIST_LINK, "Cannot find navigation button to My list");
    }

    public void clickSavedButton()
    {
        click(SAVED_BUTTON, "Cannot find saved button");
    }
}
