package scenarios.webTests;

import io.appium.java_client.AppiumDriver;
import io.restassured.RestAssured;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import setup.Hooks;
import setup.enums.AppType;

import static org.hamcrest.MatcherAssert.assertThat;

@Test(groups = "web")
public class FirstIanaSimpleTest extends Hooks {
    public FirstIanaSimpleTest() {
        super(AppType.WEB.getType());
    }

    @Test(description = "Open website")
    public void webTest() {

        // Get driver
        AppiumDriver driver = getDriver();
        // Go to URL
        driver.get(SUT);
        // Wait until page is loaded
        wait.until(ExpectedConditions.urlToBe(SUT + "/"));
        // Validate status code is 200
        assertThat("HTTP Status code is not correct", RestAssured.get(SUT).statusCode() == 200);
        // Check browser title
        assertThat("Page title is not correct", driver.getTitle().equals("Internet Assigned Numbers Authority"));
    }
}
