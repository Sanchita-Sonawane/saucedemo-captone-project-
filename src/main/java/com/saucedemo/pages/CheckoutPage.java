package com.saucedemo.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CheckoutPage extends BasePage {
    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
   
    private By checkout = By.id("checkout");
    private By continueBtn = By.id("continue");
    private By finish = By.id("finish");
    private By cancel = By.id("cancel");
    private By cartLink = By.className("shopping_cart_link");
    private By summaryItems = By.cssSelector(".cart_item");
    private By confirmation = By.cssSelector(".complete-header");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }

    public void startCheckout() {
        driver.findElement(checkout).click();
    }

    public void fillDetails(String f, String l, String p) {
        driver.findElement(firstName).sendKeys(f);
        driver.findElement(lastName).sendKeys(l);
        driver.findElement(postalCode).sendKeys(p);
    }

    public void continueCheckout() {
        driver.findElement(continueBtn).click();
    }

    public void finishCheckout() {
        driver.findElement(finish).click();
    }

    public String getConfirmation() {
        return driver.findElement(confirmation).getText();
    }

    public void cancelCheckout() {
        driver.findElement(cancel).click();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isFinishButtonPresent() {
        return driver.findElements(finish).size() > 0;
    }
     public List<WebElement> getSummaryItems() {
        return driver.findElements(summaryItems);
    }
   
   
}