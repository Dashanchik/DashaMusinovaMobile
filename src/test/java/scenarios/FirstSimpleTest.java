package scenarios;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.ContactType;
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
        String appPackageName = "com.example.android.contactmanager:id/";
        By addButton = By.id(appPackageName + "addContactButton");
        By targetAccount = By.id(appPackageName + "accountSpinner");
        By contactName = By.id(appPackageName+"contactNameEditText");
        By contactPhone = By.id(appPackageName + "contactPhoneEditText");
        By contactPhoneType = By.id(appPackageName + "contactPhoneTypeSpinner");
        By contactEmail = By.id(appPackageName + "contactEmailEditText");
        By contactEmailType = By.id(appPackageName + "contactEmailTypeSpinner");
        driver.findElement(addButton).click();
        driver.findElement(targetAccount).getText().equalsIgnoreCase(TestProperties.getAccountName());
        driver.findElement(contactName).sendKeys("Carlson");
        driver.findElement(contactPhone).sendKeys("223322");
        driver.findElement(contactPhoneType).click();
        driver.findElement(By.xpath(ContactType.WORK.getType())).click();
        driver.findElement(contactEmail).sendKeys("carlson@email.com");
        driver.findElement(contactEmailType).click();
        driver.findElement(By.xpath(ContactType.OTHER.getType())).click();

        System.out.println("Simplest Appium test done");
    }

    @Test(description = "Open website")
    public void webTest() throws InterruptedException{
        driver.get(SUT);
        Thread.sleep(5000);
        System.out.println("Site opening done");

    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
