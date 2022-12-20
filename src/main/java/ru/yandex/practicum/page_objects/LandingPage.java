package ru.yandex.practicum.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.yandex.practicum.constants.PageURL;

import java.util.List;

public class LandingPage {
    private WebDriver driver;

    // список вопросов о важном
    private By ImportantQuestionsList = By.className("accordion");
    // элемент списка вопросов о важном
    private By ImportantQuestionsItems = By.className("accordion__item");

    // Ответ на вопрос
    private By Answer = By.xpath(".//div[@class='accordion__panel']");

    // кнопка "Заказать" в хедере главной страницы
    private By OrderButtonInHeader = By.xpath(".//div[contains(@class, 'Header')]//button[text() = 'Заказать']");

    // кнопка "Заказать" на главной странице
    private By OrderButtonOnPage = By.xpath(".//div[contains(@class, 'Home_ThirdPart')]//button[text() = 'Заказать']");



    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(PageURL.MAIN_PAGE_URL);
    }

    public void clickImportantQuestionsItem(int index) {
        List<WebElement> items = driver.findElements(ImportantQuestionsItems);
        WebElement item = items.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", item);
        item.click();
    }

    public boolean isAnswerVisible(int index) {
        String  display = driver.findElements(Answer).get(index).getCssValue("display");
        return !display.equals("none");
    }

    public void clickOrderButtonInHeader() {
        driver.findElement(OrderButtonInHeader).click();
    }

    public void clickOrderButtonOnPage() {
        driver.findElement(OrderButtonOnPage).click();
    }

}
