import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskThirteenTest extends TestBase {

    @Test
    public void successAddAndDeleteProductsInCart() {
        int productCounter = 3;
        int exitCounter = 0;
        int cartSize;
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

        do {
            driver.navigate().refresh();
            cartSize = driver.findElements(By.cssSelector("#box-checkout-cart .items .item")).size();
            WebElement elementRemoveButton = driver.findElement(By.cssSelector(cssSelectorForRemoveButton));
            elementRemoveButton.click();

            if (cartSize < 2) {
                try {
                    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#box-checkout-cart > div")));
                    isCartNotEmty = false;
                } catch (TimeoutException ignored) {
                }
            }

            exitCounter++;
            if (exitCounter > productCounter) {
                isCartNotEmty = false;
            }

        } while (isCartNotEmty);

        assertTrue(isElementPresent(By.cssSelector("#checkout-cart-wrapper em")), "На странцие нет надписи об отсутствии товара");
    }
}
