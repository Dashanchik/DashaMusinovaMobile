package scenarios.nativeTests;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import setup.Hooks;
import setup.enums.AppType;

import static org.hamcrest.MatcherAssert.assertThat;

@Test(groups = "native")
public class FirstContactManagerSimpleTest extends Hooks {

    public FirstContactManagerSimpleTest(){
        super(AppType.NATIVE.getType());
    }

    @Test(description = "Open contact manager and check add aontact form")
    public void SimpleTest(){
        // Get driver
        AppiumDriver driver = getDriver();

        // Get all fields for test
        String appPackageName = "com.example.android.contactmanager:id/";
        By addButton = By.id(appPackageName + "addContactButton");
        By screenTitle = By.id("android:id/title");
        By targetAccount = By.id(appPackageName + "accountSpinner");
        By contactName = By.id(appPackageName + "contactNameEditText");
        By contactPhone = By.id(appPackageName + "contactPhoneEditText");
        By contactPhoneType = By.id(appPackageName + "contactPhoneTypeSpinner");
        By contactEmail = By.id(appPackageName + "contactEmailEditText");
        By contactEmailType = By.id(appPackageName + "contactEmailTypeSpinner");
        By saveButton = By.id(appPackageName + "contactSaveButton");

        // Click "Add" button to add new contact
        driver.findElement(addButton).click();

        // Validate the add contact form elements are displayed and enabled
        assertThat("The contact form title is not correct", driver.findElement(screenTitle).getText().equals("Add Contact"));
        assertThat("Target account is not displayed", driver.findElement(targetAccount).isDisplayed());
        assertThat("Target account is not enabled", driver.findElement(targetAccount).isEnabled());
        assertThat("Contact name is not displayed", driver.findElement(contactName).isDisplayed());
        assertThat("Contact name is not enabled", driver.findElement(contactName).isEnabled());
        assertThat("Contact phone is not displayed", driver.findElement(contactPhone).isDisplayed());
        assertThat("Contact phone is not enabled", driver.findElement(contactPhone).isEnabled());
        assertThat("Contact phone type is not displayed", driver.findElement(contactPhoneType).isDisplayed());
        assertThat("Contact phone type is not enabled", driver.findElement(contactPhoneType).isEnabled());
        assertThat("Contact email is not displayed", driver.findElement(contactEmail).isDisplayed());
        assertThat("Contact email is not enabled", driver.findElement(contactEmail).isEnabled());
        assertThat("Contact email type is not displayed", driver.findElement(contactEmailType).isDisplayed());
        assertThat("Contact email type is not enabled", driver.findElement(contactEmailType).isEnabled());
        assertThat("Contact save button is not displayed", driver.findElement(saveButton).isDisplayed());
        assertThat("Contact save button is not enabled", driver.findElement(saveButton).isEnabled());
    }
}
