import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;

public class MySecondTest extends TestBase {


    @Test
    public void secondTest() {
        System.out.println();
        System.out.println(((HasCapabilities)  driver).getCapabilities());
        System.out.println();
        driver.get("http://localhost/litecart/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        System.out.println("Тест 2, поток: " + Thread.currentThread().getName());
    }

}
