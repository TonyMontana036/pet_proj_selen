import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestBase {
    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
    protected WebDriver driver;

    @BeforeEach
    public void start() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
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
}
