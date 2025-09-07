package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddToCartPage extends BasePage {
    private By addBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private By cartLink = By.className("shopping_cart_link");
    private By removeBackpack = By.id("remove-sauce-labs-backpack");

    public AddToCartPage(WebDriver driver) { super(driver); }

    public void addItem() {
        driver.findElement(addBackpack).click();
    }

    public void removeItem() {
        driver.findElement(removeBackpack).click();
    }

    public void goToCart() {
        driver.findElement(cartLink).click();
    }
    
    public String getAddRemoveButtonText() {
        if (driver.findElements(removeBackpack).size() > 0) {
            return driver.findElement(removeBackpack).getText();
        } else {
            return driver.findElement(addBackpack).getText();
        }
    }
    
    public boolean isItemAdded() {
        return driver.findElements(removeBackpack).size() > 0;
    }
}
