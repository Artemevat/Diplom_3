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
    public void registrationOnRegPageSuccess() throws InterruptedException {
        user = new User(EMAIL, PASSWORD, NAME);
        RegistrationPage registrationPage = new RegistrationPage(this.driver);
        registrationPage.openRegistrationPage();
        registrationPage.registerUser(user);
        Thread.sleep(1000);
        registrationPage.clickRegister();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.enterEmailAndPassword(user);
        Thread.sleep(1000);
        loginPage.clickSignInButton();
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.orderButtonDisplayedMainPage());
    }

    @Test
    @DisplayName("Check registration with correct password(password > 5 characters)")
    @Description("Check registration with correct password with moving from LoginPage to RegistrationPage")
    public void registrationOnLoginPageSuccess() throws InterruptedException {
        user = new User(EMAIL, PASSWORD, NAME);
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.openLoginPage();
        Thread.sleep(1000);
        loginPage.clickRegisterButton();
        Thread.sleep(1000);
        RegistrationPage registrationPage = new RegistrationPage(this.driver);
        registrationPage.registerUser(user);
        Thread.sleep(1000);
        registrationPage.clickRegister();
        loginPage.enterEmailAndPassword(user);
        Thread.sleep(1000);
        loginPage.clickSignInButton();
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.orderButtonDisplayedMainPage());
    }

    @Test
    @DisplayName("Check registration with correct password(password > 5 characters)")
    @Description("Check registration with correct password with moving from MainPage to LoginPage to RegistrationPage")
    public void registrationOnMainPageSuccess() throws InterruptedException {
        user = new User(EMAIL, PASSWORD, NAME);
        MainPage mainPage = new MainPage(this.driver);
        mainPage.openMainPage();
        Thread.sleep(1000);
        mainPage.clickLogin();
        Thread.sleep(1000);
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.clickRegisterButton();
        RegistrationPage registrationPage = new RegistrationPage(this.driver);
        registrationPage.registerUser(user);
        Thread.sleep(1000);
        registrationPage.clickRegister();
        loginPage.enterEmailAndPassword(user);
        Thread.sleep(1000);
        loginPage.clickSignInButton();
        Assert.assertTrue(mainPage.orderButtonDisplayedMainPage());
    }

    @Test
    @DisplayName("Check registration with incorrect password (password < 5 characters)")
    @Description("Check registration with incorrect password in RegistrationPage")
    public void checkRegistrationWithWrongPassError() throws InterruptedException {
        user = new User(EMAIL, INCORRECT_PASSWORD, NAME);
        RegistrationPage registrationPage = new RegistrationPage(this.driver);
        registrationPage.openRegistrationPage();
        registrationPage.registerUser(user);
        Thread.sleep(1000);
        registrationPage.clickRegister();
        Assert.assertTrue(registrationPage.incorrectPasswordErrorDisplayed());
    }
}
