import constants.Browsers;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import object.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.PasswordRecoveryPage;
import pageobject.RegistrationPage;
import steps.UserSteps;

import static constants.UserRandomDate.*;

public class LoginTest extends BrowserTest {
    public UserSteps userSteps;
    public String accessToken;
    public User user;
    @Before
    public void setUp() {
        setUpDriver(Browsers.CHROME);
        //создать пользователя, получить токен для дальнейшего удаления
        user = new User(EMAIL, PASSWORD, NAME);
        userSteps = new UserSteps();
        ValidatableResponse validatableResponse = userSteps.createUser(user);
        accessToken = userSteps.getToken(validatableResponse);
    }
    @After
    public void tearDown() {
        takeScreenshot();
        driver.quit();
        //удалить созданного пользователя
        userSteps.deleteUserDataTests(accessToken);
    }

    @Test
    @DisplayName("Login by click button SignIn in main page")
    @Description("check successful login and displayed orderButton after successful login")
    public void loginThroughSignInButton() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.openMainPage();
        mainPage.clickLogin();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        Assert.assertTrue(mainPage.orderButtonDisplayedMainPage());
    }

    @Test
    @DisplayName("Login by click button AccountButton in main page")
    @Description("check successful login and displayed orderButton after successful login")
    public void loginThroughPersonalAccountButton() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.openMainPage();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        Assert.assertTrue(mainPage.orderButtonDisplayedMainPage());
    }

    @Test
    @DisplayName("Login by click button SignIn in RegistrationPage")
    @Description("check successful login and displayed orderButton after successful login")
    public void loginThroughTheButtonInTheRegistrationForm() {
        RegistrationPage registrationPage = new RegistrationPage(this.driver);
        registrationPage.openRegistrationPage();
        registrationPage.clickSignInButton();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.orderButtonDisplayedMainPage());
    }

    @Test
    @DisplayName("Login by click button SignIn in RecoveryPage")
    @Description("check successful login and displayed orderButton after successful login")
    public void loginFromRecoveryPage() {
        PasswordRecoveryPage passRecoveryPage = new PasswordRecoveryPage(this.driver);
        passRecoveryPage.openRecoveryPage();
        passRecoveryPage.clickSignInButton();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        MainPage mainPage = new MainPage(this.driver);
        Assert.assertTrue(mainPage.orderButtonDisplayedMainPage());

    }
}
