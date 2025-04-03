import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTwelveTest extends TestBase {

    @Test
    public void successCreateProductCard() throws InterruptedException {
        String unicName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String filePath = new File("src/test/resources/test-files/test_photo.jpg").getAbsolutePath();
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin ante massa, eget ornare libero porta congue. Cras scelerisque dui non consequat sollicitudin. Sed pretium tortor ac auctor molestie. Nulla facilisi. Maecenas pulvinar nibh vitae lectus vehicula semper. Donec et aliquet velit. Curabitur non ullamcorper mauris. In hac habitasse platea dictumst. Phasellus ut pretium justo, sit amet bibendum urna. Maecenas sit amet arcu pulvinar, facilisis quam at, viverra nisi. Morbi sit amet adipiscing ante. Integer imperdiet volutpat ante, sed venenatis urna volutpat a. Proin justo massa, convallis vitae consectetur sit amet, facilisis id libero.";
        String shortDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse sollicitudin ante massa, eget ornare libero porta congue.";

        openAndLoginByAdmin("http://localhost/litecart/admin/?app=catalog&doc=catalog");

        assertTrue(isElementPresent(By.cssSelector("#content [href*='?category_id=0&app=catalog&doc=edit_product']")), "Отсутствует кноплка Добавить продукт");
        driver.findElement(By.cssSelector("#content [href*='?category_id=0&app=catalog&doc=edit_product']")).click();

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(filePath);

        driver.findElement(By.cssSelector("#tab-general input[type=number]")).clear();

        new Actions(driver)
                .moveToElement(driver.findElement(By.cssSelector("#tab-general input[type=radio][value='1']"))).click()
                .moveToElement(driver.findElement(By.cssSelector("#tab-general input[type=text][name='name[en]']"))).click().sendKeys(Keys.HOME + unicName)
                .moveToElement(driver.findElement(By.cssSelector("#tab-general input[type=text][name='code']"))).click().sendKeys(Keys.HOME + "55555")
                .moveToElement(driver.findElement(By.cssSelector("#tab-general input[type=number]"))).click().sendKeys(Keys.HOME + "30")
                .moveToElement(driver.findElement(By.cssSelector("#content a[href='#tab-information']"))).click()
                .perform();

        new Actions(driver)
                .moveToElement(driver.findElement(By.cssSelector("#tab-information div.trumbowyg-editor"))).click().sendKeys(Keys.HOME + description)
                .moveToElement(driver.findElement(By.cssSelector("#tab-information input[name='short_description[en]']"))).click().sendKeys(Keys.HOME + shortDescription)
                .moveToElement(driver.findElement(By.cssSelector("#tab-information select[name='manufacturer_id']"))).click()
                .perform();

        driver.findElement(By.cssSelector("#tab-information select > option:nth-child(2)")).click();

        driver.findElement(By.cssSelector("#content a[href='#tab-prices']")).click();

        new Actions(driver)
                .moveToElement(driver.findElement(By.cssSelector("#tab-prices input[type=number][name=purchase_price]"))).click().sendKeys(Keys.HOME + "10,00")
                .moveToElement(driver.findElement(By.cssSelector("#tab-prices input[type=text][name='prices[USD]']"))).click().sendKeys(Keys.HOME + "20.0000")
                .moveToElement(driver.findElement(By.cssSelector("#content button[name=save]"))).click()
                .perform();

        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#app- a[href*='?app=catalog&doc=catalog']")).click();

        isElementPresent(By.xpath("//a[text()='" + unicName + "']"));
    }
}
