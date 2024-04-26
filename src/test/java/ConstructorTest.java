import constants.Browsers;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;

import java.io.IOException;


public class ConstructorTest extends BrowserTest{
    private MainPage mainPage;
    @Before
    public void setUp() {
        setUpDriver(Browsers.CHROME);
        mainPage = new MainPage(this.driver);

    }
    @After
    public void tearDown() {
        takeScreenshot();
        driver.quit();
    }
    //добавлена пауза для визуального удобства отслеживания шагов
    @Test
    @DisplayName("Go to Bun")
    @Description("Go to Bun, check section name")
    public void goToBunByClick() {
        mainPage.openMainPage();
        mainPage.clickFillingButton();
        mainPage.clickBunButton();
        Assert.assertEquals("Булки", mainPage.getTextFromSelectedMenu());
    }

    @Test
    @DisplayName("Go to Sauce")
    @Description("Go to Sauce, check section name")
    public void goToSaucesByClick() {
        mainPage.openMainPage();
        mainPage.clickFillingButton();
        mainPage.clickSauceButton();
        Assert.assertEquals("Соусы", mainPage.getTextFromSelectedMenu());
    }

    @Test
    @DisplayName("Go to Filling")
    @Description("Go to Filling, check section name")
    public void goToFillingByClick() {
        mainPage.openMainPage();
        mainPage.clickSauceButton();
        mainPage.clickFillingButton();
        Assert.assertEquals("Начинки", mainPage.getTextFromSelectedMenu());
    }
}
