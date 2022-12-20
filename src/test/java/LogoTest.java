import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.constants.PageURL;
import ru.yandex.practicum.page_objects.Header;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class LogoTest extends TestBase {

    private String URL;
    private String targetURL = PageURL.MAIN_PAGE_URL;

    private String yandexURL = "https://dzen.ru/";

    public LogoTest(String URL) {
        this.URL = URL;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {PageURL.ORDER_PAGE_URL},
                {PageURL.TRACK_PAGE_URL},
                {PageURL.MAIN_PAGE_URL}
        };
    }


    @Test
    public void ScooterLogo() {

        Header header = new Header(driver);
        driver.get(URL);
        header.clickScooterLogo();
        MatcherAssert.assertThat("Click on scooter logo opens main page", driver.getCurrentUrl(), is(targetURL));
    }

    @Test
    public void YandexLogo() {
        Header header = new Header(driver);
        driver.get(URL);
        header.clickYandexLogo();

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        MatcherAssert.assertThat("Click on Yandex logo opens Dzen main page in new tab", driver.getCurrentUrl(), containsString(yandexURL));
    }

}
