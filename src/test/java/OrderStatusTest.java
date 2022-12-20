import org.junit.Test;
import ru.yandex.practicum.constants.PageURL;
import ru.yandex.practicum.page_objects.Header;
import ru.yandex.practicum.page_objects.OrderStatus;

import static org.junit.Assert.assertTrue;

public class OrderStatusTest extends TestBase {


    @Test
    public void nonExistingOrder() {
        int orderNumber = 123456789;

        driver.get(PageURL.MAIN_PAGE_URL);
        Header header = new Header(driver);
        header.getOrderStatus(orderNumber);
        OrderStatus objOrderStatus = new OrderStatus(driver);

        assertTrue("'Order does not exist' image should be shown",
                objOrderStatus.isNoOrderImagePresentInDOM());
    }


}
