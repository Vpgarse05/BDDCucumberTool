package utilities;

import baseRepo.BaseLib;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper extends BaseLib{

    public static final int WAIT = 45;
    Properties configProp = new Properties();
    protected FileInputStream configFis;
    protected File file = new File("");
    private static final Logger Log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

    public void mouseHover(String selectBy, String objectReference) {
        Actions action = new Actions(getWebDriver());
        action.moveToElement(getSelector(selectBy,objectReference)).perform();

    }

    public void selectByVisibleText(String selectBy, String objectReference, String value) {
        Select select = new Select(getSelector(selectBy,objectReference));
        select.selectByVisibleText(value);
    }

    public void selectCheckBox(String selectBy, String objectReference) {
        WebElement element=getSelector(selectBy,objectReference);
        if (!element.isSelected()) {
            element.click();
        } else {
            Assert.assertFalse(true);
        }
    }

    /**
     * method to open specified url
     *
     * @param url to open
     */
    //Step to navigate to specified URL
    public void get(String url) {
        getWebDriver().get(url);
    }

    /**
     * method to navigate to specified page
     *
     * @param url navigation url
     */
    public void navigate(String url) {
        getWebDriver().navigate().to(url);
    }

    /**
     * method to click on an element with action class
     *
     * @param selectBy objectReference to be clicked
     */
    public void clickOnElementUsingActions(String selectBy, String objectReference) {
        WebElement element=getSelector(selectBy,objectReference);
        Actions actions = new Actions(getWebDriver());
        actions.moveToElement(element);
        actions.click().perform();
    }

    /**
     * method to click on an element using javascript
     *
     * @param selectBy to be clicked
     */
    public void clickOnElementUsingJs(String selectBy, String objectReference) {
        WebElement element=getSelector(selectBy,objectReference);
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("arguments[0].click();", element);
    }

    public void click(String selectBy, String objectReference) {
        WebElement element=getSelector(selectBy,objectReference);
        waitForElementToBeClickable(element);
        element.click();
    }

    public void check(String selectBy, String objectReference) {
        WebElement element=getSelector(selectBy,objectReference);
        sleepTime(1);
        element.click();
    }

    public String getText(String selectBy, String objectReference) {
        WebElement element=getSelector(selectBy,objectReference);
        waitForVisibility(element);
        String label = element.getText().trim();
        return label;
    }

    public boolean isElementDisplayed(String selectBy, String objectReference) {
        boolean status = waitForVisibility(getSelector(selectBy,objectReference));
        return status;
    }


    /**
     * method to get int part from a string
     *
     * @param getInt string passed
     * @return
     */
    public int getIntValue(String getInt) {
        Pattern intsOnly = Pattern.compile("\\d+");
        Matcher makeMatch = intsOnly.matcher(getInt);
        makeMatch.find();
        String inputInt = makeMatch.group();
        return Integer.parseInt(inputInt);
    }

    /**
     * method to get title of current webpage
     *
     * @return String name of a webpage
     */
    public String getTitle() {
        return getWebDriver().getTitle();
    }

    /**
     * method to wait until page is loaded completely
     *
     * @param PageName String name of current webpage
     */
    public void waitForPageToLoad(String PageName) {
        String pageLoadStatus;
        JavascriptExecutor js;
        do {
            js = (JavascriptExecutor) getWebDriver();
            pageLoadStatus = (String) js.executeScript("return document.readyState");
//            Log.info(".");
        } while (!pageLoadStatus.equals("complete"));
//        Log.info(PageName + " page loaded successfully");
    }

    /**
     * method verify whether element is present on screen
     *
     * @param selectBy element to be present
     * @return true if element is present else throws exception
     * @throws InterruptedException Thrown when a thread is waiting, sleeping,
     *                              or otherwise occupied, and the thread is interrupted, either before
     *                              or during the activity.
     */
    public Boolean isElementPresent(String selectBy, String objectReference) throws InterruptedException {
        WebElement targetElement=getSelector(selectBy,objectReference);
        Boolean isPresent = getWebDriver().findElements((By) targetElement).size() > 0;
        return isPresent;
    }

    /**
     * method verify whether element is not present on screen
     *
     * @param selectBy element not to be present
     * @return true if element is not present else throws exception
     * @throws InterruptedException Thrown when a thread is waiting, sleeping,
     *                              or otherwise occupied, and the thread is interrupted, either before
     *                              or during the activity.
     */
    public Boolean isElementNotPresent(String selectBy, String objectReference) throws InterruptedException {
        WebElement targetElement=getSelector(selectBy,objectReference);
        Boolean isPresent = (getWebDriver().findElements((By) targetElement).size() == 0);
        return isPresent;
    }

    /**
     * method to wait for an element to be visible
     *
     * @param targetElement element to be visible
     * @return true if element is visible else throws TimeoutException
     */
    public boolean waitForVisibility(WebElement targetElement) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(WAIT));
            wait.until(ExpectedConditions.visibilityOf(targetElement));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element is not visible: " + targetElement);
            System.out.println();
            System.out.println(e.getMessage());
            throw new TimeoutException();
        }
    }

    public boolean waitForAllElementsVisibility(List<WebElement> webElements) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(WAIT));
            wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element is not visible: " + webElements);
            System.out.println();
            System.out.println(e.getMessage());
            throw new TimeoutException();
        }
    }

    /**
     * Method to wait for an element to be clickable
     *
     * @param element element to be clickable
     * @param element element to be invisible
     * @param element element to be invisible
     * @return true if element is clickable else throws TimeoutException
     * <p>
     * method to wait for an element until it is invisible
     * @return true if element gets invisible else throws TimeoutException
     * <p>
     * method to find an element
     * @return WebElement if found else throws NoSuchElementException
     * <p>
     * method to wait for an element until it is invisible
     * @return true if element gets invisible else throws TimeoutException
     * <p>
     * method to find an element
     * @return WebElement if found else throws NoSuchElementException
     */
    public boolean waitForElementToBeClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(WAIT));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element is not clickable: " + element);
            System.out.println();
            System.out.println(e.getMessage());
            throw new TimeoutException();
        }
    }

    /**
     * Method to wait for an element until it is invisible
     *
     * @param selectBy element to be invisible
     * @return true if element gets invisible else throws TimeoutException
     */
    public boolean waitForInvisibility(String selectBy, String objectReference) {
        WebElement targetElement=getSelector(selectBy,objectReference);
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(WAIT));
            wait.until(ExpectedConditions.invisibilityOf(targetElement));
            return true;
        } catch (TimeoutException e) {
            System.out.println("Element is still visible: " + targetElement);
            System.out.println();
            System.out.println(e.getMessage());
            throw new TimeoutException();
        }
    }

    /**
     * Method to find an element
     *
     * @param locator element to be found
     * @return WebElement if found else throws NoSuchElementException
     */
    public WebElement findElement(By locator) {
        try {
            WebElement element = getWebDriver().findElement(locator);
            return element;
        } catch (NoSuchElementException e) {
//            Log.logError(this.getClass().getName(), "findElement", "Element not found " + locator);
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Method to find all the elements of specific locator
     *
     * @param locator element to be found
     * @return return the list of elements if found else throws NoSuchElementException
     */
    public List<WebElement> findElements(By locator) {
        try {
            List<WebElement> element = getWebDriver().findElements(locator);
            return element;
        } catch (NoSuchElementException e) {
//            Log.logError(this.getClass().getName(), "findElements", "element not found" + locator);
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * Method to match value with list elements and click on it
     *
     * @param fetchedListElements List of fetched value
     * @param valueToBeMatched    value to be matched with list elements
     */
    public void clickOnMatchingValue(List<WebElement> fetchedListElements, String valueToBeMatched) {
        for (WebElement element : fetchedListElements) {
            if (element.getText().equalsIgnoreCase(valueToBeMatched)) {
                element.click();
                return;
            }
            //System.out.println(element.getText() );
        }
    }

    /**
     * method to check value contained in list elements and click on it
     *
     * @param fetchedListElements List of fetched value
     * @param valueToBeContained  value to be contained in list elements
     */
    public void clickOnContainingValue(List<WebElement> fetchedListElements, String valueToBeContained) {
        for (WebElement element : fetchedListElements) {
            if (element.getText().toLowerCase().contains(valueToBeContained.toLowerCase())) {
                element.click();
                //System.out.println("Trying to select: "+valueToBeContained );
                return;
            }
            //		System.out.println(element.getText() );
        }
    }

    public static void runningShellCommand(String command) throws IOException, InterruptedException {
        Runtime run = Runtime.getRuntime();
        Process pr = run.exec(command);
        pr.waitFor();
        BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        String line = "";
        while ((line = buf.readLine()) != null) {
//            Log.info(line);
        }
    }

    /**
     * method to accept alert,
     * exception is thrown if no alert is present
     */
    public void acceptAlert() {
        try {
            Alert alert = getWebDriver().switchTo().alert();
            alert.accept();
        } catch (NoAlertPresentException e) {
            throw new NoAlertPresentException();
        }
    }

    /**
     * method to get message test of alert
     *
     * @return message text which is displayed
     */
    public String getAlertText() {
        try {
            Alert alert = getWebDriver().switchTo().alert();
            String alertText = alert.getText();
            alert.accept();
            return alertText;
        } catch (NoAlertPresentException e) {
            throw new NoAlertPresentException();
        }
    }

    /**
     * method to verify if alert is present
     *
     * @return returns true if alert is present else false
     */
    public boolean isAlertPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(getWebDriver(), Duration.ofSeconds(WAIT));
            wait.until(ExpectedConditions.alertIsPresent());
            getWebDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            throw new NoAlertPresentException();
        }
    }

    /**
     * method to select a value from dropdown with index
     *
     * @param selectBy  objectReference element with select tag
     * @param valueToBeSelectedIndex index to be selected
     */
    public void selectValueFromDropdownViaIndex(String selectBy, String objectReference, int valueToBeSelectedIndex) {
        WebElement e=getSelector(selectBy,objectReference);
        Select selectFromDropdown = new Select(e);
        selectFromDropdown.selectByIndex(valueToBeSelectedIndex);
    }

    /**
     * @param -WebElement
     * @return - non
     * @Method - clear
     * @Description - clear text
     */
    public void clear(String selectBy, String objectReference) {
        WebElement e=getSelector(selectBy,objectReference);
        waitForVisibility(e);
        e.clear();
    }

    /**
     * @param -WebElement,String message
     * @return - non
     * @Method - click
     * @Description - click on the webelement and print msg on console
     */
    public void click(String selectBy, String objectReference, String msg) {
        WebElement elm=getSelector(selectBy,objectReference);
        try {
            waitForVisibility(getSelector(selectBy,objectReference));
            Log.info(msg);
            elm.click();

        } catch (StaleElementReferenceException e) {
            waitForVisibility(elm);
            Log.info(msg);
            elm.click();

        }

    }

    /**
     * @param -WebElement,String Text & String message
     * @return - non
     * @Method - SendKeys
     * @Description - send text and print log msg on console
     */
    public void sendKeys(String selectBy, String objectReference, String txt) {
        WebElement e=getSelector(selectBy,objectReference);
        waitForVisibility(e);
        Log.info("Entered "+txt+" To "+e+" field successfully!");
        e.sendKeys(txt);
    }


    /**
     * @param -WebElement, String Attribute name
     * @return - non
     * @Method - getAttribute
     * @Description - get attribute of element
     */
    public String getAttribute(String selectBy, String objectReference, String attribute) {
        WebElement e=getSelector(selectBy,objectReference);
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }


    /**
     * @param -WebElement,boolean value & String  message
     * @return - non
     * @Method - softAssert
     * @Description - boolean assert verify
     */
    public void softAssert(String selectBy, String objectReference, Boolean value, String msg) {
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(getSelector(selectBy,objectReference).isDisplayed(), value.booleanValue(), msg);
        sa.assertAll();
    }

    /**
     * @param -WebElement
     * @return - non
     * @Method - scrollToElement
     * @Description - Scroll till the element view
     */
    public Status scrollToElement(String selectBy, String objectReference) {

        try {

            ((JavascriptExecutor) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", getSelector(selectBy,objectReference));
            return Status.PASS;
        } catch (Exception e) {

            Log.info(e + "Fail");
            return Status.FAIL;
        }
    }

    /**
     * @param -int seconds
     * @return - non
     * @Method - sleepTime
     * @Description - wait statically for seconds
     */
    public void sleepTime(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @param -web element
     * @return - non
     * @Method - jsClick
     * @Description - click on web element using java script executor
     */
    public void jsClick(String selectBy, String objectReference) {
        waitForVisibility(getSelector(selectBy,objectReference));
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        System.out.println("** going to click ");
        js.executeScript("arguments[0].click()", getSelector(selectBy,objectReference));
        System.out.println("**clicked");

    }

    /**
     * @param -web element
     * @return - non
     * @Method - jsScrollByElemen
     * @Description - Scroll element till the web element view
     */
    public void jsScrollByElement(String selectBy, String objectReference) {
        JavascriptExecutor js = (JavascriptExecutor) getWebDriver();
        js.executeScript("arguments[0].scrollIntoView();", getSelector(selectBy,objectReference));
    }

    /**
     * @param - non
     * @return - non
     * @Method - implicitlyWait
     * @Description - implicit wait for page load or element
     */
    public void implicitlyWait() {
        getWebDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    /**
     * @Method -
     * @Description - To select element using different selector properties
     * @author -
     * @DateCreated -
     */
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
