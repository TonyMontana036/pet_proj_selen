import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskFourteenTest extends TestBase {

    @Test
    public void successOpenNewWindowsAndCloseOnCountries() {
        openAndLoginByAdmin("http://localhost/litecart/admin/?app=countries&doc=countries");
        String originalWindow = driver.getWindowHandle();
        Set<String> exitingWindows = driver.getWindowHandles();

        assertTrue(areElementPresents(By.cssSelector("#content .row a")));
        driver.findElement(By.cssSelector("#content .row a")).click();

        List<WebElement> listOfLinks = driver.findElements(By.cssSelector("#content a[target=_blank]"));

        for (WebElement wEl : listOfLinks) {
            wEl.click();
            String newWindow = wait.until(anyWindowOtherThan(exitingWindows));  //Получаем идентификатор нового окна
            driver.switchTo().window(newWindow);                                //Переключаемся на новое окно

            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("title"))); //Ждем тайтл у страницы

            driver.close();                                                     //Закрываем новое окно
            driver.switchTo().window(originalWindow);                           //Переключаемся на первое окно
        }
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
        return driver -> {
            Set<String> handles = driver.getWindowHandles();
            handles.removeAll(oldWindows);
            return handles.size() > 0 ? handles.iterator().next() : null;
        };
    }
}
