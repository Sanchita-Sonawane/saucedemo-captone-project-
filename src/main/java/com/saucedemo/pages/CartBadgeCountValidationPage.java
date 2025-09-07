package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartBadgeCountValidationPage extends BasePage {

    private final By addBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private final By removeBackpack = By.id("remove-sauce-labs-backpack");

    private final By addBolt = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    
    private final By removeBolt = By.id("remove-sauce-labs-bolt-t-shirt");

    private final By cartBadge = By.className("shopping_cart_badge");

    public CartBadgeCountValidationPage(WebDriver driver) {
        super(driver);
    }

    public void addTwoItems() {
        addBackpackOnly();
        addBoltOnly();
    }

    public void addBackpackOnly() {
        if (driver.findElements(addBackpack).size() > 0) {
            driver.findElement(addBackpack).click();
        }
    }

    public void addBoltOnly() {
        if (driver.findElements(addBolt).size() > 0) {
            driver.findElement(addBolt).click();
        }
    }

    public void removeBackpack() {
        if (driver.findElements(removeBackpack).size() > 0) {
            driver.findElement(removeBackpack).click();
        }
    }

    public void removeBolt() {
        if (driver.findElements(removeBolt).size() > 0) {
            driver.findElement(removeBolt).click();
        }
    }

    public String getBadgeCount() {
        try {
            return driver.findElement(cartBadge).getText();
        } catch (Exception e) {
            return "0";
        }
    }
    
}
