package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends Page {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void goToCartPage() {
        driver.findElement(By.cssSelector("a[href*='/checkout'].link")).click();
    }

    public int getSizeOfCart() {
        return driver.findElements(By.cssSelector("#order_confirmation-wrapper tr .item")).size() - 1;
    }

    public void deleteAllProductsFromCartByRemoveButton(int linesInCartTable, int itemsIncart) {
        while (itemsIncart > 0) {

            List<WebElement> cartArray = driver.findElements(By.cssSelector("#box-checkout-cart > ul li"));
            if (cartArray.size() > 1) {
                driver.findElement(By.cssSelector("#box-checkout-cart > ul li")).click();
            }

            try {
                WebElement elementRemoveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#box-checkout-cart button[name='remove_cart_item']")));
                elementRemoveButton.click();
            } catch (TimeoutException e) {

            }


            linesInCartTable = driver.findElements(By.cssSelector("#order_confirmation-wrapper tr .item")).size();

            try {
                wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#order_confirmation-wrapper tr .item"), linesInCartTable - 1));
            } catch (TimeoutException e) {

            }

            itemsIncart--;
        }
    }

    public boolean isEmptyCartTextPresent() {
        return isElementPresent(By.cssSelector("#checkout-cart-wrapper em"));
    }

    protected boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
