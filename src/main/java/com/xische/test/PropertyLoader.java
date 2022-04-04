package com.xische.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    public static Properties loadProperties() {
        try {
            String testPorpertiespath = System.getProperty("user.dir");
            File f = new File(testPorpertiespath+ File.separator +"src"+
                    File.separator +"main"+
                    File.separator +"resources"+
                    File.separator + "config" +
                    File.separator + "test.properties");
            InputStream is = new FileInputStream(f);
            Properties prop = new Properties();
            prop.load(is);
            is.close();
            return prop;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
