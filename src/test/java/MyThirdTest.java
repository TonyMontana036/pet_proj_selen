import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.HasCapabilities;

public class MyThirdTest extends TestBaseObsolete {
    @Disabled
    @Test
    public void thirdTest() {
        System.out.println();
        System.out.println(((HasCapabilities) driver).getCapabilities());
        System.out.println();

        openAndLoginByAdmin();

        System.out.println("Тест 3, поток: " + Thread.currentThread().getName());
    }

}
