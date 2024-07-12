package baseRepo;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import utilities.FilePath;
import utilities.R;

import java.io.File;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BaseExecutor {
    public static WebDriver driver;
    public static ExtentReports extent;
    public String runEnvironment;
    public HashMap<String, Object> prefs = new HashMap<String, Object>();
    protected Actions action;
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    public static ExtentHtmlReporter reporter;
    public static String runEnviroment;
    public static HashMap<String, String> testResults;

    @BeforeSuite(alwaysRun = true)
    public void setupExtentReport() {
        // create test-output directory if doesn't exists
        File directory = new File("report");
        if (!directory.exists()) {
            directory.mkdir();
        }
        // initiate report generation
        reporter = new ExtentHtmlReporter("report");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        // printing execution environment
        try {
            R r = new R(FilePath.TestDataProp);
            runEnviroment = r.getPropValue("Environment");
        } catch (Exception e) {
            runEnviroment = "UNKNOWN";
        }
        switch (runEnviroment) {
            case "local":
                log.info("Running Tests on local System");
                // Below parameter has to be parameterized
                extent.setSystemInfo("System", "Local");
                extent.setSystemInfo("OS", System.getProperty("os.name"));
                System.out.println("Running Tests on local System");
                break;
            case "remote":
                log.info("Running Tests on Distributed Selenium GRID System");
                // Below parameter has to be parameterized
                extent.setSystemInfo("System", "Remote");
                extent.setSystemInfo("OS", System.getProperty("os.name"));
                System.out.println("Running Tests on Distributed Selenium GRID System");
                break;
            default:
                log.info("Unknown Execution Environment");
        }
        testResults = new HashMap<String, String>();
    }


    public WebDriver initiateTest() {
        // Setting up remote driver
        try {
            R r = new R(FilePath.TestDataProp);
            runEnvironment = r.getPropValue("environment");
            String browserName = r.getPropValue("browser");
            String hubURL;
            DesiredCapabilities capabilities;
            switch (runEnvironment) {
                case "local":
                    switch (browserName) {
                        case "chrome":
                            WebDriverManager.chromedriver().setup();
                            driver = new ChromeDriver();
                            log.info("Browser", browserName);
                            break;
                        case "firefox":
                            log.info("Browser", browserName);
                            WebDriverManager.firefoxdriver().setup();
                            driver = new FirefoxDriver();
                            break;
                        case "internetexplorer":
                            log.info("Browser", browserName);
                            WebDriverManager.iedriver().setup();
                            driver = new InternetExplorerDriver();
                            break;
                        case "edge":
                            log.info("Browser", browserName);
                            WebDriverManager.edgedriver().setup();
                            driver = new EdgeDriver();
                            break;
                        default:
                            log.info("Unknown Browser on remote");
                    }
                    driver.manage().window().maximize();
                    action = new Actions(driver);
                    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                    openURL();
            }
        } catch (Exception e1) {
            log.warn(e1 + " - Fail");
        }
        return driver;
    }

    public void openURL() {
        try {
            R r = new R(FilePath.GlobalProp);
            driver.navigate().to(r.getPropValue("stageURL"));
        } catch (Exception ex) {
            log.info(String.valueOf(ex));
        }
    }



    public static WebElement getSelector(String selectBy, String objectReference) {
        WebElement selector = null;
        switch (selectBy) {
            case "xpath":
                selector = driver.findElement(By.xpath(objectReference));
                break;
            case "cssSelector":
                selector = driver.findElement(By.cssSelector(objectReference));
                break;
            case "id":
                selector = driver.findElement(By.id(objectReference));
                break;
            case "className":
                selector = driver.findElement(By.className(objectReference));
                break;
            case "name":
                selector = driver.findElement(By.name(objectReference));
                break;
            default:
                Assert.assertFalse(false, "Unable to Find Element");
        }
        return selector;
    }

    public static void enterValues(String selectBy, String objectReference, String inputValue) {
        try {
            getSelector(selectBy, objectReference).sendKeys(inputValue);
        } catch (Exception e) {
            log.error(e + "Fail");
        }
    }

    public void clickOnElement(String selectBy, String objectReference) {
        try {
            getSelector(selectBy, objectReference).click();
        } catch (Exception e) {
            log.error(e + "Fail");
        }
    }

    public String waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated((By) element));
            return "PASS";
        } catch (Exception e) {
            log.error(e + "Fail to search element");
            return "FAIL";
        }
    }

    public void quiteBrowser() {
        driver.quit();
    }
}
