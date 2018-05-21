package fr.esgi.services.util;

import lombok.Data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Data
public class GoogleApiUtil {
    String result = "";
    InputStream inputStream;

    public String getGoogleApiKeyValue() throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "googleApi.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            result = prop.getProperty("google.api");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }
}
