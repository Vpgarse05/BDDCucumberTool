package utilities;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.*;

public class R {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    Properties props = null;
    private static Properties resourcePathProps = null;
    private static Properties apiPathProps = null;
    private static Properties applicationProps = null;
    private InputStream inputStream = null;
    private String CONFIG_PROPERTIES_PATH;

    public R(String CONFIG_PROPERTIES_PATH) {
        try {
            props = new Properties();
            this.CONFIG_PROPERTIES_PATH = CONFIG_PROPERTIES_PATH;
            inputStream = new FileInputStream(CONFIG_PROPERTIES_PATH);
            props.load(inputStream);
        } catch (Exception ex) {
            log.info(String.valueOf(ex));
        }
    }

    /**
     * This method establish connection with the resource properties file and return
     * properties instance.
     *
     * @param filePath
     * @return
     */
    public static Properties initResourcePathProps(String filePath) {
        resourcePathProps = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            resourcePathProps.load(inputStream);
        } catch (Exception e) {
            log.info(String.valueOf(e));
        }
        return resourcePathProps;
    }

    /**
     * This method establish connection with the resource properties file and return
     * properties instance.
     *
     * @param filePath
     * @return
     */
    public static Properties initAPIPathProps(String filePath) {
        apiPathProps = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            apiPathProps.load(inputStream);
        } catch (Exception e) {
            log.info(String.valueOf(e));
        }
        return apiPathProps;
    }

    /**
     * This method establish connection with the global properties file and return
     * global properties instance.
     *
     * @param filePath
     */
    public static void initGlobalProps(String filePath) {
        applicationProps = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            applicationProps.load(inputStream);
        } catch (Exception e) {
            log.info(String.valueOf(e));
        }
    }

    /**
     * This method provides value of the given key in the resource properties file.
     *
     * @param key
     * @return
     */
    public static String getResourcePathValue(String key) {
        String value;
        value = resourcePathProps.getProperty(key).trim();
        return value;
    }

    /**
     * This method provides value of the given key in the resource properties file.
     *
     * @param key
     * @return
     */
    public static String getAPIPathValue(String key) {
        String value;
        value = apiPathProps.getProperty(key).trim();
        return value;
    }

    /**
     * This method provides value of the given key in the global properties file.
     *
     * @param key
     * @return
     */
    public static String getGlobalPropValue(String key) {
        String value;
        value = applicationProps.getProperty(key).trim();
        return value;
    }

    /**
     * This is the generic method provides value of the given key from the properties file.
     *
     * @param key
     * @return
     */
    public String getPropValue(String key) {
        String value;
        value = props.getProperty(key).trim();
        return value;
    }

}
