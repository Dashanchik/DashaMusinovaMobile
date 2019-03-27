package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestProperties {
    TestProperties(){}
    String fileName;
    String getProperty(String property) {

        Properties prop = new Properties();
        InputStream input = null;
        String value = "";

        try {
            input = new FileInputStream("src\\main\\resources\\" + fileName);

            prop.load(input);

            value = prop.getProperty(property);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    protected String getApp() {
        return getProperty("aut");
    }

    protected String getWeb() {
        return getProperty("sut");
    }

    protected String getPlatform() {
        return getProperty("platform");
    }

    protected String getAppiumDriver() {
        return getProperty("driver");
    }

    protected String getChromeDriver() {
        return getProperty("chromeDriver");
    }

    protected String getDeviceName() {
        return getProperty("deviceName");
    }

    protected String getUdid(){
        return getProperty("udid");
    }
}
