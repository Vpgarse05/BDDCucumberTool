package testSteps;

import baseRepo.ExtentReportManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import objectRepo.ObjectRepository;
import testScriptModules.HomePage;
import testScriptModules.LoginPage;
import utilities.FilePath;
import utilities.R;

public class LoginSteps {

    @Given("User should be on CX Choice login page.")
    public void user_should_be_on_cx_choice_login_page() {
        LoginPage login = new LoginPage();
        login.validateLoginScreen();
        ExtentReportManager.logInfo("Navigated to login page Successfully!");
    }

    @Given("User enter user id {string} on email username text field.")
    public void user_enter_user_id_on_email_username_text_field(String email) {
        R r = new R(FilePath.TestDataProp);
        email = r.getPropValue("stageEmail");
        LoginPage login = new LoginPage();
        login.userEnterEmail(email);
        ExtentReportManager.logInfo("Navigated to login page and enter email id : " + email);
    }

    @Given("User enter password {string} on password text field.")
    public void user_enter_password_on_password_text_field(String password) {
        R r = new R(FilePath.TestDataProp);
        password = r.getPropValue("stagePassword");
        LoginPage login = new LoginPage();
        login.userEnterPassword(password);
        ExtentReportManager.logInfo("Enter password : " + password);
    }

    @Given("User click on the login button.")
    public void user_click_on_the_login_button() {
        LoginPage login = new LoginPage();
        login.userClickOnLoginButton();
        ExtentReportManager.logPass("Clicked on login button !");
    }

    @Then("Verify user should be able to log in to the CX Choice.")
    public void verify_user_should_be_able_to_log_in_to_the_cx_choice() {
        HomePage home = new HomePage();
        home.validateHomeNavigation();
    }

}
