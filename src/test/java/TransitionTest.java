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
import pageobject.ProfilePage;
import steps.UserSteps;

import static constants.UserRandomDate.*;

public class TransitionTest extends BrowserTest {
    public UserSteps userSteps;
    public String accessToken;
    public User user;
    private MainPage mainPage;
    @Before
    public void setUp() {
        setUpDriver(Browsers.CHROME);
        mainPage = new MainPage(this.driver);
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
    @DisplayName("Check transition by click button AccountButton")
    @Description("Check transition by click button AccountButton and displayed loginIndicator after transition")
    public void checkClickOnPersonalAccButton() {
        mainPage.openMainPage();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(this.driver);
        Assert.assertTrue(loginPage.loginIndicatorDisplayed());
    }

    @Test
    @DisplayName("Check transition by click ConstructorButton")
    @Description("Check transition by click ConstructorButton and displayed setBurger after transition")
    public void switchFromProfileByClickDesignerButton() {
        mainPage.openMainPage();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        ProfilePage profilePage = new ProfilePage(this.driver);
        profilePage.clickConstructorButton();
        Assert.assertTrue(mainPage.setBurgerDisplayedMainPage());
    }

    @Test
    @DisplayName("Check transition by click LogoButton")
    @Description("Check transition by click LogoButton and displayed setBurger after transition")
    public void switchFromProfileByClickLogoBurger() {
        mainPage.openMainPage();
        mainPage.clickAccountButton();
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        ProfilePage profilePage = new ProfilePage(this.driver);
        mainPage.clickAccountButton();
        profilePage.clickLogoButton();
        Assert.assertTrue(mainPage.setBurgerDisplayedMainPage());
    }

}
