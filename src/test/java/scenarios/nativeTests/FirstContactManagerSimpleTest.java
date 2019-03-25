package scenarios.nativeTests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import setup.ContactType;
import setup.Hooks;

import java.util.List;

import static org.testng.Assert.assertTrue;

@Test(groups = "native")
public class FirstContactManagerSimpleTest extends Hooks {

    public FirstContactManagerSimpleTest(){
        super("native");
    }

    @Test(description = "Add contact via contact manager")
    public void SimpleTest(){
        // Get driver
        AppiumDriver driver = getDriver();
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
        driver.findElement(targetAccount).getText().equalsIgnoreCase(getAccountName());
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


}
