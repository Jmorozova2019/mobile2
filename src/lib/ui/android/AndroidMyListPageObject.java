package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListPageObject;

public class AndroidMyListPageObject extends MyListPageObject {

    static {
        SAVED_BLOCK = "xpath://*[@resource-id='org.wikipedia:id/item_title' and @text='Saved']";
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_NAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
    }

    public AndroidMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
