package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {

    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";

    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    private static Platform instance;
    private Platform() {}

    public static Platform getInstance( )
    {
        if (instance == null)
        {
            instance = new Platform();
        }
        return instance;
    }

    public AppiumDriver getDriver()throws Exception
    {
        URL URL = new URL(APPIUM_URL);
        if(isAndroid()){
            return new AndroidDriver(URL, getAndroidCapabilities());
        } else if(isIOS()){
            return new IOSDriver(URL, getiOSCapabilities());
        } else {
            throw new Exception(("Cannot detect type of the Driver/ Platform value: " + getPlatformVar()));
        }
    }

    public boolean isAndroid()
    {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS()
    {
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getAndroidCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Nexus6_v8");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app",
                "C:/Users/Zhanna/IdeaProjects/mobile2/apks/org.wikipedia_50345_apps.evozi.com.apk");

        return capabilities;
    }

    private DesiredCapabilities getiOSCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhoneSE");
        capabilities.setCapability("platformVersion", "11.3");
        capabilities.setCapability("app",
                "C:/Users/Zhanna/IdeaProjects/mobile2/apks/org.wikipedia.app");

        return capabilities;
    }

    private boolean isPlatform(String myPlatform)
    {
        return myPlatform.equals(getPlatformVar());
    }

    private String getPlatformVar()
    {
        return System.getenv("PLATFORM");
    }
}
