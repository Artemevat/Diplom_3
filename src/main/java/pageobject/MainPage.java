package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.BasicData.BASE_URL;

public class MainPage {

    private final WebDriver driver;
    //локатор текущего меню
    private final By currentMenu = By.xpath("//div[contains(@class,'tab_tab__1SPyG tab_tab_type_current__2BEPc')]");
    //локатор кнопки Войти в аккаунт
    private final By loginToAccount = By.xpath(".//button[text()='Войти в аккаунт']");
    //локатор кнопки Личный кабинет
    private final By accountButton = By.xpath(".//p[text()='Личный Кабинет']");
    //локатор кнопки Оформить заказ
    private final By orderButton = By.xpath(".//button[text()='Оформить заказ']");
    //локатор текста раздела Булки
    private final By bunsButton = By.xpath(".//span[contains(text(),'Булки')]");
    //локатор текста раздела Соусы
    private final By saucesButton = By.xpath(".//span[contains(text(),'Соусы')]");
    //локатор текста раздела Начинки
    private final By fillingButton = By.xpath(".//span[contains(text(),'Начинки')]");
    //локатор надписи Соберите бургер
    private final By setBurger = By.xpath(".//*[text()='Соберите бургер']");

    public MainPage(WebDriver driver) {

        this.driver = driver;
    }

    @Step("Open main page")
    public void openMainPage() {//открыть страницу сайта stellar burgers
        driver.get(BASE_URL);
    }

    @Step("click Login button")
    public void clickLogin() {
        driver.findElement(loginToAccount).click();
    }

    @Step("Check setBurger displayed in main page")
    public boolean setBurgerDisplayedMainPage() {
        return driver.findElement(setBurger).isDisplayed();
    }

    @Step("Check orderButton displayed in main page")
    public boolean orderButtonDisplayedMainPage() {
        return driver.findElement(orderButton).isDisplayed();
    }

    @Step("Click on the Personal Account button")
    public void clickAccountButton() {
        driver.findElement(accountButton).click();
    }

    @Step("Click on the bun button")
    public void clickBunButton() {
        driver.findElement(bunsButton).click();
    }

    @Step("Click on the sauces button")
    public void clickSauceButton() {
        driver.findElement(saucesButton).click();
    }

    @Step("Click on the fillings button")
    public void clickFillingButton() {
        driver.findElement(fillingButton).click();
    }

    @Step("Checking the text of the current menu")
    public String getTextFromSelectedMenu() {
        return driver.findElement(currentMenu).getText();
    }


}