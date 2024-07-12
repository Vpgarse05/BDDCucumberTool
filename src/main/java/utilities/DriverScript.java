package utilities;

import baseRepo.BaseLib;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class DriverScript extends CommonFunctions {
    public static boolean blnResult = true;

    public enum ActionTypes {

        LAUNCHBROWSER, WAITTIME, CLOSEALLBROWSERS, ENTERVALUE, ENTERVALUE1, SENDKEYS, GENERATE_UNIQVALUE, STOREVALUE, UPLOAD_FILE, CLICK, CLEARTEXTBOX, WAITFORELEMENT,
        VERIFYVALUE, GETTEXT, MOUSEOVER, SELECTVALUEDROPDOWN_CXCHOICE, SELECTVALUEBYINDEX, JSCLICK, CAPTUREORDERNUMBER, CHKBOX, SWITCHHOMEWINDOW, SWITCHWINDOW, LAUNCHMOBILEAPP, KILLSESSION, MOBILELOGIN, MOBILEVERIFYVALUE,
        MOBILECLICK, ANNOATERECORD, SPECIALISTVALIDATION, TAPONSCREEN, MANAGERREJECTSALL, SUBMITRECORDS, MANAGERAPPROVEALL, ANNOATERECORD_MANAGER, STARTWORK, LOGOUT, LAUNCHMOBILEBROWSER,
        MOBILEENTERVALUE, SWIPETOBOTTOM, SCROLLDOWN, CALLAPI, SELECTVALUEBYVISIBLETEXT, SELECTVALUE, TAB, ENTER, F12, DOWN, ESCAPE, ORGANIZATION
    }

    public static boolean invokeKeyword(String funcname, String funcparameters)
            throws Throwable {
        try {
            ActionTypes actTypes = ActionTypes.valueOf(funcname.toUpperCase().trim());

            switch (actTypes) {
                case WAITTIME:
                    blnResult = WAITTIME(funcparameters);
                    break;
                case ENTERVALUE:
                    blnResult = ENTERVALUE(driver, funcparameters);
                    break;
                case SENDKEYS:
                    blnResult = SENDKEYS(driver, funcparameters);
                    break;
                case GENERATE_UNIQVALUE:
                    blnResult = GENERATE_UNIQVALUE(funcparameters);
                    break;
                case STOREVALUE:
                    blnResult = STOREVALUE(funcparameters);
                    break;
                case UPLOAD_FILE:
                    blnResult = Upload_File(funcparameters);
                    break;
                case CLICK:
                    blnResult = CLICK(driver, funcparameters);
                    break;
                case JSCLICK:
                    blnResult = JSCLICK(driver, funcparameters);
                    break;
                case CLEARTEXTBOX:
                    blnResult = CLEARTEXTBOX(driver, funcparameters);
                    break;
                case WAITFORELEMENT:
                    blnResult = WAITFORELEMENT(driver, funcparameters);
                    break;
                case VERIFYVALUE:
                    blnResult = VERIFYVALUE(driver, funcparameters);
                    break;
                case GETTEXT:
                    blnResult = GETTEXT(driver, funcparameters);
                    break;
                case MOUSEOVER:
                    blnResult = MouseOver(driver, funcparameters);
                    break;
                case SELECTVALUEDROPDOWN_CXCHOICE:
                    blnResult = SELECTVALUEDROPDOWN_CXchoice(driver, funcparameters);
                    break;
                case SELECTVALUEBYVISIBLETEXT:
                    blnResult = SelectvalueByVisibleText(driver, funcparameters);
                    break;
                case SELECTVALUE:
                    blnResult = Selectvalue(driver, funcparameters);
                    break;
                case SELECTVALUEBYINDEX:
                    blnResult = SelectvalueByIndex(driver, funcparameters);
                    break;
                case CHKBOX:
                    blnResult = chkBox(driver, funcparameters);
                    break;
                case SWITCHWINDOW:
                    blnResult = switchwindow(driver, funcparameters);
                    break;
                case SWITCHHOMEWINDOW:
                    blnResult = switchhomewindow();
                    break;
                case SCROLLDOWN:
                    blnResult = scrollDown(driver, funcparameters);
                    break;
                default:
                    System.out.println(" No Parameter found !");

            }
        } catch (Exception e) {

            // e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return blnResult;
    }

    public static boolean WAITTIME(String parameters) {
        try {
            Thread.sleep(Integer.parseInt(parameters) * 100L);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean CLOSEALLBROWSERS(WebDriver WebDriver)
            throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
        try {
            WebDriver.quit();
            System.out.println("successfully closed all browsers");
            return true;

        } catch (Exception e) {
            System.out.println("unable to find the browser" + " " + e.getMessage());
            return false;
        }

    }

    public static boolean ENTERVALUE(WebDriver WebDriver, String parameters,String value) {
        boolean Status = false;
        String xpath = "";
        try {
            xpath = PropertyReader.getProperty(PropertyFileEnum.OBJ, parameters);
            if (xpath != null) {
                WebElement locator = Getlocator(driver, xpath);
                if (locator.isEnabled()) {
                    locator.click();
                    locator.sendKeys(value);
                    Status = true;
                } else {
                    System.out.println("not able to find" + " " + locator);
                    Status = false;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("unable to find the locator" + " " + xpath);
            Status = false;
        }
        return Status;
    }

    public static boolean KEYPRESSSROBOTCLASS(WebDriver webdriver, String parameters) {

        boolean status = true;
        try {
            Robot robot = new Robot();
            Actions action = new Actions(driver);
            ActionTypes actTypes = ActionTypes.valueOf(parameters.trim());
            switch (actTypes) {

                case TAB:
                    robot.keyPress(KeyEvent.VK_TAB);
                    robot.keyRelease(KeyEvent.VK_TAB);
                    status = true;
                    break;
                case ENTER:
                    robot.keyPress(KeyEvent.VK_ENTER);
                    robot.keyRelease(KeyEvent.VK_ENTER);
                    status = true;
                    break;
                case F12:
                    robot.keyPress(KeyEvent.VK_F12);
                    robot.keyRelease(KeyEvent.VK_F12);
                    status = true;
                    break;
                case ESCAPE:
                    robot.keyPress(KeyEvent.VK_ESCAPE);
                    robot.keyRelease(KeyEvent.VK_ESCAPE);
                    status = true;
                    break;
                case DOWN:
                    robot.keyPress(KeyEvent.VK_DOWN);
                    robot.keyRelease(KeyEvent.VK_DOWN);
                    status = true;
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
        return status;
    }

    public static Boolean GENERATE_UNIQVALUE(String Parameters)  {
       boolean Status=false;
        try {

        } catch (Exception e) {
            System.out.println("exception value : " + e.getMessage());
            Status = false;
        }
        return Status;
    }

    public static boolean VERIFYVALUE(WebDriver webdriver, String parameters) {

        try {

        } catch (Exception e) {
            System.out.println("exception value : " + e.getMessage());
        }

    }

    public static boolean chkBox(WebDriver WebDriver, String parameters) {
        Boolean Status = true;
        String xpath="";
        try {
             xpath= PropertyReader.getProperty(PropertyFileEnum.OBJ, parameters);
            String[] args = splitfunction(xpath, ":");
            if (xpath != null) {
                WebElement locator = Getlocator(driver, xpath);
                if (locator.isEnabled()) {
                    WAITTIME("WAITTIME->2");
                    locator.click();
                    WAITTIME("WAITTIME->2");
                    // clickElementUsingJavascriptExecutor(args[1]);
                    System.out.println("Succesfully clicked on" + " " + locator);
                } else {
                    System.out.println("unable to find" + " " + locator);
                    Status = false;
                }
            } else {
                System.out.println("unable to find the xpath" + " " + xpath);
            }
        } catch (Exception e) {
            System.out.println("unable to find the locator" + " " + e.getMessage());
            Status = false;

        }
        return Status;
    }


    public static boolean JSCLICK(WebDriver WebDriver, String parameters)
            throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
        Boolean Status = true;
        String xpath="";
        try {
            xpath= PropertyReader.getProperty(PropertyFileEnum.OBJ, parameters);
            String[] args = splitfunction(xpath, ":");
            if (xpath != null) {
                WebElement locator = Getlocator(driver, xpath);
                if (locator.isEnabled()) {

                    WAITTIME("WAITTIME->5");
                    clickElementUsingJavascriptExecutor(args[1]);
                    WAITTIME("WAITTIME->5");
                    System.out.println("Succesfully clicked on" + " " + locator);
                } else {
                    System.out.println("unable to find" + " " + locator);
                    Status = false;
                }
            } else {
                System.out.println("unable to find the xpath" + " " + locator);
            }
        } catch (Exception e) {
            System.out.println("unable to find the locator " + xpath + " " + e.getMessage());
            Status = false;

        }
        return Status;
    }

    public static boolean clickElementUsingJavascriptExecutor(String xpathValue) throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
        boolean Status = true;
        try {
            WebElement element = driver.findElement(By.xpath(xpathValue.trim()));
            WAITTIME("waittime->3");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", new Object[]{element});
        } catch (Exception e) {
            System.out.println("exception value : " + e.getMessage());
            Status = false;
        }
        return Status;
    }

    public static Boolean STOREVALUE(String Parameters) throws Exception {


        return null;
    }

    public static boolean GETTEXT(WebDriver webdriver, String parameters,String key) {
        String xpath="";
        String ovalue="";
        Boolean Status = null;
        try {
            xpath= PropertyReader.getProperty(PropertyFileEnum.OBJ, parameters);
            String[] args = splitfunction(xpath, ":");
            if (xpath != null) {
                WebElement locator = Getlocator(driver, args[1]);
                ovalue = locator.getAttribute("value");
                if (ovalue == null || ovalue == "") {
                    ovalue = locator.getText();
                }
                if (ovalue != null) {
                    hmap.put(key, ovalue);
                    System.out.println("captured the value" + "  " +locator + "   " + ovalue);
                    Status = true;
                } else {
                    System.out.println("value is null" + " " + ovalue);
                    Status = false;
                }
            } else {
                System.out.println("unable to find the xpath" + " " + ovalue);
            }

        } catch (Exception e) {
            System.out.println("exception value : " + e.getMessage());
            Status = false;
        }
        return Status;

    }

    public static Boolean Upload_File(String filePath,String Parameters) throws IOException {
        Boolean Status = true;
        String xpath="";
        String workingDirectory = new File(".").getCanonicalPath();
        try {
            xpath= PropertyReader.getProperty(PropertyFileEnum.OBJ, Parameters);
            String[] args = splitfunction(xpath, ":");
            String path = workingDirectory + "/" + filePath.trim();
            Thread.sleep(3000);
            Runtime.getRuntime().exec("wscript " + path + ".vbs");
            System.out.println("wscript " + path + ".vbs");
            File file=new File(path);
            WebElement locator=Getlocator(driver,args[1]);
            locator.sendKeys(file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Status = false;
        }
        return Status;
    }

    public static boolean CLICK(WebDriver WebDriver, String parameters)
            throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
        Boolean Status = true;
        String[] arguments = null;
        String keyvalue = null;
        arguments = splitfunction(parameters, "->");
        try {
            WAITFORELEMENT(WebDriver, "WAITFORELEMENT->" + arguments[1]);
            String xpath = DriverScript.prop.getProperty(arguments[1]);
            if (xpath.contains("+")) {
                String[] newval = splitfunction(xpath, "+");
                if (hmap.containsKey(newval[1])) {
                    keyvalue = hmap.get(newval[1]);
                }
                xpath = xpath.replace(newval[1], keyvalue);
                xpath = xpath.replace("+", "").trim();
            }
            if (xpath != null) {
                WebElement locator = Getlocator(driver, xpath);
                if (locator.isEnabled()) {
                    String[] args = splitfunction(xpath, ":");
                    WAITTIME("WAITTIME->2");
                    locator.click();
                    WAITTIME("WAITTIME->2");
                    // clickElementUsingJavascriptExecutor(args[1]);
                    System.out.println("Succesfully clicked on" + " " + arguments[1]);
                } else {
                    System.out.println("unable to find" + " " + arguments[1]);
                    Status = false;
                }
            } else {
                System.out.println("unable to find the xpath" + " " + arguments[1]);
            }
        } catch (Exception e) {
            System.out.println("unable to find the locator" + " " + e.getMessage());
            Status = false;

        }
        return Status;
    }

    public static boolean CLEARTEXTBOX(WebDriver WebDriver, String parameters)
            throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
        Boolean Status = true;
        try {
            String[] arguments = null;
            arguments = splitfunction(parameters, "\\->");
            String xpath = DriverScript.prop.getProperty(arguments[1]);
            if (xpath != null) {
                WebElement locator = Getlocator(driver, xpath);

                if (locator.isEnabled()) {
                    locator.clear();
                    System.out.println("Succesfully cleared the text box");
                } else {
                    System.out.println("Text box was disabled");
                    Status = false;
                }
            } else {
                System.out.println("unable to find the xpath" + " " + arguments[1]);
            }

        } catch (Exception e) {

            Status = false;
        }
        return Status;

    }

    public static boolean WAITFORELEMENT(WebDriver webdriver, String parameters)
            throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
        boolean status = true;
        String keyvalue = null;
        try {
            int flag = 1;
            String[] arguments = null;
            arguments = splitfunction(parameters, "->");
            String xpath = DriverScript.prop.getProperty(arguments[1]);
            if (xpath.contains("+")) {
                String[] newval = splitfunction(xpath, "+");
                if (hmap.containsKey(newval[1])) {
                    keyvalue = hmap.get(newval[1]);
                }
                xpath = xpath.replace(newval[1], keyvalue);
                xpath = xpath.replace("+", "").trim();
            }
            if (xpath != null) {
                timer = 2000;
                do {
                    WebElement locator = Getlocator(driver, xpath);
                    if (locator == null) {
                        timer = timer - 1;
                        if (timer == 0) {
                            timer = 2001;
                        }
                    } else {
                        if (locator.isDisplayed()) {
                            timer = 2001;
                            flag = 0;
                            System.out.println("Element found" + " " + arguments[1]);
                        } else {
                            timer = timer - 1;
                            if (timer == 0) {
                                timer = 2001;
                            }
                        }
                    }
                } while (timer < 2000);

                if (flag == 1) {
                    status = false;
                }
            } else {
                System.out.println("unable to find the xpath" + " " + arguments[1]);
            }
        } catch (StaleElementReferenceException e) {
            System.out.println(e.getMessage());
            status = false;
        }
        return status;

    }

    public static boolean MouseOver(WebDriver WebDriver, String parameters)
            throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
        Thread.sleep(3000);
        String[] arguments = null;
        arguments = splitfunction(parameters, "->");
        try {
            String xpath = DriverScript.prop.getProperty(arguments[1]);
            Actions act = new Actions(WebDriver);
            if (xpath != null) {
                WAITTIME("waittime->3");
                act.moveToElement(driver.findElement(By.xpath(xpath))).build().perform();
                WAITTIME("waittime->3");
                System.out.println("Succesfully clicked on" + " " + arguments[1]);
                return true;
            } else {
                System.out.println("unable to find" + " " + arguments[1]);
                return false;
            }
        } catch (Exception e) {
            System.out.println("unable to find the locator" + " " + e.getMessage());
            return false;

        }

    }

    public static boolean switchwindow(WebDriver webdriver, String parameters)
            throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
        WAITTIME("WAITTIME->3");
        String[] arguments = null;
        boolean status = false;
        arguments = splitfunction(parameters, "->");
        WebElement locator = null;
        try {
            String xpath = DriverScript.prop.getProperty(arguments[1]);

            String mainWindowHandle = driver.getWindowHandle();
            Set<String> allWindowHandles = driver.getWindowHandles();
            for (String handle : allWindowHandles) {
                if (!handle.equals(mainWindowHandle)) {
                    driver.switchTo().window(handle);
                    locator = Getlocator(driver, xpath);
                    if (locator != null) {
                        break;
                    }
                }
            }

            locator.click();
            System.out.println("Succesfully clicked on" + " " + arguments[1]);
            //driver.switchTo().window(mainWindowHandle);
            status = true;
        } catch (Exception e) {
            System.out.println("exception value : " + e.getMessage());
        }
        return status;

    }

    public static boolean switchhomewindow()
            throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
        WAITTIME("WAITTIME->3");
        boolean status = false;
        try {
            String mainWindowHandle = driver.getWindowHandle();
            Set<String> allWindowHandles = driver.getWindowHandles();
            for (String handle : allWindowHandles) {
                if (!handle.equals(mainWindowHandle)) {
                    driver.switchTo().window(mainWindowHandle);
                    break;
                }
            }
            status = true;
            System.out.println("Successfully moved to Home Window");
        } catch (Exception e) {
            System.out.println("exception value : " + e.getMessage());
        }
        return status;
    }

    public static boolean SelectvalueByIndex(WebDriver webdriver, String parameters)
            throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
        WAITTIME("WAITTIME->3");
        String[] arguments = null;
        boolean checkstatus = false;
        String sValue = null;
        arguments = splitfunction(parameters, "->");
        try {
            String xpath = DriverScript.prop.getProperty(arguments[1]);
            WebElement locator = Getlocator(driver, xpath);
            Select oSelect = new Select(locator);
            if (locator != null) {
                int oindex = Integer.parseInt(arguments[2]);
                oSelect.selectByIndex(oindex);
                System.out.println("Selected the element with index " + " " + arguments[2]);
                checkstatus = true;
            }
        } catch (Exception e) {
            System.out.println("exception value : " + e.getMessage());
        }
        return checkstatus;

    }

    public static boolean SelectvalueByVisibleText(WebDriver webdriver, String parameters)
            throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
        WAITTIME("WAITTIME->3");
        String[] arguments = null;
        boolean checkstatus = false;
        String sValue = null;
        arguments = splitfunction(parameters, "->");
        try {
            String xpath = DriverScript.prop.getProperty(arguments[1]);
            WebElement locator = Getlocator(driver, xpath);
            Select oSelect = new Select(locator);
            if (locator != null) {
                oSelect.selectByVisibleText(arguments[2]);
                System.out.println("Selected the element with visibletest " + " " + arguments[2]);
                checkstatus = true;
            }
        } catch (Exception e) {
            System.out.println("exception value : " + e.getMessage());
        }
        return checkstatus;

    }

    public static boolean Selectvalue(WebDriver webdriver, String parameters)
            throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
        WAITTIME("WAITTIME->3");
        String[] arguments = null;
        boolean checkstatus = false;
        String sValue = null;
        arguments = splitfunction(parameters, "->");
        try {
            String xpath = DriverScript.prop.getProperty(arguments[1]);
            WebElement locator = Getlocator(driver, xpath);
            Select oSelect = new Select(locator);
            if (locator != null) {
                oSelect.selectByValue(arguments[2]);
                System.out.println("Selected the element with SelectValue " + " " + arguments[2]);
                checkstatus = true;
            }
        } catch (Exception e) {
            System.out.println("exception value : " + e.getMessage());
        }
        return checkstatus;

    }

    public static boolean scrollDown(WebDriver WebDriver, String parameters) {

        try {
            WebElement DIVelement = Getlocator(WebDriver, parameters);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].scrollIntoView(true)", DIVelement);
            return true;
        } catch (Exception e) {
            System.out.println("unable to find the locator for scroll Down " + " " + e.getMessage());
            return false;
        }
    }

    public static boolean LAUNCHBROWSER(WebDriver WebDriver, String parameters) {

        try {
            String browser = PropertyReader.getProperty(PropertyFileEnum.GLOB, "browser");
            url = PropertyReader.getProperty(PropertyFileEnum.GLOB, "url");
            switch (browser.toUpperCase()) {

                case "IEEXPLORER":
                    //System.setProperty("webdriver.ie.driver", workingDirectory + "/JavaJarfiles/IEDriverServer.exe");
                    //driver = new InternetExplorerDriver();
                    driver = init_driver("IEEXPLORER");
                    driver.get(url);
                    driver.manage().window().maximize();
                    actualurl = driver.getCurrentUrl();
                    break;
                case "FIREFOX":
                    driver = init_driver("FIREFOX");
                    driver.get(url);
                    driver.manage().window().maximize();
                    actualurl = driver.getCurrentUrl();
                    break;
                case "CHROME":
                    driver = init_driver("chrome");
                    driver.get(url);
                    driver.manage().window().maximize();
                    driver.manage().deleteAllCookies();
                    driver.navigate().refresh();
                    actualurl = driver.getCurrentUrl();
                    break;

                case "HEADLESS":
                    System.setProperty("webdriver.chrome.driver", workingDirectory + "/JavaJarfiles/chromedriver.exe");
                    options = new ChromeOptions();
                    options.addArguments("disable-infobars");
                    options.addArguments("--window-size=1920,1080");
                    options.addArguments("--allow-insecure-localhost");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--no-sandbox");
                    driver.get(url);
                    driver.manage().window().maximize();
                    driver.manage().deleteAllCookies();
                    driver.navigate().refresh();
                    actualurl = driver.getCurrentUrl();
                    break;

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public static WebElement getSelector(String selectBy, String objectReference) {
        WebElement selector = null;
        switch (selectBy) {
            case "xpath":
                selector = getWebDriver().findElement(By.xpath(objectReference));
                break;
            case "cssSelector":
                selector = getWebDriver().findElement(By.cssSelector(objectReference));
                break;
            case "id":
                selector = getWebDriver().findElement(By.id(objectReference));
                break;
            case "className":
                selector = getWebDriver().findElement(By.className(objectReference));
                break;
            case "name":
                selector = getWebDriver().findElement(By.name(objectReference));
                break;
            default:
                Assert.assertFalse(false, "Unable to Find Element");
        }
        return selector;
    }

}
