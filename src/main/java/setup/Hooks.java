package setup;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test(groups = {"native","web"})
public class Hooks extends DriverSetup {

    private static String appType;

    public Hooks(String setup) {
        appType = setup;
    }

    @BeforeClass(description = "Start driver session")
    public void setup() throws Exception {
        if (getDriver() == null) setDriver(appType);
    }

    @AfterClass(description = "Close driver session")
    public void close() {
        getDriver().closeApp();
    }

}
