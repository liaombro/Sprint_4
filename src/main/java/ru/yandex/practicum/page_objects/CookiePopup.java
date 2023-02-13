package ru.yandex.practicum.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CookiePopup {

    private WebDriver driver;
    private By okButton = By.id("rcc-confirm-button");

    public CookiePopup(WebDriver driver) {
        this.driver = driver;
    }

    public void confirmCookies() {
        driver.findElement(okButton).click();
    }
}
