import java.io.IOException;
import java.util.*;

public class Config {
    public static final String CONFIG_RESOURCE_NAME = "/breakout.properties";

    public static final Config instance = new Config();

    Properties configProperties = new Properties();

    public Config() {
        try {
            configProperties.load(Config.class.getResourceAsStream(CONFIG_RESOURCE_NAME));
        } catch (IOException e) {
            System.err.println("Unable to load configuration properties: " + CONFIG_RESOURCE_NAME);
            e.printStackTrace();

            System.exit(-1);
        }
    }

    public static Config getInstance() {
        return instance;
    }

    public double getDouble(String propertyName) {
        String propertyValueString = getMandatoryStringValue(propertyName);
        double value = 0;

        try {
            value = Double.parseDouble(propertyValueString);
        } catch (NumberFormatException e) {
            System.err.println("Property not a valid double value: " + propertyName + " with value: " + propertyValueString);

            System.exit(-1);
        }

        return value;
    }

    public int getInt(String propertyName) {
        String propertyValueString = getMandatoryStringValue(propertyName);
        int value = 0;

        try {
            value = Integer.parseInt(propertyValueString);
        } catch (NumberFormatException e) {
            System.err.println("Property not a valid int value: " + propertyName + " with value: " + propertyValueString);

            System.exit(-1);
        }

        return value;
    }

    public String getString(String propertyName) {
        return getMandatoryStringValue(propertyName);
    }

    private String getMandatoryStringValue(String propertyName) {
        String propertyValueString = configProperties.getProperty(propertyName);

        if (propertyValueString == null || propertyValueString.isEmpty()) {
            System.err.println("Missing mandatory property: " + propertyName);

            System.exit(-1);
        }

        return propertyValueString;
    }
}