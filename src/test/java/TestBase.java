import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class TestBase {
    protected WebDriver driver;

    @Before
    public void setUp(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.of(3, ChronoUnit.SECONDS));

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
