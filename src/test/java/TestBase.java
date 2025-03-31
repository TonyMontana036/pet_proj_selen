import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {
    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
    protected String url = "http://localhost/litecart";
    protected String urlAdmin = url + "/admin";
    protected WebDriver driver;

    protected void openAndLoginByAdmin() {
        openAndLoginByAdmin(this.urlAdmin); // используем поле класса как значение по умолчанию
    }


    @BeforeEach
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver();
        //driver= new FirefoxDriver();
        //driver = new InternetExplorerDriver();

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        threadLocal.set(driver);  // Сохраняем драйвер для текущего потока
    }


    @AfterEach
    public void stop() {
        WebDriver driver = threadLocal.get();
        if (driver != null) {
            driver.quit();
        }
        threadLocal.remove();  // Очищаем ThreadLocal
    }

    protected void openAndLoginByAdmin(String url) {
        driver.get(url);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean areElementPresents(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    protected static void deleteFirstAndLastWebElements(List<WebElement> arrayWebElements) {
        arrayWebElements.remove(arrayWebElements.size() - 1);
        arrayWebElements.remove(arrayWebElements.get(0));
    }
}
