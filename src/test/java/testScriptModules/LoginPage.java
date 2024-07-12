package testScriptModules;

import baseRepo.BaseLib;
import objectRepo.ObjectRepository;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.Helper;

import java.lang.invoke.MethodHandles;

import static org.codehaus.groovy.vmplugin.v8.Selector.getSelector;

public class LoginPage extends BaseLib {
    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());
    Helper helper = new Helper();

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void validateLoginScreen() {
        helper.softAssert("id", ObjectRepository.LoginPage.emailField, true, "Email field verified Successfully!");
        helper.softAssert("id", ObjectRepository.LoginPage.passwordField, true, "Password field verified Successfully!");
    }

    public void userEnterEmail(String userName) {
        helper.sendKeys("id", ObjectRepository.LoginPage.emailField, userName);
    }

    public void userEnterPassword(String password) {
        helper.sendKeys("id", ObjectRepository.LoginPage.passwordField, password);
    }

    public void userClickOnLoginButton() {
        helper.click("xpath", ObjectRepository.LoginPage.loginButton);
    }
}
