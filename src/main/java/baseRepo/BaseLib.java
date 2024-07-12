package baseRepo;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.PropertyFileEnum;
import utilities.PropertyReader;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class BaseLib {
    private static Logger log = Logger.getLogger(BaseLib.class.getName());
    public static WebDriver driver = null;

    /**
     * ThreadLocal variable which contains the web driver instance which is
     * used to perform browser interactions with.
     */

    public void setUP() {

        // browser name value passed from command line
        String browserName = PropertyReader.getProperty(PropertyFileEnum.GLOB,"browser");
        log.info(browserName);
        if (browserName == null) {
            browserName = "chrome";
        }
        // if browser name passed as firefox
        if (browserName.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
        }
        // if browser name passed as chrome
        else if (browserName.equalsIgnoreCase("chrome")) {
            ChromeOptions option = new ChromeOptions();
            if (System.getProperty("os.name", "generic").toLowerCase().contains("mac os")) {
                option.addArguments("--disable-dev-shm-usage");
            }
            option.addArguments("--remote-allow-origins=*");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(option);
            driver.navigate().to(PropertyReader.getProperty(PropertyFileEnum.CONFIG, "stageURL"));
        } else {
            log.info(browserName + " Browser Not Supported");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
    }

    public void tearDown(Scenario scenario) {
        log.info("Shutting down driver" + "\n" + "----------------------------------------------");
        // quitting the web driver
        getWebDriver().quit();
    }

    /**
     * @return the driver for the current thread
     */
    public static WebDriver getWebDriver() {
        return driver;
    }
    public String takeScreenshot(String screenshotName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = "screenshots/" + screenshotName + ".png";
        try {
            FileUtils.copyFile(source, new File(destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destination;
    }
}
