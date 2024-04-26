import constants.Browsers;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class BrowserTest {
    protected WebDriver driver;

    public void setUpDriver(Browsers browserName) {
        if  (browserName == Browsers.CHROME) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        } else if (browserName == Browsers.YANDEX) {
            System.setProperty("webdriver.chrome.driver","C:\\WebDriver\\Yandex\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Users\\TArtemeva\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }

    public void takeScreenshot(){
        String path;
        try{
            WebDriver webDriver = new Augmenter().augment(driver);
            File source = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
            path = "C:\\AT\\screenshot" + source.getName();
            FileUtils.copyFile(source, new File(path));
        } catch (IOException ignored){
        }
    }
}
