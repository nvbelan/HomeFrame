package ru.appline.framework.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage {

    @FindBy(xpath = "//input[@placeholder = 'Поиск по сайту']")
    WebElement searchField;

    public StartPage fillSearchField (String value) {
        fillInputField(searchField, value);
        return this;
    }
    public SearchPage clickSearch(){
        searchField.click();
        searchField.sendKeys(Keys.ENTER);
       return app.getSearchPage();
    }

}