package app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartPage;
import pages.DefaultPage;
import pages.ProductCardPage;

public class Application {

    private WebDriver driver;

    public DefaultPage defaultPage;
    public ProductCardPage productCardPage;
    public CartPage cartPage;

    public Application() {
        driver = new ChromeDriver();
        defaultPage = new DefaultPage(driver);
        productCardPage = new ProductCardPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit() {
        driver.quit();
    }
}
