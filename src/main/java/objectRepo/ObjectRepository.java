package objectRepo;

public class ObjectRepository {


    public static class LoginPage {
        public static final String emailField = "username";
        public static final String passwordField = "password";
        public static final String loginButton = "//button[@class='btn success-color right w-100']";
    }

    public static class HomePage {
        public static final String profilePicture = "profilePicture";
        public static final String settingIcon = "iconSetting";
        public static final String alertIcon = "alertlink";
        public static final String logo = "//a[@class='brand-logo']";
        public static final String orders = "//a[text()='Orders']";
    }

    public static class SignUpPage {

    }
}
