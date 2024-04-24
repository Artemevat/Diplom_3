package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.BasicData.RECOVERY_PASSWORD_URL;

public class PasswordRecoveryPage {
    private final WebDriver driver;
    //локатор кнопки Войти на странице восстановления пароля
    private final By signInButtonRecoveryPage = By.xpath(".//a[text()='Войти']");
    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open recovery page")
    public void openRecoveryPage() {
        driver.get(RECOVERY_PASSWORD_URL);
    }

    @Step("Click SignInButton")
    public void clickSignInButton() {
        driver.findElement(signInButtonRecoveryPage).click();
    }
}