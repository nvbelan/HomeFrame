package ru.appline.framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.framework.utils.ConstConteiner;

import static ru.appline.framework.managers.DriverManager.getDriver;

public class BasketPage extends BasePage {
    @FindBy(xpath = "//span/span[normalize-space()='Вернуть удалённый товар']")
    WebElement returnDet;
    @FindBy(xpath = "//a[contains(text(),'Detroit')]/../../..//i[contains(@class,'minus')]")
    WebElement dellGame;
    @FindBy(xpath = "//a[contains(text(),'Detroit')]/../../..//span[@class='price__current']")
    WebElement costGame;
    @FindBy(xpath = "//a[contains(text(),'PlayStation 4 S')]/../../..//i[contains(@class,'plus')]")
    WebElement addSony;
    @FindBy(xpath = "//span[normalize-space() = '+ 24 мес.']")
    WebElement checkedTickGuarantee;
    @FindBy(xpath = "//a[contains(text(),'PlayStation 4 S')]/../../..//span[@class='price__current']")
    WebElement costSony;
    @FindBy(xpath = "//div[contains(text(),'Итого')]/../../..//span[@class='price__current']")
    WebElement costItog;

    public BasketPage checkTick() {
        if (!(checkedTickGuarantee.getAttribute("class")
                .equals("base-ui-radio-button__icon base-ui-radio-button__icon_checked"))) {
            throw new AssertionError("Не установлена гарантия");
        }
        return this;
    }

    public BasketPage checkCostPS() {
        costEquals(costSony, ConstConteiner.psCost);
        return this;
    }

    public BasketPage checkCostGame() {
        costEquals(costGame, ConstConteiner.detCost);
        return this;
    }

    public BasketPage checkItog() {
        costEquals(costItog, (ConstConteiner.detCost + ConstConteiner.psCostWithGuarantee));
        return this;
    }

    public BasketPage dellDetroitWithCheck() {
        Integer temp = strToInt(costItog);
        clicker(dellGame);
        wait.until(BasePage.textNotToBePresentInElement(costItog,temp));
        if (temp - ConstConteiner.detCost != strToInt(costItog)) {
            throw new AssertionError("Игра не удалилась");
        }
        oNNeTyt(costGame,"Игра не удалилась" );

        return this;
    }

    public BasketPage addSonyPlus() {
        Integer temp = strToInt(costItog);
        clicker(addSony);
        wait.until(BasePage.textNotToBePresentInElement(costItog,temp));
        if (temp + ConstConteiner.psCostWithGuarantee != strToInt(costItog)) {
            throw new AssertionError("Игра не добавилась");
        }
        return this;
    }

    public BasketPage checkThreePS() {
        if ((ConstConteiner.psCostWithGuarantee * 3) != strToInt(costItog)) {

        }
        return this;
    }

    public BasketPage clickReturn() {
        Integer temp = strToInt(costItog);
        clicker(returnDet);
        wait.until(BasePage.textNotToBePresentInElement(costItog,temp));
       // sleeper(2000);
        if ((ConstConteiner.psCostWithGuarantee * 3) + ConstConteiner.detCost != strToInt(costItog)) {
            throw new AssertionError("Сумма не соответствует");
        }
        return this;
    }

    public BasketPage checkLastAddDet() {
        oNTyt(costGame, "Игра не добавлена");
        return this;
    }


}
