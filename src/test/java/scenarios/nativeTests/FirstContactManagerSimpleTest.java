package scenarios.nativeTests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.hamcrest.Matcher;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import setup.ContactType;
import setup.DriverSetup;
import setup.TestProperties;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;

public class FirstContactManagerSimpleTest extends DriverSetup {

    protected FirstContactManagerSimpleTest() {
    }

    @BeforeSuite
    public void setup() throws Exception {
//        prepareAndroidWeb();
//        prepareAndroidNative();
        SUT = null;
        prepareDriver();
    }

    @Test(description = "Add contact via contact manager")
    public void SimpleTest() {
        String appPackageName = "com.example.android.contactmanager:id/";
        By addButton = By.id(appPackageName + "addContactButton");
        By targetAccount = By.id(appPackageName + "accountSpinner");
        By contactName = By.id(appPackageName + "contactNameEditText");
        By contactPhone = By.id(appPackageName + "contactPhoneEditText");
        By contactPhoneType = By.id(appPackageName + "contactPhoneTypeSpinner");
        By contactEmail = By.id(appPackageName + "contactEmailEditText");
        By contactEmailType = By.id(appPackageName + "contactEmailTypeSpinner");
        By saveButton = By.id(appPackageName + "contactSaveButton");
        By contactList = By.id(appPackageName + "contactList");

        driver.findElement(addButton).click();//todo add button clicked assert
        driver.findElement(targetAccount).getText().equalsIgnoreCase(TestProperties.getAccountName());
        driver.findElement(contactName).sendKeys("Carlson");
        driver.findElement(contactPhone).sendKeys("223322");
        driver.findElement(contactPhoneType).click();
        driver.findElement(By.xpath(ContactType.WORK.getType())).click();
        driver.findElement(contactEmail).sendKeys("carlson@email.com");
        driver.findElement(contactEmailType).click();
        driver.findElement(By.xpath(ContactType.OTHER.getType())).click();
        driver.findElement(saveButton).click();

        Rectangle listBounds = driver.findElement(contactList).getRect();
        WebElement contactRow = null;
        String firstOnScreen = null;

        while (true) {
            TouchAction action = new TouchAction(driver);
            List<WebElement> foundRows = driver.findElement(contactList).findElements(By.xpath("//android.widget.TextView[@text='Carlson']"));
            if (foundRows.size() == 1) {
                contactRow = foundRows.get(0);
            }
            String newFirstOnScreen = driver.findElement(By.xpath("//android.widget.LinearLayout[1]")).getText();

            if (contactRow != null || firstOnScreen != null && firstOnScreen == newFirstOnScreen) {
                break;
            }
            int midX = listBounds.width / 2 + listBounds.x;
            int bottomY = listBounds.height+listBounds.y;
            action.press(PointOption.point(midX, bottomY)).waitAction().moveTo(PointOption.point(midX, listBounds.y)).release().perform();
            firstOnScreen = newFirstOnScreen;
        }

        assertTrue(contactRow != null);

        System.out.println("Simplest Appium test done");
    }

    @AfterSuite
    public void tearDown() throws Exception {
        driver.quit();
    }
}
