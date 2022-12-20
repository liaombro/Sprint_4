import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.constants.ErrorText;
import ru.yandex.practicum.constants.PageURL;
import ru.yandex.practicum.page_objects.OrderFormCustomerInfo;

import static org.hamcrest.CoreMatchers.is;

@RunWith(Parameterized.class)
public class ErrorTextsTest extends TestBase {

    private String fieldForTest;
    private String inputData;
    private String expectedErrorMessage;

    public ErrorTextsTest(String fieldForTest, String inputData, String expectedErrorMessage) {
        this.fieldForTest = fieldForTest;
        this.inputData = inputData;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"name", "Ы" + Keys.TAB, ErrorText.ERROR_INCORRECT_NAME},
                {"surname", "Ы" + Keys.TAB, ErrorText.ERROR_INCORRECT_SURNAME},
                {"address", "Ы" + Keys.TAB, ErrorText.ERROR_INCORRECT_ADDRESS},
                {"phone", "Ы" + Keys.TAB, ErrorText.ERROR_INCORRECT_PHONE}
        };
    }



    @Test
    public void checkErrorMessagesOnFirstScreen() {
        driver.get(PageURL.ORDER_PAGE_URL);
        OrderFormCustomerInfo objCustomerInfo = new OrderFormCustomerInfo(driver);

        switch (fieldForTest) {
            case "name": {
                objCustomerInfo.setCustomerName(inputData);
                break;
            }
            case "surname": {
                objCustomerInfo.setCustomerSurname(inputData);
                break;
            }
            case "address": {
                objCustomerInfo.setAddress(inputData);
                break;
            }
            case "phone": {
                objCustomerInfo.setPhone(inputData);
                break;
            }
        }


        MatcherAssert.assertThat("Error message for field " + fieldForTest + " should be shown",
                objCustomerInfo.getErrorMessagesTexts().size() > 0);
        MatcherAssert.assertThat("Error message for field " + fieldForTest + " should be correct",
                objCustomerInfo.getErrorMessagesTexts().get(0), is(expectedErrorMessage));
    }

}
