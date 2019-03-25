package scenarios.webTests;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import setup.Hooks;

@Test(groups = "web")
public class FirstIanaSimpleTest extends Hooks {
    public FirstIanaSimpleTest() {
        super("web");
    }

    @Test(description = "Open website")//todo add check page title and http status code
    public void webTest() {

        // Get driver
        AppiumDriver driver = getDriver();
        // Go to URL
        driver.get(SUT);
        // Wait until page is loaded
        wait.until(ExpectedConditions.urlToBe(SUT + "/")); //todo use regexp

        System.out.println("Site opening done");

    }
}
