package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage {
    private final WebDriver driver;
    //локатор кнопки Выход
    private final By logoutButton = By.xpath(".//button[text() = 'Выход']");
    //локатор кнопки Конструктор
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    //локатор логотипа
    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click logoutButton")
    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    @Step("Click constructorButton")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Click on the LogoButton")
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }
}