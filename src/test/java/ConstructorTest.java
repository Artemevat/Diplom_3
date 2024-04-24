import constants.Browsers;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pageobject.MainPage;



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
    public void goToBunByClick() throws InterruptedException {
        mainPage.openMainPage();
        mainPage.clickFillingButton();
        Thread.sleep(1000);
        mainPage.clickBunButton();
        Thread.sleep(1000);
        Assert.assertEquals("Булки", mainPage.getTextFromSelectedMenu());
    }

    @Test
    @DisplayName("Go to Sauce")
    @Description("Go to Sauce, check section name")
    public void goToSaucesByClick() throws InterruptedException {
        mainPage.openMainPage();
        mainPage.clickFillingButton();
        Thread.sleep(1000);
        mainPage.clickSauceButton();
        Thread.sleep(1000);
        Assert.assertEquals("Соусы", mainPage.getTextFromSelectedMenu());
    }

    @Test
    @DisplayName("Go to Filling")
    @Description("Go to Filling, check section name")
    public void goToFillingByClick() throws InterruptedException {
        mainPage.openMainPage();
        mainPage.clickSauceButton();
        Thread.sleep(1000);
        mainPage.clickFillingButton();
        Thread.sleep(1000);
        Assert.assertEquals("Начинки", mainPage.getTextFromSelectedMenu());
    }
}
