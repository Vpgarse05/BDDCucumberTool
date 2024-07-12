package utilities;

import baseRepo.BaseLib;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertyReader {
    private static Logger log = Logger.getLogger(BaseLib.class.getName());
    public String readProperty(String propertyName) {
        try {
            FileReader fileReader = new FileReader("./config/config.properties");
            Properties properties = new Properties();
            try {
                properties.load(fileReader);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return properties.getProperty(propertyName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    static Properties properties = new Properties();
    static boolean configFileCheck = false;
    static boolean globalFileCheck = false;
    static boolean objectRepoFileCheck = false;
    static FileReader configReader;
    static FileReader globalReader;
    static FileReader objectReader;

    public static String getProperty(PropertyFileEnum propertyFileEnum, String propertyName) {
        switch (propertyFileEnum) {
            case CONFIG:
                try {
                    if (configFileCheck != true) {
                        configReader = new FileReader("src/main/resources/config.properties");
                        configFileCheck = true;
                    }
                    properties.load(configReader);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            case GLOB:
                try {
                    if (globalFileCheck != true) {
                        globalReader = new FileReader("src/main/resources/global.properties");
                        globalFileCheck = true;
                    }
                    properties.load(globalReader);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            case API:
                try {
                    if (globalFileCheck != true) {
                        globalReader = new FileReader("src/main/resources/api.properties");
                        globalFileCheck = true;
                    }
                    properties.load(globalReader);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            case OBJ:
                try {
                    if (objectRepoFileCheck != true) {
                        objectReader = new FileReader("src/main/resources/objectRepository.properties");
                        objectRepoFileCheck = true;
                    }
                    properties.load(objectReader);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            default:
                log.info(propertyFileEnum + " Given Property File Not Exist");
        }
        return properties.getProperty(propertyName);
    }
}
