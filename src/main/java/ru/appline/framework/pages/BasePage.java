package ru.appline.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.ManagerPages;

import static ru.appline.framework.managers.DriverManager.getDriver;

public class BasePage {
    protected ManagerPages app = ManagerPages.getManagerPages();

    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 50, 1000);

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    protected void scrollToElementJs(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    protected WebElement elementToBeClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    public void fillInputField(WebElement field, String value) {
        scrollToElementJs(field);
        elementToBeClickable(field).click();
        field.sendKeys(value);
    }

    public void fillDateField(WebElement field, String value) {
        scrollToElementJs(field);
        field.sendKeys(value);
    }

    private void elementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickerV(WebElement element) {
        if (!element.isDisplayed()) {
            scrollToElementJs(element);
        }
        elementToBeVisible(element);
        element.click();
    }

    public void clicker(WebElement element) {
        if (!element.isDisplayed()) {
            scrollToElementJs(element);
        }
        elementToBeClickable(element);
        element.click();
    }

    public void costEquals(WebElement a, int b) {
        if (Integer.parseInt(a.getText().replaceAll("[^0-9]", "")) != b) {
            throw new AssertionError("Несоответствие цены на ПС");
        }
    }

    public static ExpectedCondition<Boolean> textNotToBePresentInElement(final WebElement element, final Integer text) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    Integer elementText = Integer.parseInt(element.getText().replaceAll("[^0-9]", ""));
                    return !(elementText==text);
                } catch (StaleElementReferenceException var3) {
                    return null;
                }
            }

            public String toString() {
                return String.format("text ('%s') to be present in element %s", text, element);
            }
        };
    }

    public Integer strToInt(String a) {
        return Integer.parseInt(a.replaceAll("[^0-9]", ""));
    }

    public Integer strToInt(WebElement a) {
        return Integer.parseInt(a.getText().replaceAll("[^0-9]", ""));
    }
    public void oNNeTyt(WebElement a,String mess){
        boolean marker = true;
        try {
            a.getText();
        } catch (Exception ex) {
            marker = false;
        }
        if (marker) {
            throw new AssertionError( mess);
        }
    }
    public void oNTyt(WebElement a, String mess){
        boolean marker = false;
        try {
            a.getText();
        } catch (Exception ex) {
            marker = true;
        }
        if (marker) {
            throw new AssertionError(mess);
        }
    }
    public void sleeper(long a){
        try {
            Thread.sleep(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


