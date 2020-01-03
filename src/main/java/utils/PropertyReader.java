package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyReader.class);

    private static PropertyReader uniqueInstance;

    private PropertyReader() {
    }

    public static synchronized PropertyReader getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new PropertyReader();
        }
        return uniqueInstance;
    }

    public Properties getProperties(String propertyFile) {
        Properties props = new Properties();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File(propertyFile));
            props.load(fileReader);
            return props;
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ex) {
                    LOGGER.warn(ex.getMessage());
                }
            }
        }
    }

    public String getProperty(String key, String propertyFile) {
        return getProperties(propertyFile).getProperty(key);
    }
}


