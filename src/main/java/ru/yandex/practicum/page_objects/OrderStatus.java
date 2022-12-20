package ru.yandex.practicum.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderStatus {
    private WebDriver driver;

    private By noOrderImage = By.xpath(".//img[@alt='Not found']");
    public OrderStatus(WebDriver driver) {
        this.driver = driver;
    }
    public boolean isNoOrderImagePresentInDOM(){
        return driver.findElements(noOrderImage).size()>0;
    }
}
