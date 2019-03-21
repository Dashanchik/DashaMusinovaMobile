package scenarios;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.DriverSetup;
import setup.TestProperties;



public class FirstSimpleTest extends DriverSetup {

    protected FirstSimpleTest()  {
    }

    @BeforeClass
    public void setup() throws Exception {
//        prepareAndroidWeb();
//        prepareAndroidNative();
        prepareDriver();

    }

    @Test
    public void SimpleTest() {
        String app_package_name = "com.example.android.contactmanager:id/";
        By add_btn = By.id(app_package_name + "addContactButton");
        driver.findElement(add_btn).click();
        System.out.println("Simplest Appium test done");
    }

    @Test(description = "Open website")
    public void webTest() throws InterruptedException{
        driver.get(TestProperties.getWeb());
        Thread.sleep(5000);
        System.out.println("Site opening done");

    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
