package setup;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestProperties {

    private static String getProperty(String property) {

        Properties prop = new Properties();
        InputStream input = null;
        String value = "";

        try {
            input = new FileInputStream("src\\main\\resources\\test.properties");

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

    public static String getApp() {
        return getProperty("aut");
    }
    public static String getWeb() {
        return getProperty("sut");
    }
    public static String getPlatform() {
        return getProperty("platform");
    }
    public static String getDriver() {
        return getProperty("driver");
    }
    public static String getChromeDriver() {
        return getProperty("chromeDriver");
    }
}
