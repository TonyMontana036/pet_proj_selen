import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ie.InternetExplorerDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTenTest extends TestBase {
    String cSSQueryForFirstCardFromCampaign = "#box-campaigns a.link ";
    String cSSRegularPrice = "s.regular-price";
    String cSSCampaignPrice = "strong.campaign-price";
    String cSSQueryRegularPriceFromProductCard = "#box-product div.price-wrapper " + cSSRegularPrice;
    String cSSQueryCampaignPriceFromProductCard = "#box-product div.price-wrapper " + cSSCampaignPrice;

    @Test
    public void successCheckNameOnFirstCampaignsAndCardPage() {
        String cSSQueryH1Title = "#box-product h1";
        String cSSName = "div.name";

        driver.get(url);

        assertTrue(isElementPresent(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSName)), "Отсутсвтует название товара");

        String titleFromCampaign = driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSName)).getText();
        driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSName)).click();

        assertTrue(isElementPresent(By.cssSelector(cSSQueryH1Title)), "Отсутсвтует название товара");
        driver.findElement(By.cssSelector(cSSQueryH1Title)).getText();

        assertEquals(titleFromCampaign, driver.findElement(By.cssSelector(cSSQueryH1Title)).getText(), "Названия отличаются");
    }

    @Test
    public void successComparePricesOnFirstCampaignsAndCardPage() {
        driver.get(url);

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
        String expectedGreyNormalColorOnCampaign = "119, 119, 119";
        String expectedGreyNormalColorOnCard = "102, 102, 102";
        String expectedDiscountRedColor = "204, 0, 0";
        String typeOfTextDecoration = "text-decoration-line";
        String expectedTextStyle = "line-through";

        if (driver instanceof InternetExplorerDriver) {
            typeOfTextDecoration = "text-decoration";
        }

        driver.get(url);

        assertTrue(isElementPresent(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSRegularPrice)), "Отсутсвтует обычная цена товара товара");
        assertTrue(isElementPresent(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSCampaignPrice)), "Отсутсвтует скидочная цена товара товара");

        String normalColorCampaign = driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSRegularPrice)).getCssValue("color");
        String normalTextStyleCampaign = driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSRegularPrice)).getCssValue(typeOfTextDecoration);
        String discountColorCampaign = driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSCampaignPrice)).getCssValue("color");

        assertTrue(normalColorCampaign.contains(expectedGreyNormalColorOnCampaign), "Цвет обычной цены на главной странице отличается от ожидаемого");
        assertEquals(expectedTextStyle, normalTextStyleCampaign, "Стиль текста отличается на главной странице");
        assertTrue(discountColorCampaign.contains(expectedDiscountRedColor), "Цвет скидки на главной странице отличается от ожидаемого");

        openProductCard();

        assertTrue(isElementPresent(By.cssSelector(cSSQueryRegularPriceFromProductCard)), "Отсутсвтует обычная цена товара");
        assertTrue(isElementPresent(By.cssSelector(cSSQueryCampaignPriceFromProductCard)), "Отсутсвтует скидочная цена товара");

        String discountColorOnCard = driver.findElement(By.cssSelector(cSSQueryCampaignPriceFromProductCard)).getCssValue("color");
        String normalTextStyleOnCard = driver.findElement(By.cssSelector(cSSQueryRegularPriceFromProductCard)).getCssValue(typeOfTextDecoration);
        String normalColorOnCard = driver.findElement(By.cssSelector(cSSQueryRegularPriceFromProductCard)).getCssValue("color");

        assertTrue(normalColorOnCard.contains(expectedGreyNormalColorOnCard), "Цвет обычной цены на странице товара отличается от ожидаемого");
        assertEquals(expectedTextStyle, normalTextStyleOnCard, "Стиль текста отличается на странице товара");
        assertTrue(discountColorOnCard.contains(expectedDiscountRedColor), "Цвет скидки на  странице товара отличается от ожидаемого");
    }

    @Test
    public void successCompareSizeOfPrices() {
        driver.get(url);

        assertTrue(isElementPresent(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSRegularPrice)), "Отсутсвтует обычная цена товара товара");
        assertTrue(isElementPresent(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSCampaignPrice)), "Отсутсвтует скидочная цена товара товара");

        Dimension normalPriceSize = driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSRegularPrice)).getSize();
        Dimension discountPriceSize = driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSCampaignPrice)).getSize();

        assertTrue(getDimensionSquare(normalPriceSize) < getDimensionSquare(discountPriceSize), "Размерная площадь обычной цены не меньше скидочной цены");

        openProductCard();

        assertTrue(isElementPresent(By.cssSelector(cSSQueryRegularPriceFromProductCard)), "Отсутсвтует обычная цена товара");
        assertTrue(isElementPresent(By.cssSelector(cSSQueryCampaignPriceFromProductCard)), "Отсутсвтует скидочная цена товара");

        Dimension normalPriceSizeForProductCard = driver.findElement(By.cssSelector(cSSQueryRegularPriceFromProductCard)).getSize();
        Dimension discountPriceSizeProductCard = driver.findElement(By.cssSelector(cSSQueryCampaignPriceFromProductCard)).getSize();

        assertTrue(getDimensionSquare(normalPriceSizeForProductCard) < getDimensionSquare(discountPriceSizeProductCard), "Размерная площадь обычной цены не меньше скидочной цены");

    }

    private int getDimensionSquare(Dimension dimension) {
        int width = dimension.getWidth();
        int height = dimension.getHeight();

        return width * height;
    }

    private void openProductCard() {
        driver.findElement(By.cssSelector(cSSQueryForFirstCardFromCampaign + cSSRegularPrice)).click();
    }
}
