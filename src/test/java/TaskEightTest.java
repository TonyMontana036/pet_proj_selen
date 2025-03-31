import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.sort;
import static org.junit.jupiter.api.Assertions.*;

public class TaskEightTest extends TestBase {

    @Test
    public void successAlphabeticOrderCountries() {
        openAndLoginByAdmin("http://localhost/litecart/admin/?app=countries&doc=countries");
        String countriesQuery = "#content tr.row a:not([title='Edit']";
        By countriesCssQuery = By.cssSelector(countriesQuery);

        assertTrue(isElementPresent(countriesCssQuery), "Стран нет на странице");

        ArrayList<String> unSortedCountriesArray = new ArrayList<>();
        ArrayList<String> sortedCountriesArray;


        List<WebElement> arrayOfCountriesElements = driver.findElements(countriesCssQuery);
        for (WebElement wEl : arrayOfCountriesElements) {
            unSortedCountriesArray.add(wEl.getText());
        }

        sortedCountriesArray = new ArrayList<>(unSortedCountriesArray);
        sort(sortedCountriesArray);

        assertEquals(sortedCountriesArray, unSortedCountriesArray, "Страны не в алфавитном порядке");

    }

    @Test
    public void successAlphabeticOrderForTimeZones() {
        openAndLoginByAdmin("http://localhost/litecart/admin/?app=countries&doc=countries");
        String countriesQuery = "#content tr.row";
        By countriesCssQuery = By.cssSelector(countriesQuery);

        List<String> arrayCountiesWithTimeZone = new ArrayList<>();

        assertTrue(isElementPresent(countriesCssQuery), "Стран нет на странице");

        List<WebElement> arrayOfCountriesElements = driver.findElements(countriesCssQuery);
        for (WebElement wEl : arrayOfCountriesElements) {

            assertFalse(wEl.findElements(By.tagName("td")).isEmpty(), "У стран нет стобцов");

            if (Integer.parseInt(wEl.findElements(By.tagName("td")).get(5).getText()) > 0) {
                String countryWithTimeZone = wEl.findElements(By.tagName("td")).get(4).findElement(By.tagName("a")).getText();
                arrayCountiesWithTimeZone.add(countryWithTimeZone);
            }
        }

        for (String s : arrayCountiesWithTimeZone) {
            driver.findElement(By.xpath("//a[text()='" + s + "']")).click();

            List<WebElement> arrayUnSortedCountry = new ArrayList<>(driver.findElements(By.cssSelector("#table-zones tr")));
            deleteFirstAndLastWebElements(arrayUnSortedCountry);

            assertFalse(arrayUnSortedCountry.isEmpty(), "Отсутствуют зоны");

            ArrayList<String> arrayUnsortedZoneNames = new ArrayList<>();
            ArrayList<String> arraySortedZoneName;

            for (WebElement unsorted : arrayUnSortedCountry) {
                assertFalse(unsorted.findElements(By.tagName("td")).isEmpty(), "Отсутствуют зоны у страны");
                arrayUnsortedZoneNames.add(unsorted.findElements(By.tagName("td")).get(2).getText());
            }

            arraySortedZoneName = new ArrayList<>(arrayUnsortedZoneNames);
            sort(arraySortedZoneName);

            assertEquals(arraySortedZoneName, arrayUnsortedZoneNames, "Зоны " + s + " не в алфавитном порядке");

            driver.navigate().back();
        }
    }
}
