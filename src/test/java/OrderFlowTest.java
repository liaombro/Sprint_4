import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.practicum.page_objects.CookiePopup;
import ru.yandex.practicum.page_objects.LandingPage;
import ru.yandex.practicum.page_objects.OrderFormCustomerInfo;
import ru.yandex.practicum.page_objects.OrderFormOrderInfo;

import static org.hamcrest.core.Is.is;

@RunWith(Parameterized.class)
public class OrderFlowTest extends TestBase {

    private String name;
    private String surname;
    private String address;

    private String metroName;
    private String phone;

    private int day;
    private int month;
    private int year;
    private int lendTerm;
    private boolean[] colors;
    private String comment;

    // Expected result
    private boolean isOrderNumberVisible;

    //Регулирует то, какая кнопка "Заказать" будет нажата -- в хедере или на странице
    private boolean isHeaderButtonUsed;

    public OrderFlowTest(String name, String surname, String address, String metroName, String phone,
                         int year, int month, int day, int lendTerm, boolean[] colors, String comment,
                         boolean isHeaderButtonUsed,
                         boolean isOrderNumberVisible) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metroName = metroName;
        this.phone = phone;
        this.year = year;
        this.month = month;
        this.day = day;
        this.lendTerm = lendTerm;
        this.colors = colors;
        this.comment = comment;
        this.isOrderNumberVisible = isOrderNumberVisible;
        this.isHeaderButtonUsed = isHeaderButtonUsed;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Иван", "Иванов", "Москва, ул. Пушкина, дом Колотушкина", "Комсомольская", "88005553535",
                        2023, 1, 5, 3, new boolean[]{true, false}, "Коммент",
                        true,
                        true},
                {"Петр", "Петрович", "Москва, ул. Пушкина, дом Колотушкина", "Черкизовская", "88005553355",
                        2023, 1, 20, 5, new boolean[]{true, true}, "Нарисуйте ламантину в цилиндре",
                        true,
                        false},


        };


    }


    @Test
    public void checkOrder() {
        LandingPage objLandingPage = new LandingPage(driver);
        objLandingPage.openPage();
        CookiePopup objCookies = new CookiePopup(driver);
        objCookies.confirmCookies();

        if (isHeaderButtonUsed) {
            objLandingPage.clickOrderButtonInHeader();
        } else {
            objLandingPage.clickOrderButtonOnPage();
        }

        OrderFormCustomerInfo objCustomerInfo = new OrderFormCustomerInfo(driver);
        objCustomerInfo.setCustomerInformation(name, surname, address, metroName, phone);
        objCustomerInfo.clickNextButton();

        OrderFormOrderInfo objOrderInfo = new OrderFormOrderInfo(driver);
        objOrderInfo.setOrderInformation(year, month, day, lendTerm, colors, comment);
        objOrderInfo.makeOrder();

        MatcherAssert.assertThat("Order number should be visible after order is made", objOrderInfo.checkIfOrderNumberIsVisible(), is(isOrderNumberVisible));
    }

}
