package lib.ui;

import lib.utils.Checker;
import lib.utils.SafeAction;
import lib.utils.Swipe;
import lib.utils.WaitUtils;

import io.appium.java_client.AppiumDriver;


public class MainPageObject {

    protected AppiumDriver driver;

    public Checker checker;
    public WaitUtils waitUtils;
    public SafeAction safeAction;
    public Swipe swipe;

    public MainPageObject(AppiumDriver driver)
    {
        this.driver = driver;

        waitUtils = new WaitUtils(driver);
        checker = new Checker(waitUtils);
        safeAction = new SafeAction(waitUtils);
        swipe = new Swipe(driver, waitUtils);
    }
}