package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class DriverSetup extends TestProperties{
//    public AndroidDriver driver;
    protected AppiumDriver driver;
    protected DesiredCapabilities capabilities;
    protected WebDriverWait wait;

    protected String AUT;
    protected String SUT;
    protected String TEST_PLATFORM;
    protected String DRIVER;

    protected DriverSetup() {
        AUT = getApp();
        SUT = getWeb();
        TEST_PLATFORM = getPlatform();
        DRIVER = getDriver();
    }

    protected void prepareDriver() throws Exception{
       capabilities = new DesiredCapabilities();
       String browserName;

       switch (TEST_PLATFORM){
           case "Android":
               capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator-5554");
               browserName = "Chrome";
               break;
           case "iOS":
               browserName = "Safari";
               break;
           default: throw new Exception("Unknown mobile platform");
       }
       capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,TEST_PLATFORM);

       if(AUT != null && SUT == null){
           File app = new File(AUT);
           capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
       } else if (SUT != null && AUT == null) {
           capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,browserName);
       }else{
           throw new Exception("Unclear type of mobile app");
       }

       driver=new AppiumDriver(new URL(DRIVER),capabilities);
    }

    public void prepareAndroidNative() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformName", getPlatform());

//        capabilities.setCapability("adbExecTimeout", "50000");
        String path = System.getProperty("user.dir");
        File app = new File (path + getApp());

        capabilities.setCapability("aut", app.getAbsolutePath());

        driver = new AndroidDriver(new URL(TestProperties.getDriver()), capabilities);
    }

    public void prepareAndroidWeb() throws MalformedURLException{
        String path = System.getProperty("user.dir");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformName", getPlatform());
        capabilities.setCapability("chromedriverExecutable", path + TestProperties.getChromeDriver());
        capabilities.setCapability("browserName", "Chrome");

        driver = new AndroidDriver(new URL(TestProperties.getDriver()), capabilities);

    }
}
