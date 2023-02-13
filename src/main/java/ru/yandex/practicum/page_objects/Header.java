package ru.yandex.practicum.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Header {
    private By yandexLogo = By.xpath(".//img[@alt='Yandex']");
    private By scooterLogo = By.xpath(".//img[@alt='Scooter']");

    private By orderStatusButton = By.xpath(
            ".//button[contains(@class, 'Header_Link') and text()='Статус заказа']");
    private By orderNumberInput = By.xpath(".//input[contains(@class,'Header_Input')]");
    private By goButton = By.xpath(".//button[contains(@class, 'Header_Button') and text()='Go!']");

    private WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    public void clickYandexLogo() {
        driver.findElement(yandexLogo).click();
    }

    public void clickScooterLogo() {
        driver.findElement(scooterLogo).click();
    }

    public void getOrderStatus(int orderNumber) {
        driver.findElement(orderStatusButton).click();
        new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.visibilityOfElementLocated(orderNumberInput));
        driver.findElement(orderNumberInput).sendKeys(String.valueOf(orderNumber));
        driver.findElement(goButton).click();
    }
}
