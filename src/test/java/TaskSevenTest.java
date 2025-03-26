import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskSevenTest extends TestBase {
    @Test
    public void successCheckOnlyOneStickerPerImage() {
        String mostPopularImageQuery = "#box-most-popular";
        String campaignsImageQuery = "#box-campaigns";
        String latestProductsPopularImageQuery = "#box-latest-products";
        String[] imageBlocksQuery = {mostPopularImageQuery, campaignsImageQuery, latestProductsPopularImageQuery};

        driver.get(url);

        for (String query : imageBlocksQuery) {
            int boxImagesSize = driver.findElements(By.cssSelector(query + " a.link")).size();

            for (int i = 1; i < boxImagesSize + 1; i++) {
                assertEquals(1, driver.findElements(By.cssSelector(query + " li:nth-child(" + i + ") div.sticker")).size(), "У картинки не один стикер");
            }

        }
    }
}
