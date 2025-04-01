import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskNineTest extends TestBase {
    @Test
    public void successAlphabeticOrderGeoZones() {
        ArrayList<String> countriesWithZones = new ArrayList<>();

        openAndLoginByAdmin("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

        List<WebElement> arrayOfCountries = driver.findElements(By.cssSelector("#content tbody tr"));
        deleteFirstAndLastWebElements(arrayOfCountries);

        assertFalse(arrayOfCountries.isEmpty(), "Стран нет в списке");

        for (WebElement wEl : arrayOfCountries) {
            countriesWithZones.add(wEl.findElements(By.tagName("td")).get(2).getText());
        }

        for (String s : countriesWithZones) {
            driver.findElement(By.xpath("//a[text()='" + s + "']")).click();

            ArrayList<String> unsortedArrayOfZones = new ArrayList<>();

            List<WebElement> zones = driver.findElements(By.cssSelector("select[name^='zones'][name$='[zone_code]'] option[selected]"));

            for (WebElement wEl : zones) {
                unsortedArrayOfZones.add(wEl.getText());
            }

            ArrayList<String> sortedArrayOfZones = new ArrayList<>(unsortedArrayOfZones);
            sort(sortedArrayOfZones);

            assertEquals(sortedArrayOfZones, unsortedArrayOfZones, "Зоны " + s + " не в алфавитном порядке");

            driver.navigate().back();
        }
    }
}
