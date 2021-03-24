package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.MyListPageObject;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.android.AndroidMyListPageObject;
import lib.ui.ios.iOSArticlePageObject;
import lib.ui.ios.iOSMyListPageObject;

public class MyListPageObjectFactory {
    public static MyListPageObject get(AppiumDriver driver)
    {
        if(Platform.getInstance().isAndroid())
        {
            return new AndroidMyListPageObject(driver);
        } else
        {
            return new iOSMyListPageObject(driver);
        }
    }
}
