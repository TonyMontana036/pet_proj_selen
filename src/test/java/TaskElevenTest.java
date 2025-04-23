import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskElevenTest extends TestBaseObsolete {

    @Test
    public void successCreateUserAndLogout() {
        String firstName = "Tony";
        String lastName = "Montana";
        String adress1 = "Mobil";
        String postcode = "55555";
        String country = "united states";
        String phone = "+11111111111";
        String unicMasc = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String email = "testemail" + unicMasc + "@gmail.com";
        String password = "P@ssw0rd";

        driver.get("http://localhost/litecart/en/create_account");

        new Actions(driver)
                .moveToElement(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(1) > input[type=text]")))
                .click()
                .sendKeys(Keys.HOME + firstName)
                .moveToElement(driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(2) > input[type=text]")))
                .click()
                .sendKeys(Keys.HOME + lastName)
                .moveToElement(driver.findElement(By.cssSelector("tr:nth-child(3) > td:nth-child(1) > input[type=text]")))
                .click()
                .sendKeys(Keys.HOME + adress1)
                .moveToElement(driver.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(1) > input[type=text]")))
                .click()
                .sendKeys(Keys.HOME + postcode)
                .moveToElement(driver.findElement(By.cssSelector("tr:nth-child(4) > td:nth-child(2) > input[type=text]")))
                .click()
                .sendKeys(Keys.HOME + adress1)
                .moveToElement(driver.findElement(By.cssSelector("tr:nth-child(6) > td:nth-child(1) > input[type=email]")))
                .click()
                .sendKeys(Keys.HOME + email)
                .moveToElement(driver.findElement(By.cssSelector("tr:nth-child(6) > td:nth-child(2) > input[type=tel]")))
                .click()
                .sendKeys(Keys.HOME + phone)
                .moveToElement(driver.findElement(By.cssSelector("tr:nth-child(8) > td:nth-child(1) > input[type=password]")))
                .click()
                .sendKeys(Keys.HOME + password)
                .moveToElement(driver.findElement(By.cssSelector("tr:nth-child(8) > td:nth-child(2) > input[type=password]")))
                .click()
                .sendKeys(Keys.HOME + password)
                .moveToElement(driver.findElement(By.cssSelector("span[title]")))
                .click()
                .perform();

        new Actions(driver)
                .moveToElement(driver.findElement(By.cssSelector("[role=textbox]"))).click().sendKeys(Keys.HOME + country + Keys.ENTER)
                .perform();

        assertTrue(isElementPresent(By.cssSelector("[name=create_account]")), "Кнопка 'Создать аккаунт' не найдена");
        driver.findElement(By.cssSelector("[name=create_account]")).click();

        assertTrue(isElementPresent(By.cssSelector("#box-account a[href*='logout']")), "Кнопка 'разлогиниться' не найдена");
        driver.findElement(By.cssSelector("#box-account a[href*='logout']")).click();

        new Actions(driver)
                .moveToElement(driver.findElement(By.cssSelector("#box-account-login input[name=email]"))).click().sendKeys(email)
                .moveToElement(driver.findElement(By.cssSelector("#box-account-login input[name=password]"))).click().sendKeys(password)
                .moveToElement(driver.findElement(By.cssSelector("#box-account-login button[name=login]"))).click()
                .perform();

        assertTrue(isElementPresent(By.cssSelector("#box-account a[href*='logout']")), "Кнопка 'разлогиниться' не найдена");
        driver.findElement(By.cssSelector("#box-account a[href*='logout']")).click();
    }
}
