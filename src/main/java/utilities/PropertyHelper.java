package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyHelper {

    static Properties prop;

    public String getProperty(String PropName)  {

        try {
            File file=new File("src/main/resources/global.properties");
            FileInputStream fis= null;
            fis = new FileInputStream(file);
            prop=new Properties();
            prop.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return prop.getProperty(PropName);
    }
}
