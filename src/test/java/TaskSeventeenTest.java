import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskSeventeenTest extends TestBase {
    @Test
    public void successCheckEmptyBrowserConsoleLog() {
        openAndLoginByAdmin("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        By productRowsSelector = By.cssSelector("#content tr a[href*='category_id=1&product_id=']:not([title])");
        List<WebElement> productsArray = driver.findElements(productRowsSelector);

        for (int i = 0; i < productsArray.size(); i++) {
            productsArray.get(i).click();

            assertTrue(driver.manage().logs().get("browser").getAll().isEmpty(), "В консоли браузера есть сообщения");

            driver.navigate().back();
            productsArray = driver.findElements(productRowsSelector);
        }
    }
}
