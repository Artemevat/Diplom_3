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

public class LogoutTest extends BrowserTest {
    public UserSteps userSteps;
    public String accessToken;
    public User user;
    @Before
    public void setUp() {
        setUpDriver(Browsers.CHROME);
        user = new User(EMAIL, PASSWORD, NAME);
        userSteps = new UserSteps();
        ValidatableResponse validatableResponse = userSteps.createUser(user);
        accessToken = userSteps.getToken(validatableResponse);
    }
    @After
    public void tearDown() {
        takeScreenshot();
        userSteps.deleteUserDataTests(accessToken);
        driver.quit();
    }

    @Test
    @DisplayName("Logout in ProfilePage")
    @Description("check successful logout and displayed loginIndicator after logout in ProfilePage")
    public void logoutSuccess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmailAndPassword(user);
        loginPage.clickSignInButton();
        mainPage.clickAccountButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogoutButton();
        Assert.assertTrue(loginPage.loginIndicatorDisplayed());
    }

}
