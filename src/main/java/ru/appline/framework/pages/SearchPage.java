package ru.appline.framework.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {
    @FindBy (xpath = "//a[contains(text(),'Игровая консоль PlayStation 4 Slim Black 1 TB + 3 игры')]")
    WebElement PsSlimB;

    public ZakazPage clickPsSlimB(){
        clicker(PsSlimB);
        return app.getZakazPage();
    }
}
