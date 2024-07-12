package utilities;

import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;

import static org.apache.commons.exec.util.StringUtils.split;

public class CommonFunctions {

	public static WebDriver driver;
	public static Connection conn;
	public final static HashMap<String, String> hmap = new HashMap<String, String>();
	public final static HashMap<String, Integer> TChmap = new HashMap<String, Integer>();
	public final static HashMap<String, String> TCstatushmap = new HashMap<String, String>();
	public final static HashMap<String, List> GroupMap = new HashMap<String, List>();
	private static final String MSEC_SINCE_EPOCH = null;
	private static int flag;
	public static Date odate;
	public static String Tcase;
	public static int timer;
	static DriverScript ds;
	public static String user;
	public static String Tsuitename;
	public static String suitename;
	public static String Tcasename;
	public static String updstpstatus = null;
	public static String envfilepath = null;
	public static String savedlocation = null;
	public static String Tstep = "Tstep";
	public static String locator;
	public static Element prjElement = null;
	public static Element tsElement = null;
	public static Element tcElement = null;
	public static Element tstpElement = null;
	public static String SCellData;
	public static ChromeOptions options;
	public static String muldatastatus;
	public static String mulpledata;
	public static String url;
	public static Date date;
	public static DateFormat formatter;
	public static String today;
	public static String[] dateformat;
	public static String finaldate;
	public static String dat;
	public static int fcolval;
	public static String value;
	public static String data;
	public static String storedatavalue;
	public static Boolean storedataflag = null;
	public static boolean sendmail = false;
	public static int Tcasecount = 0;
	public static String TCasenames;
	public static String exsuitestatus = null;
	public static String exduration = null;
	public static String display;
	public static String exelcasefinalstatus;
	public static int casecount = 0;
	public static int sanitycnt = 1;
	public static int tempnum = 0;
	public static String casestatus;
	public static String Esuitename = null;
	public static String Esuiteduration;
	public static String ESuitestatus;
	public static int TCpasscount = 0;
	public static int TCfailcount = 0;
	public static String Stpname = null;
	public static String releasename;
	public static String environmentname;
	public static String mailgroup;
	public static String Customername;
	public static String Typeofrun;
	public static String sheetsuitename;
	public static String sheetenvironment;
	public static String temp = null;
	public static HashMap<String, List> Typemap = new HashMap<String, List>();
	public static String mailsuite = null;
	public static String stppass = null;
	public final static HashMap<String, String> hpass = new HashMap<String, String>();
	public static HashMap<String, List> teststepsmap = new HashMap<String, List>();
	public static ArrayList<String> teststepslist = new ArrayList<String>();
	public static ArrayList<String> teststeps = new ArrayList<String>();
	public static HashMap<String, List> xmlcases = new HashMap<String, List>();
	public static ArrayList<String> xmlsteps = new ArrayList<String>();
	public static HashMap<String, String> mailcontent = new HashMap<String, String>();
	public static String mailendtime = null;
	public static String mailstarttime = null;
	public static String ESuiteDuration = null;
	public static String day = null;
	public static String runsuser = null;
	public static String ExecutionMode;
	public static String suiterelease = null;
	public static String suiteenvurl = null;
	public static String summarypath = null;
	public static String wait = "1";

	public enum ActionTypes {

		TAB, ENTER, F12, DOWN, ESCAPE, ORGANIZATION
	}

	public static String readpath() throws IOException {

		String workingDirectory = new File(".").getCanonicalPath();
		try {
			System.out.println(workingDirectory);
			return workingDirectory;
		} catch (Exception e) {

			// e.printStackTrace();
			// System.out.println(e.getMessage());
		}
		return workingDirectory;
	}

