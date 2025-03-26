import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskSixTest extends TestBase {

    @Test
    public void successClickAndCheckTitlePresentsLeftBoxLines() {
        openAndLoginByAdmin();
        String categoriesQuery = "span.fa-stack.fa-lg.icon-wrapper";
        By categoriesCssSelector = By.cssSelector(categoriesQuery);

        String subCategoriesQuery = "#app- > ul li";
        By subcategoriesCssSelector = By.cssSelector(subCategoriesQuery);

        List<WebElement> listOfCategories = driver.findElements(categoriesCssSelector);

        for (int i = 0; i < listOfCategories.size(); i++){

            listOfCategories = driver.findElements(categoriesCssSelector);
            listOfCategories.get(i).click();

            List<WebElement> listOfSubCategories = driver.findElements(subcategoriesCssSelector);

            if(areElementPresents(subcategoriesCssSelector)){
                for (int j = 0; j < listOfSubCategories.size(); j++){
                    listOfSubCategories.get(j).click();
                    listOfSubCategories = driver.findElements(subcategoriesCssSelector);
                    assertTrue(isElementPresent(By.cssSelector("#content > h1")));
                }
            }

            assertTrue(isElementPresent(By.cssSelector("#content > h1")));
        }
    }
}
