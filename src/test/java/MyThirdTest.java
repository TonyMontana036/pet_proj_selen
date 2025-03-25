import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;

public class MyThirdTest extends TestBase {

    @Test
    public void thirdTest() {
        System.out.println();
        System.out.println(((HasCapabilities) driver).getCapabilities());
        System.out.println();
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        System.out.println("Тест 3, поток: " + Thread.currentThread().getName());
    }

}
