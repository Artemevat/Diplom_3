import constants.Browsers;
import object.Credentials;
import object.User;
import steps.UserSteps;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RegistrationPage;

import java.util.concurrent.TimeUnit;

import static constants.UserRandomDate.*;

public class RegistrationTest extends BrowserTest{
    private User user;
    @Before
    public void setUp() {
        setUpDriver(Browsers.CHROME);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    @After
    public void tearDown() {
        UserSteps userSteps = new UserSteps();
        Credentials credentials = new Credentials(user.getEmail(), user.getPassword());
        ValidatableResponse responseLogin = userSteps.login(credentials);
        String accessToken = userSteps.getToken(responseLogin);
        userSteps.deleteUserDataTests(accessToken);
        takeScreenshot();
        driver.quit();
    }

    @Test
    @DisplayName("Check registration with correct password(password > 5 characters)")
    @Description("Check registration with correct password in RegistrationPage")
    public void registrationOnRegPageSuccess() {
        user = new User(EMAIL, PASSWORD, NAME);
        RegistrationPage registrationPage = new RegistrationPage(this.driver);
        registrationPage.openRegistrationPage();
        registrationPage.registerUser(user);
        registrationPage.clickRegister();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.orderButtonDisplayedMainPage());
    }

    @Test
    @DisplayName("Check registration with correct password(password > 5 characters)")
    @Description("Check registration with correct password with moving from LoginPage to RegistrationPage")
    public void registrationOnLoginPageSuccess() {
        user = new User(EMAIL, PASSWORD, NAME);
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.openLoginPage();
        loginPage.clickRegisterButton();
        RegistrationPage registrationPage = new RegistrationPage(this.driver);
        registrationPage.registerUser(user);
        registrationPage.clickRegister();
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.orderButtonDisplayedMainPage());
    }

    @Test
    @DisplayName("Check registration with correct password(password > 5 characters)")
    @Description("Check registration with correct password with moving from MainPage to LoginPage to RegistrationPage")
    public void registrationOnMainPageSuccess() {
        user = new User(EMAIL, PASSWORD, NAME);
        MainPage mainPage = new MainPage(this.driver);
        mainPage.openMainPage();
        mainPage.clickLogin();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.clickRegisterButton();
        RegistrationPage registrationPage = new RegistrationPage(this.driver);
        registrationPage.registerUser(user);
        registrationPage.clickRegister();
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        Assert.assertTrue(mainPage.orderButtonDisplayedMainPage());
    }

    @Test
    @DisplayName("Check registration with incorrect password (password < 5 characters)")
    @Description("Check registration with incorrect password in RegistrationPage")
    public void checkRegistrationWithWrongPassError() {
        user = new User(EMAIL, INCORRECT_PASSWORD, NAME);
        RegistrationPage registrationPage = new RegistrationPage(this.driver);
        registrationPage.openRegistrationPage();
        registrationPage.registerUser(user);
        registrationPage.clickRegister();
        Assert.assertTrue(registrationPage.incorrectPasswordErrorDisplayed());
    }
}
