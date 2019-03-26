package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.enums.AppType;

import java.io.File;
import java.net.URL;

public class DriverSetup extends TestProperties {
    private static AppiumDriver singleDriver;
    private static DesiredCapabilities capabilities;
    protected static WebDriverWait wait;

    protected static String AUT;
    protected static String SUT;
    private static String TEST_PLATFORM;
    private static String DRIVER;

    DriverSetup() {
    }

    void setDriver(String appType) throws Exception {
        setFileByApp(appType);
        prepareDriver(appType);
    }

    private void setFileByApp(String appType) {
        if (appType.equals(AppType.WEB.getType())) {
            fileName = "webTest.properties";
        } else if (appType.equals(AppType.NATIVE.getType())) {
            fileName = "nativeTest.properties";
        }
    }

    private void prepareDriver(String setup) throws Exception {
        capabilities = new DesiredCapabilities();
        String path = System.getProperty("user.dir");
        String browserName;

        TEST_PLATFORM = getPlatform();
        switch (TEST_PLATFORM) {
            case "Android":
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getDeviceName());
                browserName = "Chrome";
                break;
            case "iOS":
                browserName = "Safari";
                break;
            default:
                throw new Exception("Unknown mobile platform");
        }

        if (setup.equals(AppType.WEB.getType())) {
            SUT = getWeb();
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
            capabilities.setCapability("chromedriverExecutable", path + getChromeDriver());
        } else if (setup.equals(AppType.NATIVE.getType())) {
            AUT = getApp();
            File app = new File(path + AUT);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        } else {
            throw new Exception("Unclear type of mobile app");
        }

        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        DRIVER = getAppiumDriver();
        singleDriver = new AppiumDriver(new URL(DRIVER), capabilities);
        wait = new WebDriverWait(singleDriver, 10);
    }

    protected AppiumDriver getDriver() {
        return singleDriver;
    }

}
