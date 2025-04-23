import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.HasCapabilities;

public class MySecondTest extends TestBaseObsolete {

    @Disabled
    @Test
    public void secondTest() {
        System.out.println();
        System.out.println(((HasCapabilities)  driver).getCapabilities());
        System.out.println();

        openAndLoginByAdmin();

        System.out.println("Тест 2, поток: " + Thread.currentThread().getName());
    }
}
