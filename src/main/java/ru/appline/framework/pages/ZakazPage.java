package ru.appline.framework.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.appline.framework.utils.ConstConteiner;

import java.util.List;

public class ZakazPage extends BasePage {
    @FindBy(xpath = "//span[@class = 'cart-link__price']")
    WebElement basket;
    @FindBy(xpath = "//span[@class='ui-input-search__icon ui-input-search__icon_search ui-input-search__icon_presearch']")
    WebElement search;
    @FindBy(xpath = "//input[@placeholder = 'Поиск по сайту']")
    WebElement searchField;

    @FindBy(xpath = "//button[text()='Купить']")
    WebElement buyButton;

    @FindBy(xpath = "//select[@class='form-control select']")
    WebElement guarantee;

    @FindBy(xpath = "//select[@class='form-control select']/option")
    List<WebElement> variableGuarantee;

    @FindBy(xpath = "//span[@class = 'product-card-price__current']")
    WebElement productCost;
    @FindBy(xpath = "//span[@class ='product-card-price__current product-card-price__current_active']")
    WebElement productCostWithG;

    public ZakazPage setGuaranteeValue(String value) {
        clicker(guarantee);
        boolean status = false;
        for (WebElement menuItem : variableGuarantee) {
            if (menuItem.getText().contains(value)) {
                clicker(menuItem);
                status = true;
            }
        }
        if (status = false) {
            throw new AssertionError("Данные доп гарантии заполнены неправильно");
        }
        return this;
    }

    public ZakazPage writeCostPS() {
        wait.until(ExpectedConditions.visibilityOf(productCost));
        ConstConteiner.psCost = strToInt(productCost);
        return this;
    }

    public ZakazPage writeCostPSWithGuarantee() {
        wait.until(ExpectedConditions.visibilityOf(productCostWithG));
        ConstConteiner.psCostWithGuarantee = strToInt(productCostWithG);
        return this;
    }

    public ZakazPage clickBuyButton() {
        clicker(buyButton);
        return this;
    }

    public ZakazPage fillSearchField(String value) {
        fillInputField(searchField, value);
        return this;
    }

    public ZakazPage clickSearch() {
        searchField.sendKeys(Keys.ENTER);
        return this;
    }

    public ZakazPage writeCostDetroit() {
        wait.until(ExpectedConditions.visibilityOf(productCost));
        ConstConteiner.detCost = strToInt(productCost);
        return this;
    }

    public ZakazPage checkCost() {
        Integer temp = strToInt(basket);
        if (textNotToBePresentInElement(basket, temp)
                && (ConstConteiner.detCost + ConstConteiner.psCostWithGuarantee != strToInt(basket))) {
            throw new AssertionError("Цена не совпадает");
        }
        return this;
    }

    public BasketPage clickBasket() {
        clicker(basket);
        return app.getBasketPage();
    }


}
