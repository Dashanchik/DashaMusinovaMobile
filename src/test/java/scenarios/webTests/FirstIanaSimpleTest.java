package scenarios.webTests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import setup.DriverSetup;

public class FirstIanaSimpleTest extends DriverSetup {

    protected FirstIanaSimpleTest() {
    }

    @BeforeSuite
    public void setup() throws Exception {
//        prepareAndroidWeb();
//        prepareAndroidNative();
        AUT = null;
        prepareDriver();
    }

    @Test(description = "Open website")//todo add check page title and http status code
    public void webTest() throws InterruptedException {
        driver.get(SUT);//
//        Thread.sleep(5000);
        wait.until(ExpectedConditions.urlToBe(SUT + "/")); //todo use regexp
        System.out.println("Site opening done");

    }

    @AfterSuite
    public void tearDown() throws Exception {
        driver.quit();
    }
}
