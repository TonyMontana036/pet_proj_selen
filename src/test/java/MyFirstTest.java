import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;

public class MyFirstTest extends TestBase{
    @Disabled
    @Test
    public void firstTest() {
        System.out.println();
        System.out.println(((HasCapabilities)  driver).getCapabilities());
        System.out.println();

        openAndLoginByAdmin();

        System.out.println("Тест 1, поток: " + Thread.currentThread().getName());
    }

}
