package runner;

import baseRepo.BaseLib;
import baseRepo.ExtentReportManager;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

@CucumberOptions(
        features = "src/test/java/feature",
        glue = {"testSteps"},
        tags = "@VTC_01",
        monochrome = true,
        plugin = {"pretty",
                "html:report/cucumber-reports/cucumber.html",
                "json:report/cucumber-reports/cucumber.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        }
)

public class SingleWebRunner extends AbstractTestNGCucumberTests {
    BaseLib baseLib = new BaseLib();
    Scenario scenario;
    protected ExtentTest test;

    @BeforeMethod
    public void setup() {
        baseLib.setUP();
        test = ExtentReportManager.createTest("Setup Test", "Initialize WebDriver and open the URL");
    }

    @AfterMethod
    public void down(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            String screenshotPath = baseLib.takeScreenshot(result.getName());
            ExtentReportManager.logFail("Test Failed: " + result.getThrowable());
            ExtentReportManager.addScreenshot(screenshotPath);
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentReportManager.logPass("Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentReportManager.logInfo("Test Skipped: " + result.getThrowable());
        }
        baseLib.tearDown(scenario);
        ExtentReportManager.flush();
    }

}
