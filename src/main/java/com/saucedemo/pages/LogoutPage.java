package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LogoutPage extends BasePage {
    private By menu = By.id("react-burger-menu-btn");
    private By logout = By.id("logout_sidebar_link");
    private By loginButton = By.id("login-button");

    private WebDriverWait wait;

    public LogoutPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void logout() {
        // Open the menu
        WebElement menuBtn = wait.until(ExpectedConditions.elementToBeClickable(menu));
        menuBtn.click();

        // Ensure the logout button is visible before clicking
        wait.until(ExpectedConditions.visibilityOfElementLocated(logout));
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(logout));
        logoutBtn.click();

        // Wait for the login button to confirm logout
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }
}
