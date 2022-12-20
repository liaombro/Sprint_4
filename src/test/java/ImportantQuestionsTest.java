import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.page_objects.CookiePopup;
import ru.yandex.practicum.page_objects.LandingPage;

import static org.hamcrest.core.Is.is;


@RunWith(Parameterized.class)
public class ImportantQuestionsTest extends TestBase {

    int index;
    boolean isVisible;


    public ImportantQuestionsTest(int index) {
        this.index = index;
        this.isVisible = true;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionIndex() {
        return new Object[][]{
                {0},
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7}
        };
    }




    @Test
    public void clickImportantQuestionItem() {

        LandingPage objLandingPage = new LandingPage(driver);
        objLandingPage.openPage();

        CookiePopup objCookies = new CookiePopup(driver);
        objCookies.confirmCookies();

        objLandingPage.clickImportantQuestionsItem(index);

        MatcherAssert.assertThat("Answer should be visible after click on question", objLandingPage.isAnswerVisible(index), is(isVisible));
    }




}
