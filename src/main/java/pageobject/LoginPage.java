package pageobject;

import io.qameta.allure.Step;
import object.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constants.BasicData.LOGIN_PAGE_URL;

public class LoginPage {
    private final WebDriver driver;
    //локатор текста Вход
    private final By loginIndicator = By.xpath(".//*[text()='Вход']");
    //локатор кнопки зарегистрироваться
    private final By registerButton = By.xpath(".//a[(@class = 'Auth_link__1fOlj' and text()= 'Зарегистрироваться')]");
    //локатор кнопки войти
    private final By signInButton = By.xpath(".//button[text()='Войти']");
    //локатор поля ввода почты
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    //локатор поля ввода пароля
    private final By passwordField = By.xpath(".//*[text()='Пароль']/following-sibling::input");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open login page")
    public void openLoginPage() {
        driver.get(LOGIN_PAGE_URL);
    }

    @Step("loginIndicator Displayed")
    public boolean loginIndicatorDisplayed() {
        return driver.findElement(loginIndicator).isDisplayed();
    }

    @Step("Click registerButton")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Enter email and password")
    public void enterEmailAndPassword(User user) {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.urlToBe(LOGIN_PAGE_URL));
        driver.findElement(emailField).click();
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(user.getPassword());
    }

    @Step("Click signInButton")
    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }
}