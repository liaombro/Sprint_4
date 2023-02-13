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
    private By importantQuestionsList = By.className("accordion");
    // элемент списка вопросов о важном
    private By importantQuestionsItems = By.className("accordion__item");

    // Ответ на вопрос
    private By answer = By.xpath(".//div[@class='accordion__panel']");

    // Текст ответа на вопрос
    private By answerTextRelativeLocator = By.xpath(".//p");

    // кнопка "Заказать" в хедере главной страницы
    private By orderButtonInHeader = By.xpath(".//div[contains(@class, 'Header')]//button[text() = 'Заказать']");

    // кнопка "Заказать" на главной странице
    private By orderButtonOnPage = By.xpath(".//div[contains(@class, 'Home_ThirdPart')]//button[text() = 'Заказать']");



    public LandingPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(PageURL.MAIN_PAGE_URL);
    }

    public void clickImportantQuestionsItem(int index) {
        List<WebElement> items = driver.findElements(importantQuestionsItems);
        WebElement item = items.get(index);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView()", item);
        item.click();
    }

    public boolean isAnswerVisible(int index) {
        String  display = driver.findElements(answer).get(index).getCssValue("display");
        return !display.equals("none");
    }

    public String getAnswerText(int index) {
       WebElement element = driver.findElements(answer).get(index);
       return element.findElement(answerTextRelativeLocator).getText();
    }

    public void clickOrderButtonInHeader() {
        driver.findElement(orderButtonInHeader).click();
    }

    public void clickOrderButtonOnPage() {
        driver.findElement(orderButtonOnPage).click();
    }

}
