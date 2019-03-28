package setup;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Hooks extends DriverSetup {

    private static String appType;

    public Hooks(String setup) {
        appType = setup;
    }

    @BeforeClass(groups = {"native","web"}, description = "Start driver session")
    public void setup() throws Exception {
        if (getDriver() == null) setDriver(appType);
    }

    @AfterClass(groups = "web", description = "Close driver session")
    public void close() {
        getDriver().close();
    }

    @AfterClass(groups = "native", description = "Close driver session")
    public void tearDown() {
        getDriver().quit();
    }
}
