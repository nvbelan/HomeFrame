package ru.appline.framework.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.appline.framework.managers.ManagerPages;

import static ru.appline.framework.managers.DriverManager.getDriver;

public class BasePage {
    protected ManagerPages app = ManagerPages.getManagerPages();

    protected JavascriptExecutor js = (JavascriptExecutor) getDriver();

    protected WebDriverWait wait = new WebDriverWait(getDriver(), 16, 200);

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

    public Boolean textNotToBePresentInElement(final WebElement element, final Integer text) {
        Integer elementText;
        int i = 0;
        do {
            elementText = Integer.parseInt(element.getText().replaceAll("[^0-9]", ""));
            sleeper(200);
            i++;
        } while (elementText == text || i < 10);
        return true;

    }

    public Integer strToInt(WebElement a) {
        return Integer.parseInt(a.getText().replaceAll("[^0-9]", ""));
    }

    public void oNNeTyt(WebElement a, String mess) {
        boolean marker = true;
        try {
            a.getText();
        } catch (NoSuchElementException ex) {
            marker = false;
        }
        if (marker) {
            throw new AssertionError(mess);
        }
    }

    public void oNTyt(WebElement a, String mess) {
        boolean marker = false;
        try {
            a.getText();
        } catch (NoSuchElementException ex) {
            marker = true;
        }
        if (marker) {
            throw new AssertionError(mess);
        }
    }

    public void sleeper(long a) {
        try {
            Thread.sleep(a);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


