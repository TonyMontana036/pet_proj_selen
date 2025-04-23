package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DefaultPage extends  Page{
    public DefaultPage(WebDriver driver) {
        super(driver);
    }

    public void defaultPageOpen(){
        driver.get("http://localhost/litecart/en/");
    }

    public boolean isOnThisPage() {
            return driver.findElements(By.cssSelector("#box-most-popular li:nth-child(1) > a.link")).size() > 0;
    }

    public void openFirstPopularProductCard(){
        driver.findElement(By.cssSelector("#box-most-popular li:nth-child(1) > a.link")).click();
    }
}
