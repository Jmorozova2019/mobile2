package lib;

import io.appium.java_client.AppiumDriver;

import junit.framework.TestCase;

import lib.ui.WelcomePageObject;
import org.junit.AfterClass;
import org.openqa.selenium.ScreenOrientation;


import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        driver = Platform.getInstance().getDriver();
        rotateScreenPotrait();
        skipWelcomePageForIOSApp();
    }

    @AfterClass
    private void rotateScreenPotraitBeforTest()
    {
        rotateScreenPotrait();
    }

    @Override
    public void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPotrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(Duration durationOfSecond)
    {
        driver.runAppInBackground(durationOfSecond);
    }

    private void skipWelcomePageForIOSApp()
    {
        //if (Platform.getInstance().isIOS())
        {
            WelcomePageObject welcomePageObject = new WelcomePageObject(driver);
            welcomePageObject.clickSkip();
        }
    }
}
