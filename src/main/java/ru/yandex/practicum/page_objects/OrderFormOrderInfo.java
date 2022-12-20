package ru.yandex.practicum.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Calendar;

public class OrderFormOrderInfo {

    private WebDriver driver;

    // Дата
    private By dateField = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    // Дата -- кнопка "Вперед" на календаре
    private By calendarForwardButton = By.xpath(".//button[@aria-label='Next Month']");
    // Дата -- кнопка "Назад" на календаре
    private By calendarBackButton = By.xpath(".//button[@aria-label='Previous Month']");
    // Дата -- ячейки календаря
    private By calendarCells = By.xpath(".//div[contains(@class,'react-datepicker__day')]");

    // Поле "срок аренды"
    private By lendTermField = By.xpath(".//div[@class='Dropdown-placeholder']");

    // Элементы выпадающего списка "Срок аренды"
    private By lendTermOptions = By.className("Dropdown-option");

    // Чекбокс "черный жемчуг"
    private By blackColorCheckbox = By.id("black");

    // Чекбокс "серая безысходность"
    private By greyColorCheckbox = By.id("grey");

    // Комментарий
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    // Кнопка "Заказать"
    private By makeOrderButton = By.xpath(".//div[contains(@class, 'Order_Content')]//button[text() = 'Заказать']");

    // Кнопка "Да" в модалке подтверждения заказа
    private By yesButton = By.xpath(".//button[text()='Да']");

    // Элемент с номером созданного заказа
    private By orderNumberModal = By.xpath(".//div[contains(@class, 'Order_Text')]");

    public OrderFormOrderInfo(WebDriver driver) {
        this.driver = driver;
    }


    public void setOrderInformation(int year, int month, int day, int lendTerm, boolean[] colors, String comment) {
        setOrderDate(year, month, day);
        setLendTerm(lendTerm);
        setColors(colors);
        setComment(comment);
    }

    public void makeOrder() {
        driver.findElement(makeOrderButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOfElementLocated(yesButton));
        driver.findElement(yesButton).click();
    }

    private void setOrderDate(int year, int month, int day) {
        month = month - 1; // months start from 0 in Calendar object
        Calendar calendar = new Calendar.Builder().setDate(year, month, day).build();
        Calendar today = Calendar.getInstance();

        int targetMonth = calendar.get(Calendar.MONTH) + 12 * year;
        int currentMonth = today.get(Calendar.MONTH) + 12 * today.get(Calendar.YEAR);

        int numberOfForwardClicks = targetMonth > currentMonth ? targetMonth - currentMonth : 0;
        int numberOfBackClicks = currentMonth > targetMonth ? currentMonth - targetMonth : 0;

        Calendar firstDayOfTargetMonth = new Calendar.Builder().setDate(year, month, 1).build();
        int addendum = (firstDayOfTargetMonth.get(Calendar.DAY_OF_WEEK) + 5) % 7;
        int cellIndex = day - 1 + addendum;

        driver.findElement(dateField).click();
        WebElement backButton = driver.findElement(calendarBackButton);
        for (int i = 0; i < numberOfBackClicks; i++) {
            backButton.click();
        }
        WebElement forwardButton = driver.findElement(calendarForwardButton);
        for (int i = 0; i < numberOfForwardClicks; i++) {
            forwardButton.click();
        }
        driver.findElements(calendarCells).get(cellIndex).click();
    }

    private void setLendTerm(int lendTermIndex) {
        driver.findElement(lendTermField).click();
        driver.findElements(lendTermOptions).get(lendTermIndex).click();
    }

    private void setColors(boolean[] colors) {

        WebElement blackCheckbox = driver.findElement(blackColorCheckbox);
        WebElement greyCheckbox = driver.findElement(greyColorCheckbox);
        if (colors[0] != blackCheckbox.isSelected()) {
            blackCheckbox.click();
        }
        if (colors[1] != greyCheckbox.isSelected()) {
            greyCheckbox.click();
        }
    }

    private void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }

    public boolean checkIfOrderNumberIsVisible() {
        return driver.findElement(orderNumberModal).isDisplayed();
    }
}
