package setup;

import org.testng.annotations.*;

public class Hooks extends DriverSetup {
    public static String appType;

    public Hooks(String setup) {
        this.appType = setup;
    }

    @BeforeSuite
    public void setup() throws Exception {
        if (getDriver() == null) setDriver(appType);
    }

    @AfterSuite
    public void tearDown() {
        getDriver().quit();
    }
}
