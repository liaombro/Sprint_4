import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.constants.AnswerText;
import ru.yandex.practicum.page_objects.CookiePopup;
import ru.yandex.practicum.page_objects.LandingPage;

import static org.hamcrest.core.Is.is;
import static ru.yandex.practicum.constants.AnswerText.*;


@RunWith(Parameterized.class)
public class ImportantQuestionsTest extends TestBase {

    int index;

    String answer;
    boolean isVisible;


    public ImportantQuestionsTest(int index, String answer) {
        this.index = index;
        this.answer = answer;
        this.isVisible = true;
    }

    @Parameterized.Parameters
    public static Object[][] getQuestionIndex() {
        return new Object[][]{
                {0, HOW_MUCH_ANSWER},
                {1, SEVERAL_SCOOTERS_ANSWER},
                {2, LEND_TIME_ANSWER},
                {3, TODAY_ANSWER},
                {4, PROLONG_ORDER_ANSWER},
                {5, CHARGER_ANSWER},
                {6, CANCEL_ORDER_ANSWER},
                {7, MKAD_ANSWER}
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
        MatcherAssert.assertThat("Answer text should be correct", objLandingPage.getAnswerText(index), is(answer));
    }




}
