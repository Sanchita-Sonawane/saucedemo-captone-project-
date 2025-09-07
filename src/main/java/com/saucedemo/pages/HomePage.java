package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private By title = By.cssSelector(".title");
    private By menu = By.id("react-burger-menu-btn");
    private By logout = By.id("logout_sidebar_link");
    private By cartBadge = By.className("shopping_cart_badge");

    public HomePage(WebDriver driver) { 
    	super(driver); 
    	}

    public String getHeader() { 
    	return driver.findElement(title).getText(); 
    	}

    public void openMenu() { 
    	driver.findElement(menu).click(); 
    	}

    public void logout() { 
    	openMenu(); 
    	driver.findElement(logout).click(); 
    	}

    public String getCartBadgeCount() {
        try { return driver.findElement(cartBadge).getText(); } catch (Exception e) { return "0"; }
    }
}
