package ru.yandex.practicum.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class OrderFormCustomerInfo {

    private WebDriver driver;

    // Поле "Имя"
    private By nameField = By.xpath(".//input[@placeholder = '* Имя']");
    // Поле "Фамилия"
    private By surnameField = By.xpath(".//input[@placeholder = '* Фамилия']");
    // Адрес
    private By addressField = By.xpath(".//input[contains(@placeholder, 'Адрес')]");
    // Метро
    private By metroField = By.xpath(".//input[@placeholder = '* Станция метро']");

    // Элементы списка станций метро
    private By metroStations = By.xpath(".//li[@class = 'select-search__row']/button");

    private By metroStation;
    // Телефон
    private By phoneField = By.xpath(".//input[contains(@placeholder, 'Телефон')]");
    // Кнопка Далее
    private By nextButton = By.xpath(".//button[text()='Далее']");

    //Error messages for text fields

    private By visibleErrorMessages = By.xpath(
            ".//div[contains(@class, 'Input_ErrorMessage') and contains(@class, 'Input_Visible')]");


    public OrderFormCustomerInfo(WebDriver driver) {
        this.driver = driver;
    }

    public void setCustomerInformation(String name, String surname, String address, String metroName, String phone) {
        setCustomerName(name);
        setCustomerSurname(surname);
        setAddress(address);
        setMetro(metroName);
        setPhone(phone);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }


    public void setCustomerName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setCustomerSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }

    public void setMetro(String metroName) {
        driver.findElement(metroField).click();
        metroStation = getMetroLocatorByName(metroName);
        driver.findElements(metroStation).get(0).click(); // get(0) is for different stations with same name
    }


    public void setPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
    }

    public List<String> getErrorMessagesTexts(){

        List<String> result = new ArrayList<>();
        List<WebElement> elements = driver.findElements(visibleErrorMessages);
        for(WebElement el : elements){
            result.add(el.getText());
        }
        return result;
    }

    private By getMetroLocatorByName (String name) {
        String xpath = String.format(".//button/div[contains(@class,'Order_Text') and text()='%s']", name);
        return By.xpath(xpath);
    }
}
