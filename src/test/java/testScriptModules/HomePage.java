package testScriptModules;

import baseRepo.BaseLib;
import baseRepo.ExtentReportManager;
import objectRepo.ObjectRepository;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.Helper;

import java.lang.invoke.MethodHandles;

public class HomePage extends BaseLib {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    Helper helper = new Helper();

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public void validateHomeNavigation() {
        helper.softAssert("id", ObjectRepository.HomePage.profilePicture, true, "Profile picture verified Successfully!");
        helper.softAssert("xpath", ObjectRepository.HomePage.logo, true, "Logo verified Successfully!");
        ExtentReportManager.logInfo("Home screen validated successfully!");
    }
}
