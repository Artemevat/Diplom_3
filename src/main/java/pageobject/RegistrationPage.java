package pageobject;

import io.qameta.allure.Step;
import object.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static constants.BasicData.REGISTER_PAGE_URL;

public class RegistrationPage {

    private final WebDriver driver;
    //локатор поля ввода имя
    private final By nameField = By.xpath(".//label[text() = 'Имя']/../input[contains(@name, 'name')]");
    //локатор поля ввода email
    private final By emailField = By.xpath(".//label[text() = 'Email']/../input[contains(@name, 'name')]");
    //локатор поля ввода пароль
    private final By passwordField = By.xpath(".//label[text() = 'Пароль']/../input[contains(@type, 'password')]");
    //локатор кнопки зарегистрироваться
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    //локтор кнопки войти внизу страницы
    private final By signInButton = By.xpath(".//a[text()='Войти']");
    //локатор надписи Некорректный пароль
    private final By incorrectPassword = By.xpath(".//*[text()='Некорректный пароль']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openRegistrationPage() {
        driver.get(REGISTER_PAGE_URL);
    }

    @Step("Click registerButton")
    public void clickRegister() {
        driver.findElement(registerButton).click();
    }

    @Step("Enter email, name, password for registration")
    public void registerUser(User user) {
        driver.findElement(nameField).click();
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(user.getName());
        driver.findElement(emailField).click();
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(user.getEmail());
        driver.findElement(passwordField).click();
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(user.getPassword());
    }

    @Step("Displayed error when enter incorrect password")
    public boolean incorrectPasswordErrorDisplayed() {
        return driver.findElement(incorrectPassword).isDisplayed();
    }

    @Step("Click the login button at the bottom of the page")
    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }
}