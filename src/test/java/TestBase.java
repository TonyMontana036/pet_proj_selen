import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class TestBase {
    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
    protected String url = "http://localhost/litecart";
    protected String urlAdmin = url + "/admin";
    protected WebDriver driver;

    @BeforeEach
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
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

    void openAndLoginByAdmin() {
        driver.get(urlAdmin);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean areElementPresents(By locator) {
        return !driver.findElements(locator).isEmpty();
    }
}
