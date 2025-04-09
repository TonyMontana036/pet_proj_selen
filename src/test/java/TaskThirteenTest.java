import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskThirteenTest extends TestBase {

    @Test
    public void successAddAndDeleteProductsInCart() {
        int productCounter = 3;
        int linesInCartTable = 0;
        int itemsIncart;
        boolean isCartNotEmty = true;

        String cssSelectorForRemoveButton = "#box-checkout-cart button[name='remove_cart_item']";

        driver.get(url);

        for (int i = 1; i < productCounter + 1; i++) {
            driver.findElement(By.cssSelector("#box-most-popular li:nth-child(1) > a.link")).click();


            if (isElementPresent(By.cssSelector("#box-product td.options"))) {
                WebElement w = driver.findElement(By.cssSelector("#box-product td.options"));
                w.click();
                w.findElement(By.cssSelector("option:nth-child(2)")).click();
            }

            driver.findElement(By.cssSelector("#box-product button")).click();

            wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("#cart > a.content > span.quantity")), Integer.toString(i)));

            driver.navigate().back();
        }

        driver.findElement(By.cssSelector("a[href*='/checkout'].link")).click();

        itemsIncart = driver.findElements(By.cssSelector("#order_confirmation-wrapper tr .item")).size() - 1;

        while (itemsIncart > 0) {
            List<WebElement> cartArray = driver.findElements(By.cssSelector("#box-checkout-cart > ul li"));
            if (cartArray.size() > 1) {
                driver.findElement(By.cssSelector("#box-checkout-cart > ul li")).click();
            }

            WebElement elementRemoveButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelectorForRemoveButton)));
            elementRemoveButton.click();

            linesInCartTable = driver.findElements(By.cssSelector("#order_confirmation-wrapper tr .item")).size();

            if (linesInCartTable == 2) {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#checkout-cart-wrapper em")));
            } else {
                wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("#order_confirmation-wrapper tr .item"), linesInCartTable - 1));
            }

            itemsIncart--;
        }

        assertTrue(isElementPresent(By.cssSelector("#checkout-cart-wrapper em")), "На странцие нет надписи об отсутствии товара");
    }
}