	public static String selectfile() throws InterruptedException, AWTException, IOException, ClassNotFoundException,
			SQLException, InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {

		String filePath = null;
		File newfile = null;
		JFileChooser chooser = new JFileChooser();
		// chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		File chooserFile = new File((new File(".").getCanonicalPath()));
		newfile = chooserFile.getAbsoluteFile();
		String name = newfile + "\\TestSuites";
		File finalfile = new File(name);
		chooser.setCurrentDirectory(finalfile);
		try {
			int returnValue = chooser.showOpenDialog(null);
			File file = null;
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				file = chooser.getSelectedFile();
			}
			if (file != null) {
				filePath = file.getPath();
				Tsuitename = file.getName();
				Tsuitename = Tsuitename.replace(".xlsx", "");
				if (suitename == null) {
					suitename = "suitename";
				}
				hmap.put(suitename, Tsuitename);
				FOLDERSTRUCTURE(Tsuitename);

			} else {
				System.out.println("File not selected");
			}
		} catch (Exception e) {
			System.out.println("exception value : " + e.getMessage());
		}
		return filePath;
	}

	public static void FOLDERSTRUCTURE(String suitename)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
		try {
			final long MSEC_SINCE_EPOCH = System.currentTimeMillis();
			String workingDirectory = new File(".").getCanonicalPath();
			String opath = workingDirectory;
			opath = opath.replace("IRScripts", "");
			opath = opath.replace('\\', '/');
			envfilepath = opath + "/EnvironmentalFiles/EnvironmentVariables.xlsx";
			File srcDir = new File(opath + "/HTMLTemplates/");
			File destDir = new File(opath + "/Reports/");
			// Calendar calendar = Calendar.getInstance();
			// Date now = calendar.getTime();
			Date instant = new Date(MSEC_SINCE_EPOCH);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:hh:mm:ss");
			String time = sdf.format(instant);
			time = time.replace(':', '-');
			suitename = suitename + "_" + time;
			mailsuite = suitename;
			File dir = new File(opath + "/Reports/" + suitename);
			dir.mkdir();
			FileUtils.copyDirectory(srcDir, dir);
			savedlocation = opath + "/Reports/" + suitename;
		} catch (Exception e) {
			System.out.println("exception value : " + e.getMessage());
		}
	}

	public static boolean SENDKEYS(WebDriver webdriver, String parameters)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {

		boolean status = true;
		try {
			Robot robot = new Robot();
			Actions action = new Actions(driver);
			String[] arguments = null;
			arguments = splitfunction(parameters, "->");

			ActionTypes actTypes = ActionTypes.valueOf(arguments[1].trim());
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
		}
		return status;
	}

	public static String GENERATE_UNIQVALUE(String Parameters) throws InterruptedException, IOException {

	}

	public static String[] splitFunction(String keyparameters, String symbol)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
		String[] parameters = null;
		try {

			if (symbol == "+") {
				symbol = "\\+";
			} else if (symbol == "|") {
				symbol = "\\|";
			} else if (symbol == "*") {
				symbol = "\\*";
			}
			parameters = keyparameters.split(symbol);
			return parameters;

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		return parameters;
	}

	public static boolean LAUNCHBROWSER(WebDriver WebDriver, String parameters)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {

		try {

			String workingDirectory = new File(".").getCanonicalPath();
			String actualurl = null;

			String[] arguments = splitfunction(parameters, "->");
			String[] args = splitfunction(arguments[2], ",");
			String browser = arguments[1];
			if (hmap.containsKey(args[0])) {
				url = hmap.get(args[0]);
			} else {
				url = args[0];
			}

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
				workingDirectory = new File(".").getCanonicalPath();
				String dpath = workingDirectory;
				File file = new File(dpath + "\\Savedoutput");
				String[] myFiles;
				if (file.isDirectory()) {
					myFiles = file.list();
					for (int i = 0; i < myFiles.length; i++) {
						File myFile = new File(file, myFiles[i]);
						myFile.delete();
					}
				}

                //System.setProperty("webdriver.gecko.driver", workingDirectory + "/JavaJarfiles//geckodriver.exe");
				FirefoxProfile profile = new FirefoxProfile();
				FirefoxOptions ffoptions = new FirefoxOptions();
				profile.setPreference("browser.download.dir", dpath + "\\Savedoutput");
				profile.setPreference("browser.download.folderList", 2);
				// profile.setPreference("browser.helperApps.alwaysAsk.force", "false");
				profile.setPreference("browser.helperApps.neverAsk.saveToDisk",
						"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/x-excel, application/x-msexcel, application/excel, application/vnd.ms-excel,application/xls;");
				// profile.setPreference("browser.download.manager.showWhenStarting", "false");
				profile.setPreference("pdfjs.disabled", "true");
				//driver = new FirefoxDriver((Capabilities) profile);
				ffoptions.setProfile(new FirefoxProfile());
				//driver = new FirefoxDriver(ffoptions);
			   // String remoteurlfirefox = "http://localhost:4446/wd/hub";
				//driver = new RemoteWebDriver(new URL(remoteurlfirefox), ffoptions);
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
				if (!(driver instanceof WebStorage)) {
					throw new IllegalArgumentException("This test expects the driver to implement WebStorage");
				}

				break;
			
		case "HEADLESS":
			System.setProperty("webdriver.chrome.driver", workingDirectory + "/JavaJarfiles/chromedriver.exe");
			options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.setHeadless(true);
			options.addArguments("--window-size=1920,1080");
		    options.addArguments("--allow-insecure-localhost");
		    options.addArguments("--disable-gpu");
		    options.addArguments("--no-sandbox");
			driver.get(url);
			actualurl = driver.getCurrentUrl();
			if (!(driver instanceof WebStorage)) {
				throw new IllegalArgumentException("This test expects the driver to implement WebStorage");
			}
//		    webStorage = (WebStorage) driver;
//			webStorage.getSessionStorage().clear();
//			webStorage.getLocalStorage().clear();
			break;

		}

			if (url.equals(url)) {
				WAITTIME("waittime->3");
			//	login(driver, args[1] + "->" + args[2]);
				//ssslogin(driver, args[1] + "->" + args[2]);
				return true;
			} else {
				return false;

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
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

	// ************************************************************************************************************************
	/*
	 * Function Name:WAITTIME
	 * Stops execution for given time(in secs) 
	 * Author: Murali
	 * Created On: 13-04-2021 Version:1 Parameters (Keyword, Time) (WAITTIME 3)
	 */
//*************************************************************************************************************************
	public static boolean WAITTIME(String parameters)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
		String[] arguments = null;
		arguments = splitfunction(parameters, "->");
		try {
			int sleepTime = Integer.valueOf(arguments[1].trim()) * 500;
			Thread.sleep(sleepTime);
			return true;
		} catch (Exception e) {
			return false;
		}

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
			if(xpath.contains("+")) {
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

	public static WebElement Getlocator(WebDriver WebDriver, String parameters)
	{
		//// RecoveryScenarios();
		WebElement locpath = null;
		try {

			String[] arguments = null;
			boolean b;
			b = parameters.matches(".*:.*");

			if (b) {
				if(parameters.contains("-=")) {
					arguments = splitfunction(parameters, "-=");	
				} else {
				arguments = splitfunction(parameters, ":");
				}
			} else {
				arguments = splitfunction(parameters, "\\|");
			}

			String mode = arguments[0];
			arguments[0].trim();
			locator = arguments[1];

			switch (mode.toLowerCase(Locale.ROOT)) {

			case "id":
				locpath = driver.findElement(By.id(locator));
				break;
			case "name":
				locpath = driver.findElement(By.name(locator));
				break;
			case "linkText":
				locpath = driver.findElement(By.linkText(locator));
				break;
			case "xpath":
				locpath = driver.findElement(By.xpath(locator));
				break;
			case "cssSelector":
				locpath = driver.findElement(By.cssSelector(locator));
				break;
			case "partialLinkText":
				locpath = driver.findElement(By.partialLinkText(locator));
				break;
			case "className":
				locpath = driver.findElement(By.className(locator));
				break;
			case "tagName":
				locpath = driver.findElement(By.tagName(locator));
				break;

			}

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("unable to find the locator" + " " + locator);
		}

		return locpath;

	}

	public static boolean ENTERVALUE(WebDriver WebDriver, String parameters)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
		String[] arguments = null;
		Boolean Status = true;
		String keyvalue = null;
		arguments = splitfunction(parameters, "\\->");
		try {
			String xpath = DriverScript.prop.getProperty(arguments[1]);
			if(xpath.contains("+")) {
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
					if (arguments[2].toUpperCase().contains("SYSDATE")) {
						String sysdat = Sysdate(arguments[2]);
						locator.click();
						WAITTIME("WAITTIME->2");
			        	locator.sendKeys(sysdat);
						System.out.println("Succesfully entered the Date" + " " + sysdat);
					} else if (hmap.containsKey(arguments[2])) {
						String value = hmap.get(arguments[2]);
						locator.click();
						WAITTIME("WAITTIME->2");
						locator.sendKeys(value);
						System.out.println("Succesfully entered the value" + " " + value);
					} else {
						locator.click();
						WAITTIME("WAITTIME->2");
						locator.sendKeys(arguments[2]);
						System.out.println("Succesfully entered the value" + " " + arguments[2]);
					}
				} else {
					System.out.println("not able to find" + " " + arguments[2]);
					Status = false;
				}
			} else {
				System.out.println("unable to find the xpath" + " " + arguments[1]);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("unable to find the locator" + " " + arguments[1]);
			Status = false;
		}
		return Status;
	}

	public static boolean VERIFYVALUE(WebDriver webdriver, String parameters)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
		flag = 1;
		String[] arguments = null;
		String keyvalue = null;
		arguments = splitfunction(parameters, "->");
		String value = null;
		try {
			if (hmap.containsKey(arguments[2])) {
				value = hmap.get(arguments[2]);
			} else {
				value = arguments[2];
			}

			String xpath = DriverScript.prop.getProperty(arguments[1]);
			if(xpath.contains("+")) {
				String[] newval = splitfunction(xpath, "+");
				
				if (hmap.containsKey(newval[1])) {
					keyvalue = hmap.get(newval[1]);
				}
				xpath = xpath.replace(newval[1], keyvalue);
				xpath = xpath.replace("+", "").trim();
			}
			if (xpath != null) {
				WebElement locator = Getlocator(driver, xpath);
				String ovalue = locator.getAttribute("value");
				if (ovalue == null || ovalue == "") {
					ovalue = locator.getText();
				}
				if (hmap.containsKey(arguments[2])) {
					value = hmap.get(arguments[2]);
				} else if (arguments[2].toUpperCase().contains("SYSDATE")) {
					String sysdat = Sysdate(arguments[2]);
					value = sysdat;
				} else if ((arguments[2].contains("+"))) {

					String[] values = splitfunction(arguments[3], "+");
					if (values.length > 1) {
						if ((hmap.containsKey(values[0]))
								&& (hmap.containsKey(values[1]) && (hmap.containsKey(values[2])))) {
							Float firstval = Float.parseFloat(hmap.get(values[0]));
							Float Secval = Float.parseFloat(hmap.get(values[1]));
							Float thirdval = Float.parseFloat(hmap.get(values[2]));
							value = String.valueOf(firstval + Secval + thirdval);
							hmap.put(arguments[2], value);
						} else {
							if ((hmap.containsKey(values[0])) && (hmap.containsKey(values[1]))) {
								Float firstval = Float.parseFloat(hmap.get(values[0]));
								Float Secval = Float.parseFloat(hmap.get(values[1]));
								value = String.valueOf(firstval + Secval);
								hmap.put(arguments[2], value);
							}
						}
					} else {

					}
				} else if ((arguments[2].contains("*"))) {

					String[] values = splitfunction(arguments[2], "*");
					if ((hmap.containsKey(values[0])) && (hmap.containsKey(values[1]))) {
						Float firstval = Float.parseFloat(hmap.get(values[0]));
						Float Secval = Float.parseFloat(hmap.get(values[1]));
						value = String.valueOf(firstval * Secval);
					} else {

					}
				} else {
					value = arguments[2];
				}
				if (value.equalsIgnoreCase("IS NOT NULL")) {
					int len = ovalue.length();
					if (len > 0) {

					} else {

						flag = 0;

					}
				} else {
					if (ovalue.equalsIgnoreCase(value)) {
						System.out.println("Both values Matched");

					} else {
						System.out.println("Values did not match");
						flag = 0;
					}
				}
			} else {
				System.out.println("unable to find the xpath" + " " + arguments[1]);
			}
		} catch (Exception e) {
			System.out.println("exception value : " + e.getMessage());
			flag = 0;
		}
		if (flag == 1) {
			return true;
		} else {
			return false;
		}
	}

	public static String Sysdate(String parameters) throws Exception {
		boolean flag = false;
		if (parameters.contains("sysdate,")) {
			dateformat = split(parameters, ",");
		} else {
			dateformat = split(parameters, ",");
			flag = true;
		}
		switch (dateformat[1].toUpperCase()) {
		case "DD-MM-YYYY":
			date = Calendar.getInstance().getTime();
			formatter = new SimpleDateFormat("dd-MM-yyyy");
			today = formatter.format(date);
			if (flag == true) {
				date = Calendar.getInstance().getTime();
				formatter = new SimpleDateFormat("dd-MM-yyyy");
				today = formatter.format(date);
				String[] odate = split(parameters, ",");
				if (parameters.contains("+")) {
					String[] addnum = split(odate[0], "+");
					dat = returndate(addnum[1], today, "-");
				} else if (parameters.contains("-")) {
					String[] addnum = split(odate[0], "-");
					dat = returnbackdate(addnum[1], today, "-");
				}
				today = dat;
			}
			break;
		case "MM-DD-YYYY":
			date = Calendar.getInstance().getTime();
			formatter = new SimpleDateFormat("MM-dd-yyyy");
			today = formatter.format(date);
			if (flag == true) {
				date = Calendar.getInstance().getTime();
				formatter = new SimpleDateFormat("dd-MM-yyyy");
				today = formatter.format(date);
				String[] odate = split(parameters, ",");
				if (parameters.contains("+")) {
					String[] addnum = split(odate[0], "+");
					dat = returndate(addnum[1], today, "-");
				} else if (parameters.contains("-")) {
					String[] addnum = split(odate[0], "-");
					dat = returnbackdate(addnum[1], today, "-");
				}
				String[] odat = split(dat, "-");
				String len = odat[1];
				int oval = Integer.parseInt(odat[1]);
				if ((oval <= 9) && (len.length() == 1)) {
					odat[1] = "0" + odat[1];
				}

				today = odat[1] + "-" + odat[0] + "-" + odat[2];
			}
			break;
		case "DD/MM/YYYY":
			date = Calendar.getInstance().getTime();
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			today = formatter.format(date);
			if (flag == true) {
				date = Calendar.getInstance().getTime();
				formatter = new SimpleDateFormat("dd/MM/yyyy");
				today = formatter.format(date);
				String[] odate = split(parameters, ",");
				if (parameters.contains("+")) {
					String[] addnum = split(odate[0], "+");
					dat = returndate(addnum[1], today, "/");
				} else if (parameters.contains("-")) {
					String[] addnum = split(odate[0], "-");
					dat = returnbackdate(addnum[1], today, "/");
				}
				today = dat;
			}
			break;
		case "MM/DD/YYYY":
			date = Calendar.getInstance().getTime();
			formatter = new SimpleDateFormat("MM/dd/yyyy");
			today = formatter.format(date);
			if (flag == true) {
				date = Calendar.getInstance().getTime();
				formatter = new SimpleDateFormat("dd-MM-yyyy");
				today = formatter.format(date);
				String[] odate = split(parameters, ",");
				if (parameters.contains("+")) {
					String[] addnum = split(odate[0], "+");
					dat = returndate(addnum[1], today, "-");
				} else if (parameters.contains("-")) {
					String[] addnum = split(odate[0], "-");
					dat = returnbackdate(addnum[1], today, "-");
				}
				String[] odat = split(dat, "-");
				String len = odat[1];
				int oval = Integer.parseInt(odat[1]);

				if ((oval <= 9) && (len.length() == 1)) {
					odat[1] = "0" + odat[1];
				}
				today = odat[1] + "/" + odat[0] + "/" + odat[2];
			}
			break;
		case "DD-MM-YY":
			date = Calendar.getInstance().getTime();
			formatter = new SimpleDateFormat("dd-MM-yy");
			today = formatter.format(date);
			if (flag == true) {
				date = Calendar.getInstance().getTime();
				formatter = new SimpleDateFormat("dd-MM-yy");
				today = formatter.format(date);
				String[] odate = split(parameters, ",");
				if (parameters.contains("+")) {
					String[] addnum = split(odate[0], "+");
					dat = returndate(addnum[1], today, "-");
					today = dat;
				} else if (parameters.contains("-")) {
					String[] addnum = split(odate[0], "-");
					dat = returnbackdate(addnum[1], today, "-");
					String[] odat = split(dat, "-");
					odat[2] = String.valueOf(odat[2]).substring(2);
					today = odat[0] + "-" + odat[1] + "-" + odat[2];
				}

			}
			break;
		case "MM-DD-YY":
			date = Calendar.getInstance().getTime();
			formatter = new SimpleDateFormat("MM-dd-yy");
			today = formatter.format(date);
			if (flag == true) {
				date = Calendar.getInstance().getTime();
				formatter = new SimpleDateFormat("dd-MM-yy");
				today = formatter.format(date);
				String[] odate = split(parameters, ",");
				if (parameters.contains("+")) {
					String[] addnum = split(odate[0], "+");
					dat = returndate(addnum[1], today, "-");
					String[] odat = split(dat, "-");
					today = odat[1] + "-" + odat[0] + "-" + odat[2];
				} else if (parameters.contains("-")) {
					String[] addnum = split(odate[0], "-");
					dat = returnbackdate(addnum[1], today, "-");
					String[] odat = split(dat, "-");
					odat[2] = String.valueOf(odat[2]).substring(2);
					today = odat[1] + "-" + odat[0] + "-" + odat[2];
				}

			}
			break;
		case "DD/MM/YY":
			date = Calendar.getInstance().getTime();
			formatter = new SimpleDateFormat("dd/MM/yy");
			today = formatter.format(date);
			if (flag == true) {
				date = Calendar.getInstance().getTime();
				formatter = new SimpleDateFormat("dd/MM/yy");
				today = formatter.format(date);
				String[] odate = split(parameters, ",");
				if (parameters.contains("+")) {
					String[] addnum = split(odate[0], "+");
					dat = returndate(addnum[1], today, "/");
					today = dat;
				} else if (parameters.contains("-")) {
					String[] addnum = split(odate[0], "-");
					dat = returnbackdate(addnum[1], today, "/");
					String[] odat = split(dat, "/");
					odat[2] = String.valueOf(odat[2]).substring(2);
					today = odat[0] + "/" + odat[1] + "/" + odat[2];

				}

			}
			break;
		case "MM/DD/YY":
			date = Calendar.getInstance().getTime();
			formatter = new SimpleDateFormat("MM/dd/yy");
			today = formatter.format(date);
			if (flag == true) {
				date = Calendar.getInstance().getTime();
				formatter = new SimpleDateFormat("dd/MM/yy");
				today = formatter.format(date);
				String[] odate = split(parameters, ",");
				if (parameters.contains("+")) {
					String[] addnum = split(odate[0], "+");
					dat = returndate(addnum[1], today, "/");
					String[] odat = split(dat, "/");
					today = odat[1] + "/" + odat[0] + "/" + odat[2];
				} else if (parameters.contains("-")) {
					String[] addnum = split(odate[0], "-");
					dat = returnbackdate(addnum[1], today, "/");
					String[] odat = split(dat, "/");
					odat[2] = String.valueOf(odat[2]).substring(2);
					today = odat[1] + "/" + odat[0] + "/" + odat[2];
				}

			}
			break;
		case "DD-MMM-YYYY":
			date = Calendar.getInstance().getTime();
			formatter = new SimpleDateFormat("dd-MMM-yyyy");
			today = formatter.format(date);
			if (flag == true) {
				date = Calendar.getInstance().getTime();
				formatter = new SimpleDateFormat("dd-MMM-yyyy");
				today = formatter.format(date);
				String[] odate = split(parameters, ",");
				if (parameters.contains("+")) {
					String[] addnum = split(odate[0], "+");
					dat = returndate(addnum[1], today, "-");
				} else if (parameters.contains("-")) {
					String[] addnum = split(odate[0], "-");
					dat = returnbackdate(addnum[1], today, "-");
				}
				today = dat;
			}
			break;
		case "DD/MMM/YYYY":
			date = Calendar.getInstance().getTime();
			formatter = new SimpleDateFormat("dd/MMM/yyyy");
			today = formatter.format(date);
			if (flag == true) {
				date = Calendar.getInstance().getTime();
				formatter = new SimpleDateFormat("dd/MMM/yyyy");
				today = formatter.format(date);
				String[] odate = split(parameters, ",");
				if (parameters.contains("+")) {
					String[] addnum = split(odate[0], "+");
					dat = returndate(addnum[1], today, "/");
				} else if (parameters.contains("-")) {
					String[] addnum = split(odate[0], "-");
					dat = returnbackdate(addnum[1], today, "/");
				}
				today = dat;
			}
			break;
		case "YYYY-MM-DD":
			date = Calendar.getInstance().getTime();

			formatter = new SimpleDateFormat("YYYY-MM-dd");
			today = formatter.format(date);
			if (flag == true) {
				date = Calendar.getInstance().getTime();
				formatter = new SimpleDateFormat("dd-MM-yyyy");
				today = formatter.format(date);
				String[] odate = split(parameters, ",");
				if (parameters.contains("+")) {
					String[] addnum = split(odate[0], "+");
					dat = returndate(addnum[1], today, "-");
					String[] odat = split(dat, "-");
					dat = odat[2] + "-" + odat[1] + "-" + odat[0];
				} else if (parameters.contains("-")) {
					String[] addnum = split(odate[0], "-");
					dat = returnbackdate(addnum[1], today, "-");
					String[] odat = split(dat, "-");
					dat = odat[2] + "-" + odat[1] + "-" + odat[0];
				}
				today = dat;
			}
			break;
		}
		System.out.println(today);
		return today;

	}

	public static String returndate(String num, String date, String s) throws Exception {
		int oyear = 0;
		int month = 0;
		String temp = null;
		Boolean flag = true;
		try {
			String[] formats = splitfunction(date, s);
			temp = formats[1];
			switch (formats[1].toUpperCase()) {
			case "JAN":
				formats[1] = "01";
				flag = false;
				break;
			case "FEB":
				formats[1] = "02";
				flag = false;
				break;
			case "MAR":
				formats[1] = "03";
				flag = false;
				break;
			case "APR":
				formats[1] = "04";
				flag = false;
				break;
			case "MAY":
				formats[1] = "05";
				flag = false;
				break;
			case "JUN":
				formats[1] = "06";
				flag = false;
				break;
			case "JUL":
				formats[1] = "07";
				flag = false;
				break;
			case "AUG":
				formats[1] = "08";
				flag = false;
				break;
			case "SEP":
				formats[1] = "09";
				flag = false;
				break;
			case "OCT":
				formats[1] = "10";
				flag = false;
				break;
			case "NOV":
				formats[1] = "11";
				flag = false;
				break;
			case "DEC":
				formats[1] = "12";
				flag = false;
				break;
			}
			int currdate = Integer.parseInt(num) + Integer.parseInt(formats[0]);
			if (currdate >= 31) {
				while (currdate >= 31) {
					if (formats[1].equalsIgnoreCase("08") || (Integer.parseInt(formats[1]) % 2 != 0)
							|| formats[1].equalsIgnoreCase("02") || formats[1].equalsIgnoreCase("12")) {
						if (formats[1].equalsIgnoreCase("02")) {
							currdate = currdate - 28;
							month = Integer.parseInt(formats[1]) + 1;
							if (month < 10) {
								formats[1] = 0 + String.valueOf(month);
							} else if ((month > 12)) {
								month = month % 12;
								oyear = oyear + 1;
								formats[1] = String.valueOf(month);
							} else if ((month >= 10) && (month <= 12)) {
								formats[1] = String.valueOf(month);
							}
						} else if (currdate == 31) {
							currdate = 31;
							month = Integer.parseInt(formats[1]);
							break;

						} else {
							currdate = currdate - 31;
							month = Integer.parseInt(formats[1]) + 1;
						}
						if (month < 10) {
							formats[1] = 0 + String.valueOf(month);
							if (formats[1].equalsIgnoreCase("02")) {
								if (currdate > 28) {
									currdate = currdate - 28;
									month = Integer.parseInt(formats[1]) + 1;
									formats[1] = String.valueOf(month);
								}
							}
						} else if ((month > 12)) {
							month = month % 12;
							formats[1] = String.valueOf(month);
							oyear = oyear + 1;
						} else if ((month >= 10) && (month <= 12)) {
							formats[1] = String.valueOf(month);
						}
					} else {
						currdate = currdate - 30;
						month = Integer.parseInt(formats[1]) + 1;
						if (month < 10) {
							formats[1] = 0 + String.valueOf(month);
						} else if ((month > 12)) {
							month = month % 12;
							formats[1] = String.valueOf(month);
							oyear = oyear + 1;
						} else if ((month >= 10) && (month <= 12)) {
							formats[1] = String.valueOf(month);
						}
					}
				}
				if (flag == false) {
					switch (formats[1]) {
					case "1":
						formats[1] = "JAN";
						break;
					case "2":
						formats[1] = "FEB";
						flag = false;
						break;
					case "3":
						formats[1] = "MAR";
						flag = false;
						break;
					case "4":
						formats[1] = "APR";
						flag = false;
						break;
					case "5":
						formats[1] = "MAY";
						flag = false;
						break;
					case "6":
						formats[1] = "JUN";
						flag = false;
						break;
					case "7":
						formats[1] = "JUL";
						flag = false;
						break;
					case "8":
						formats[1] = "AUG";
						flag = false;
						break;
					case "9":
						formats[1] = "SEP";
						flag = false;
						break;
					case "10":
						formats[1] = "OCT";
						flag = false;
						break;
					case "11":
						formats[1] = "NOV";
						flag = false;
						break;
					case "12":
						formats[1] = "DEC";
						flag = false;
						break;
					}
				}
				if (oyear == 0) {
					today = "0" + currdate + s + formats[1] + s + formats[2];
				} else if (oyear > 0) {
					int year = Integer.parseInt(formats[2]) + oyear;
					formats[2] = String.valueOf(year);
					today = "0" + currdate + s + formats[1] + s + formats[2];
				}
				if (currdate < 10) {
					today = "0" + currdate + s + formats[1] + s + formats[2];
				} else {
					today = currdate + s + formats[1] + s + formats[2];
				}
			} else {
				if (flag == false) {
					formats[1] = temp;
				}
				if (currdate < 10) {
					today = "0" + currdate + s + formats[1] + s + formats[2];
				} else {
					today = currdate + s + formats[1] + s + formats[2];
				}
				// today = currdate + s + formats[1] + s + formats[2];

			}
		} catch (Exception e) {

			// out.println("unable to find the locator" + " " + e.getMessage());
		}
		return today;
	}

	public static String returnbackdate(String num, String date, String s) throws Exception {
		int oyear = 0;
		int month;
		Boolean flag = true;
		String temp;
		String[] formats = split(date, s);
		temp = formats[1];
		switch (formats[1].toUpperCase()) {
		case "JAN":
			formats[1] = "01";
			flag = false;
			break;
		case "FEB":
			formats[1] = "02";
			flag = false;
			break;
		case "MAR":
			formats[1] = "03";
			flag = false;
			break;
		case "APR":
			formats[1] = "04";
			flag = false;
			break;
		case "MAY":
			formats[1] = "05";
			flag = false;
			break;
		case "JUN":
			formats[1] = "06";
			flag = false;
			break;
		case "JUL":
			formats[1] = "07";
			flag = false;
			break;
		case "AUG":
			formats[1] = "08";
			flag = false;
			break;
		case "SEP":
			formats[1] = "09";
			flag = false;
			break;
		case "OCT":
			formats[1] = "10";
			flag = false;
			break;
		case "NOV":
			formats[1] = "11";
			flag = false;
			break;
		case "DEC":
			formats[1] = "12";
			flag = false;
			break;
		}
		int currdate = Integer.parseInt(formats[0]) - Integer.parseInt(num);
		if (currdate >= 1) {
			today = currdate + s + formats[1] + s + formats[2];
		} else if (currdate <= 0) {
			while (currdate <= 0) {
				month = Integer.parseInt(formats[1]) - 1;
				if ((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10)
						|| (month == 12)) {
					currdate = 31 - Math.abs(currdate);
				} else if (month == 2) {
					if (Integer.parseInt(formats[2]) / 4 == 0) {
						currdate = 29 - Math.abs(currdate);
					} else {
						currdate = 28 - Math.abs(currdate);
					}
				} else {
					currdate = 30 - Math.abs(currdate);
				}

				if (month == 0) {
					month = 12;
					oyear = oyear + 1;
				}
				if (month < 10) {
					formats[1] = 0 + String.valueOf(month);
				} else {
					formats[1] = String.valueOf(month);
				}

			}
		}
		if (flag == false) {
			switch (formats[1]) {
			case "1":
				formats[1] = "JAN";
				break;
			case "2":
				formats[1] = "FEB";
				flag = false;
				break;
			case "3":
				formats[1] = "MAR";
				flag = false;
				break;
			case "4":
				formats[1] = "APR";
				flag = false;
				break;
			case "5":
				formats[1] = "MAY";
				flag = false;
				break;
			case "6":
				formats[1] = "JUN";
				flag = false;
				break;
			case "7":
				formats[1] = "JUL";
				flag = false;
				break;
			case "8":
				formats[1] = "AUG";
				flag = false;
				break;
			case "9":
				formats[1] = "SEP";
				flag = false;
				break;
			case "10":
				formats[1] = "OCT";
				flag = false;
				break;
			case "11":
				formats[1] = "NOV";
				flag = false;
				break;
			case "12":
				formats[1] = "DEC";
				flag = false;
				break;
			}
		}

		int year = Calendar.getInstance().get(Calendar.YEAR) - oyear;
		formats[2] = String.valueOf(year);
		if (Math.abs(currdate) < 10) {
			today = "0" + currdate + s + formats[1] + s + formats[2];
		} else {
			today = currdate + s + formats[1] + s + formats[2];
		}
		// System.out.println(today);
		return today;
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
			if(xpath.contains("+")) {
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
	
	public static boolean chkBox(WebDriver WebDriver, String parameters)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
		Boolean Status = true;
		String[] arguments = null;
		arguments = splitfunction(parameters, "->");
		try {
			WAITFORELEMENT(WebDriver, "WAITFORELEMENT->" + arguments[1]);
			String xpath = DriverScript.prop.getProperty(arguments[1]);
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
	
	
	public static boolean JSCLICK(WebDriver WebDriver, String parameters)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
		Boolean Status = true;
		String[] arguments = null;
		arguments = splitfunction(parameters, "->");
		try {
			String xpath = DriverScript.prop.getProperty(arguments[1]);
			if (xpath != null) {
				WebElement locator = Getlocator(driver, xpath);
				if (locator.isEnabled()) {
					String[] args = splitfunction(xpath, ":");
					WAITTIME("WAITTIME->5");
					clickElementUsingJavascriptExecutor(args[1]);
					WAITTIME("WAITTIME->5");
					System.out.println("Succesfully clicked on" + " " + arguments[1]);
				} else {
					System.out.println("unable to find" + " " + arguments[1]);
					Status = false;
				}
			} else {
				System.out.println("unable to find the xpath" + " " + arguments[1]);
			}
		} catch (Exception e) {
			System.out.println("unable to find the locator " +arguments[1]+ " " + e.getMessage());
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
		Boolean Status = false;
		String value;
		String[] values = null;
		String[] argum = null;
		String revalue = null;
		String revalue1 = null;
		Boolean flag = true;
		String newoval = null;
		Double num1;
		Double num2;
		try {
			String[] arguments = splitfunction(Parameters, "->");
			if ((arguments[2].contains("+"))) {
				argum = splitfunction(arguments[2], "+");
				if (((hmap.containsKey(argum[0]) == true))) {
					revalue = hmap.get(argum[0]);
					num1 = Double.parseDouble(String.valueOf(revalue));
				} else {
					num1 = Double.parseDouble(String.valueOf(argum[0]));
				}
				if (((hmap.containsKey(argum[1]) == true))) {
					revalue1 = hmap.get(argum[1]);
					num2 = Double.parseDouble(String.valueOf(revalue1));
				} else {
					num2 = Double.parseDouble(String.valueOf(argum[1]));
				}
				arguments[2] = String.valueOf(num1 + num2);
				hmap.put(arguments[1], arguments[2]);
				Status = true;
			} else if (arguments[2].contains("*")) {
				argum = splitfunction(arguments[2], "*");
				if (((hmap.containsKey(argum[0]) == true))) {
					revalue = hmap.get(argum[0]);
					num1 = Double.parseDouble(String.valueOf(revalue));
				} else {
					num1 = Double.parseDouble(String.valueOf(argum[0]));
				}
				if (((hmap.containsKey(argum[1]) == true))) {
					revalue1 = hmap.get(argum[1]);
					num2 = Double.parseDouble(String.valueOf(revalue1));
				} else {
					num2 = Double.parseDouble(String.valueOf(argum[1]));
				}
				arguments[2] = String.valueOf(num1 * num2);
				hmap.put(arguments[1], arguments[2]);
				Status = true;
			} else if ((arguments[2].contains("minus"))) {
				argum = splitfunction(arguments[2], "minus");
				if (((hmap.containsKey(argum[0]) == true))) {
					revalue = hmap.get(argum[0]);
					num1 = Double.parseDouble(String.valueOf(revalue));
				} else {
					num1 = Double.parseDouble(String.valueOf(argum[0]));
				}
				if (((hmap.containsKey(argum[1]) == true))) {
					revalue1 = hmap.get(argum[1]);
					num2 = Double.parseDouble(String.valueOf(revalue1));
				} else {
					num2 = Double.parseDouble(String.valueOf(argum[1]));
				}
				arguments[2] = String.valueOf(num1 - num2);
				hmap.put(arguments[1], arguments[2]);
				Status = true;
			} else {
				if (hmap.containsKey(arguments[2])) {
					arguments[2] = hmap.get(arguments[2]);
				}
				hmap.put(arguments[1], arguments[2]);
				Status = true;
			}

		} catch (Exception e) {
			Status = false;
		}
		return Status;
	}

	public static boolean GETTEXT(WebDriver webdriver, String parameters)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
		String ovalue = null;
		String key;
		Boolean Status = true;
		try {
			String[] arguments = null;
			arguments = splitfunction(parameters, "->");
			key = arguments[2];
			String xpath = DriverScript.prop.getProperty(arguments[1]);
			if (xpath != null) {
				WebElement locator = Getlocator(driver, xpath);
				ovalue = locator.getAttribute("value");
				if (ovalue == null || ovalue == "") {
					ovalue = locator.getText();
				}
				if (ovalue != null) {
					hmap.put(key, ovalue);
					System.out.println("captured the value" + "  " + arguments[2] + "   " + ovalue);
					Status = true;
				} else {
					System.out.println("value is null" + " " + ovalue);
					Status = false;
				}
			} else {
				System.out.println("unable to find the xpath" + " " + arguments[1]);
			}

		} catch (Exception e) {
			System.out.println("exception value : " + e.getMessage());
			Status = false;
		}
		return Status;

	}

	public static Boolean Upload_File(String Parameters)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
		Boolean Status = true;
		String workingDirectory = new File(".").getCanonicalPath();
		try {
			String[] arguments = splitfunction(Parameters, "->");
			String path = workingDirectory + "/" + arguments[1].trim();
			Thread.sleep(3000);
			Runtime.getRuntime().exec("wscript " + path + ".vbs");
			System.out.println("wscript " + path + ".vbs");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Status = false;
		}
		return Status;
	}

	public static boolean login(WebDriver WebDriver, String parameters)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
		Boolean Status = true;
		String username = DriverScript.prop.getProperty("cxusername");
		String pswd = DriverScript.prop.getProperty("cxpswd");
		String login = DriverScript.prop.getProperty("cxlogin");
		try {
			String[] arguments = null;
			arguments = splitfunction(parameters, "\\->");
			if (hmap.containsKey(arguments[0])) {
				arguments[0] = hmap.get(arguments[0]);
			}
			if (hmap.containsKey(arguments[1])) {
				arguments[1] = hmap.get(arguments[1]);
			}
			WAITFORELEMENT(WebDriver, "WAITFORELEMENT->cxusername");
			WebElement locator = Getlocator(driver, username);
			if (locator != null) {
				locator.sendKeys(arguments[0]);
				WebElement locato1 = Getlocator(driver, pswd);
				if (locato1 != null) {
					locato1.click();
					locato1.sendKeys(arguments[1]);
					// WAITTIME("waittime->3");
					WebElement locato2 = Getlocator(driver, login);
					if (locato2 != null) {
						locato2.click();
					} else {
						System.out.println("unable to find the login button");
					}

				} else {
					System.out.println("unable to find the password feild" + " " + arguments[1]);
				}
			} else {
				System.out.println("unable to find the username feild" + " " + arguments[0]);
			}

		} catch (Exception e) {

			Status = false;
			System.out.println(e.getMessage());
		}
		return Status;

	}
    
	public static boolean ssslogin(WebDriver WebDriver, String parameters)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
		Boolean Status = true;
		String username = DriverScript.mobileprop.getProperty("emailaddress");
		String pswd = DriverScript.mobileprop.getProperty("password");
		String submit = DriverScript.mobileprop.getProperty("Submit");
		String signin = DriverScript.mobileprop.getProperty("SignIn");
		try {
			String[] arguments = null;
			arguments = splitfunction(parameters, "\\->");
			if (hmap.containsKey(arguments[0])) {
				arguments[0] = hmap.get(arguments[0]);
			}
			if (hmap.containsKey(arguments[1])) {
				arguments[1] = hmap.get(arguments[1]);
			}
			//WAITFORELEMENT(WebDriver, "WAITFORELEMENT->"+submit);
			WebElement locator = Getlocator(driver, username);
			if (locator != null) {
				locator.sendKeys(arguments[0]);
				WebElement locato1 = Getlocator(driver, submit);
				if (locato1 != null) {
					locato1.click();
					WAITTIME("waittime->3");
					WebElement locato2 = Getlocator(driver, pswd);
					if (locato2 != null) {
						locato2.click();
						locato2.sendKeys(arguments[1]);
						WebElement locato3 = Getlocator(driver, signin);
						if (locato3 != null) {
							locato3.click();
						} else {
							System.out.println("unable to find SignIN button");
						}
					} else {
						System.out.println("unable to find the Password Feild");
					}

				} else {
					System.out.println("unable to find the password feild" + " " + arguments[1]);
				}
			} else {
				System.out.println("unable to find the username feild" + " " + arguments[0]);
			}

		} catch (Exception e) {

			Status = false;
			System.out.println(e.getMessage());
		}
		return Status;

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

	public static boolean SELECTVALUEDROPDOWN_CXchoice(WebDriver webdriver, String parameters)
			throws InterruptedException, AWTException, IOException, ClassNotFoundException, SQLException,
			InstantiationException, IllegalAccessException, Exception, StaleElementReferenceException {
		WAITTIME("WAITTIME->3");
		String[] arguments = null;
		boolean checkstatus = false;
		String sValue = null;
		String sValue1 = null;
		int index = 1;
		arguments = splitfunction(parameters, "->");
		if ((hmap.containsKey(arguments[2].trim()))) {
			arguments[2] = hmap.get(arguments[2].trim());
		}
		try {
			String xpath = DriverScript.prop.getProperty(arguments[1]);
			((JavascriptExecutor)driver).executeScript("jQuery('#staff-place-order-clientId').css('display','block')");
			((JavascriptExecutor)driver).executeScript("jQuery('#staff-place-order-productType').css('display','block')");
			((JavascriptExecutor)driver).executeScript("jQuery('#staff-place-order-docDelivery').css('display','block')");
			WebElement locator = Getlocator(driver, xpath);
			Select oSelect = new Select(locator);
			checkstatus=true;
			List<WebElement> elementCount = oSelect.getOptions();
			int iSize = elementCount.size();
			for (int i = 0; i < iSize; i++) {
				sValue = elementCount.get(i).getText();
				sValue1 = elementCount.get(i).getAttribute("value");
				if ((sValue.equalsIgnoreCase(arguments[2]) || (sValue1.equalsIgnoreCase(arguments[2] )))) {
					checkstatus = true;
					if (ISNUMERIC(arguments[2])) {
						if (arguments[2].length() == 1) {
							int oindex = Integer.parseInt(arguments[2]);
							oSelect.selectByIndex(oindex);
							System.out.println("Selected the element with index " + " " + arguments[2]);
						} else {
							oSelect.selectByValue(arguments[2]);
							System.out.println("Selected the value " + " " + arguments[2]);
						}
					} else {
						oSelect.selectByVisibleText(arguments[2]);
						System.out.println("Selected the text" + " " + arguments[2]);
					}
					break;
				}
			}
			if (checkstatus != true) {
				System.out.println("unable to find the element" + " " + arguments[2]);
			}
		} catch (Exception e) {
			System.out.println("exception value : " + e.getMessage());
		}
		return checkstatus;
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
					 if(locator!=null) {
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


	public static boolean ISNUMERIC(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	

		public static boolean captureOrderNumber(WebDriver webdriver, String parameters)
				throws StaleElementReferenceException, InstantiationException, IllegalAccessException, SQLException,
				Exception {
			Boolean Status = true;
			String arguments[] = null;
			String strvalue = null;
			try {
			arguments = splitfunction(parameters, "->");
			if ((CommonFunctions.hmap.containsKey(arguments[1]))) {
				strvalue = CommonFunctions.hmap.get(arguments[1]);
			} else {
				strvalue = arguments[1];
			}
			String[] message = splitfunction(strvalue, "Order");
			String[] args = splitfunction(message[1], "created");
			System.out.println("Sucessfully captured value order number "+args[0]);
			CommonFunctions.hmap.put(arguments[2], args[0]);
			} catch(Exception e) {
				System.out.println(e.getMessage());
				Status = false;
			}
			return Status;

		}
		
		  public static WebDriver init_driver(String browserName) {
		       // System.out.println( WebDriverManager.chromedriver().getDriverVersions());
		        Properties prop;
		        System.out.println("Browser lanuch is : " + browserName);
		        if (browserName.equalsIgnoreCase("chrome"))
				{
					WebDriverManager.chromedriver().clearDriverCache().setup();
		            driver = new ChromeDriver();
		        } else if (browserName.equalsIgnoreCase("firefox")) {
		            WebDriverManager.firefoxdriver().setup();
		            driver = new FirefoxDriver();
		        } else if (browserName.equalsIgnoreCase("ieexplorer")) {
		            WebDriverManager.iedriver();
		            driver = new InternetExplorerDriver();    
		        } else {
		            System.out.println("Browser is not supported " + browserName);
		        }

		        driver.manage().window().maximize();
		        driver.manage().deleteAllCookies();
		        return driver;
		    }

}
