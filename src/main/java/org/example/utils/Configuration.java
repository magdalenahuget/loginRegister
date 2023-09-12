package org.example.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
    private static final Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream("/WEB-INF/classes/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getDatabaseProperty(String key) {
        return properties.getProperty(key);
    }
}
