package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.LogoutPage;
import com.saucedemo.utils.DataSet;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestLogoutPage extends BaseTest {

	@Test
	public void logoutWorks() throws InterruptedException {
	    new LoginPage(driver).navigate().login(DataSet.STANDARD_USER, DataSet.PASSWORD);
	    new LogoutPage(driver).logout();

	    // Wait up to 5 seconds for redirect to homepage
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));

	    System.out.println("Current URL after logout: " + driver.getCurrentUrl());
	    Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
	    Assert.assertTrue(new LoginPage(driver).isLoginFormDisplayed(), "Login form should be visible after logout");
	}

}
