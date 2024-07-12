package baseRepo;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.IOException;

public class ExtentReportManager {
    public static ExtentReports extent;
    private static ExtentTest test;

    public static ExtentReports getExtentReports() {
        if (extent == null) {
            // Set up the HTML Reporter with additional customizations
            ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("report/extent/extent-report.html");
            htmlReporter.config().setTheme(Theme.STANDARD);
            htmlReporter.config().setDocumentTitle("Test Automation Report");
            htmlReporter.config().setReportName("Test Execution Report");
            htmlReporter.config().setCss(".r-img {width: 50%;}");
            htmlReporter.config().setJs("$(document).ready(function() { $('header').css('background-color', '#2196F3'); });");

            // Create ExtentReports and attach the reporter
            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("User", "Vaibhav Garse");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        }
        return extent;
    }

    public static ExtentTest createTest(String testName, String description) {
        test = getExtentReports().createTest(testName, description);
        return test;
    }

    public static void logInfo(String message) {
        if (test != null) {
            test.info(message);
        }
    }

    public static void logPass(String message) {
        if (test != null) {
            test.pass(message);
        }
    }

    public static void logFail(String message) {
        if (test != null) {
            test.fail(message);
        }
    }

    public static void addScreenshot(String path) throws IOException {
        if (test != null) {
            test.addScreenCaptureFromPath(path);
        }
    }

    public static void flush() {
        if (extent != null) {
            logInfo("Open browser closed successfully !");
            extent.flush();
        }
    }
}
