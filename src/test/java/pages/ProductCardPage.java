package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductCardPage extends  Page{

    public ProductCardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSizesOnCard(){
        return isElementPresent(By.cssSelector("#box-product td.options"));
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickFirstSize(){
        WebElement w = driver.findElement(By.cssSelector("#box-product td.options"));
        w.click();
        w.findElement(By.cssSelector("option:nth-child(2)")).click();
    }

    public void addProductToCartAndReturnOnPreviousPage(int i){
        driver.findElement(By.cssSelector("#box-product button")).click();

        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("#cart > a.content > span.quantity")), Integer.toString(i)));

        driver.navigate().back();
    }
}
