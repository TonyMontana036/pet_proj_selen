import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTenTest extends TestBaseObsolete {
    String cSSQueryForFirstCardFromCampaign = "#box-campaigns a.link ";
    String cSSRegularPrice = "s.regular-price";
    String cSSCampaignPrice = "strong.campaign-price";
    String cSSQueryRegularPriceFromProductCard = "#box-product div.price-wrapper " + cSSRegularPrice;
    String cSSQueryCampaignPriceFromProductCard = "#box-product div.price-wrapper " + cSSCampaignPrice;


    @BeforeEach
    public void startWithUrl() {
        driver.get(url);
    }

    @Test
    public void successCheckNameOnFirstCampaignsAndCardPage() {
        String cSSQueryH1Title = "#box-product h1";
        String cSSName = "div.name";

        assertTrue(isElementPresent(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSName)), "Отсутсвтует название товара");

        String titleFromCampaign = driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSName)).getText();
        driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSName)).click();

        assertTrue(isElementPresent(By.cssSelector(cSSQueryH1Title)), "Отсутсвтует название товара");
        driver.findElement(By.cssSelector(cSSQueryH1Title)).getText();

        assertEquals(titleFromCampaign, driver.findElement(By.cssSelector(cSSQueryH1Title)).getText(), "Названия отличаются");
    }

    @Test
    public void successComparePricesOnFirstCampaignsAndCardPage() {

        assertTrue(isElementPresent(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSRegularPrice)), "Отсутсвтует обычная цена товара товара");
        assertTrue(isElementPresent(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSCampaignPrice)), "Отсутсвтует скидочная цена товара товара");

        String normalPrice = driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSRegularPrice)).getText();
        String discountPrice = driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSCampaignPrice)).getText();

        openProductCard();

        assertTrue(isElementPresent(By.cssSelector(cSSQueryRegularPriceFromProductCard)), "Отсутсвтует обычная цена товара товара");
        assertTrue(isElementPresent(By.cssSelector(cSSQueryCampaignPriceFromProductCard)), "Отсутсвтует скидочная цена товара товара");

        assertEquals(normalPrice, driver.findElement(By.cssSelector(cSSQueryRegularPriceFromProductCard)).getText(), "Обычная цена отличается");
        assertEquals(discountPrice, driver.findElement(By.cssSelector(cSSQueryCampaignPriceFromProductCard)).getText(), "Скидочная цена отличается");
    }

    @Test
    public void successCompareColorAndStyleOfPrices() {
        String typeOfTextDecoration = "text-decoration-line";
        String expectedTextStyle = "line-through";

        if (driver instanceof InternetExplorerDriver) {
            typeOfTextDecoration = "text-decoration";
        }

        assertTrue(isElementPresent(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSRegularPrice)), "Отсутсвтует обычная цена товара товара");
        assertTrue(isElementPresent(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSCampaignPrice)), "Отсутсвтует скидочная цена товара товара");

        String normalColorCampaign = driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSRegularPrice)).getCssValue("color");
        String normalTextStyleCampaign = driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSRegularPrice)).getCssValue(typeOfTextDecoration);
        String discountColorCampaign = driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSCampaignPrice)).getCssValue("color");

        assertTrue((isGrey(normalColorCampaign)), "Цвет обычной цены на главной странице не серый");
        assertEquals(expectedTextStyle, normalTextStyleCampaign, "Стиль текста отличается на главной странице");
        assertTrue((isRed(discountColorCampaign)), "Цвет скидки на главной странице не красный");

        openProductCard();

        assertTrue(isElementPresent(By.cssSelector(cSSQueryRegularPriceFromProductCard)), "Отсутсвтует обычная цена товара");
        assertTrue(isElementPresent(By.cssSelector(cSSQueryCampaignPriceFromProductCard)), "Отсутсвтует скидочная цена товара");

        String discountColorOnCard = driver.findElement(By.cssSelector(cSSQueryCampaignPriceFromProductCard)).getCssValue("color");
        String normalTextStyleOnCard = driver.findElement(By.cssSelector(cSSQueryRegularPriceFromProductCard)).getCssValue(typeOfTextDecoration);
        String normalColorOnCard = driver.findElement(By.cssSelector(cSSQueryRegularPriceFromProductCard)).getCssValue("color");

        assertTrue((isGrey(normalColorOnCard)), "Цвет обычной цены на странице товара не серый");
        assertEquals(expectedTextStyle, normalTextStyleOnCard, "Стиль текста отличается на странице товара");
        assertTrue((isRed(discountColorOnCard)), "Цвет скидки на  странице товара не красный");
    }

    @Test
    public void successCompareSizeOfPrices() {

        assertTrue(isElementPresent(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSRegularPrice)), "Отсутсвтует обычная цена товара товара");
        assertTrue(isElementPresent(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSCampaignPrice)), "Отсутсвтует скидочная цена товара товара");

        String normalPriceSize = driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSRegularPrice)).getCssValue("font-size");
        String discountPriceSize = driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSCampaignPrice)).getCssValue("font-size");

        assertTrue(isFirstDoubleBigger(discountPriceSize, normalPriceSize), "Размер шрифта скидки не больше шрифта обычной цены");

        openProductCard();

        assertTrue(isElementPresent(By.cssSelector(cSSQueryRegularPriceFromProductCard)), "Отсутсвтует обычная цена товара");
        assertTrue(isElementPresent(By.cssSelector(cSSQueryCampaignPriceFromProductCard)), "Отсутсвтует скидочная цена товара");

        String normalPriceSizeForProductCard = driver.findElement(By.cssSelector(cSSQueryRegularPriceFromProductCard)).getCssValue("font-size");
        String discountPriceSizeProductCard = driver.findElement(By.cssSelector(cSSQueryCampaignPriceFromProductCard)).getCssValue("font-size");

        assertTrue(isFirstDoubleBigger(discountPriceSizeProductCard, normalPriceSizeForProductCard), "Размер шрифта скидки не больше шрифта обычной цены");
    }

    private boolean isFirstDoubleBigger(String discount, String normal) {
        double dis = Double.parseDouble(discount.substring(0, discount.length() - 2));
        double nor = Double.parseDouble(normal.substring(0, normal.length() - 2));

        return dis > nor;
    }

    private void openProductCard() {
        driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSRegularPrice)).click();
    }

    private boolean isGrey(String colorString) {

        int[] rgb = parseColor(colorString);

        return (rgb[0] == rgb[1]) && (rgb[1] == rgb[2]);
    }

    private boolean isRed(String colorString) {

        int[] rgb = parseColor(colorString);

        return rgb[0] != 0 && (rgb[1] == 0 && rgb[2] == 0);
    }

    public static int[] parseColor(String color) {
        String numbers = color.split("\\(")[1].split("\\)")[0];
        String[] parts = numbers.split(",\\s*");

        return new int[]{
                Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2])
        };
    }
}
