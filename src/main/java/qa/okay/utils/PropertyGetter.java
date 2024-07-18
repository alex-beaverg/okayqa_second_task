package qa.okay.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyGetter {
    private static final Properties property = new Properties();
    private static final String configPath = "src/main/resources/config.properties";
    private static final String dataPath = "src/main/resources/data.properties";
    private static final String locatorsPath = "src/main/resources/locators.properties";

    public static String getProperty(String key, String type) {
        String value;
        String path = switch (type) {
            case "config" -> configPath;
            case "data" -> dataPath;
            case "locator" -> locatorsPath;
            default -> throw new RuntimeException();
        };
        try (FileInputStream fis = new FileInputStream(path)) {
            property.load(fis);
            value = property.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException("You have been problem with reading from property file!", e);
        }
        return value;
    }
}
