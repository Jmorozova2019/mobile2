package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class iOSTestCase extends TestCase {
    protected AppiumDriver driver;
    private static String AppiumUrl = "http://127.0.0.1:4723/wd/hub";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhoneSE");
        capabilities.setCapability("platformVersion", "11.3");
                capabilities.setCapability("app",
                "/Users/Zhanna/Desktop/JavaAppiumAutomation/apks/org.wikipedia.app");

        driver = new IOSDriver(new URL(AppiumUrl), capabilities);

        //rotateScreenPotrait();
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
}
