package com.saucedemo.tests;

import com.saucedemo.pages.AddToCartPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.HomePage;
import com.saucedemo.utils.DataSet;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
public class TestAddToCartPage extends BaseTest {
	 //AddToCartPage atc = new AddToCartPage(driver);
    @BeforeMethod
    public void loginBeforeEachTest() {
        new LoginPage(driver).navigate().login(DataSet.STANDARD_USER, DataSet.PASSWORD);
    }

    @Test
    public void addItemUpdatesCartBadge() {
        AddToCartPage atc = new AddToCartPage(driver);
        atc.addItem();
        HomePage homepage = new HomePage(driver);
        Assert.assertEquals(homepage.getCartBadgeCount(), "1", "Cart badge should be 1 after adding an item.");
    }

    @Test
    public void removeItemUpdatesCartBadgeToZero() {
        AddToCartPage atc = new AddToCartPage(driver);
        atc.addItem();
        atc.removeItem();
        HomePage homepage = new HomePage(driver);
        Assert.assertEquals(homepage.getCartBadgeCount(), "0", "Cart badge should be 0 after removing the item.");
    }

    @Test
    public void cartBadgeAccumulatesItems() {
        AddToCartPage atc = new AddToCartPage(driver);
        atc.addItem(); 
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        HomePage hp = new HomePage(driver);
        Assert.assertEquals(hp.getCartBadgeCount(), "2", "Cart badge should show 2 after adding two items.");
    }

    @Test
    public void cartLinkNavigatesToCartPage() {
        AddToCartPage atc = new AddToCartPage(driver);
        atc.addItem();
        atc.goToCart();
        String expectedUrl = "https://www.saucedemo.com/cart.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "User should be navigated to the cart page.");
    }
    
    @Test
    public void addToCartButtonChangesToRemove() {
        AddToCartPage atc = new AddToCartPage(driver);
        atc.addItem();
        String buttonText = atc.getAddRemoveButtonText();
        Assert.assertEquals(buttonText, "Remove", "Button should show 'Remove' after adding item to cart.");
    }

    @Test
    public void removeFromCartButtonChangesToAddToCart() {
        AddToCartPage atc = new AddToCartPage(driver);
        atc.addItem();
        atc.removeItem();
        String buttonText = atc.getAddRemoveButtonText();
        Assert.assertEquals(buttonText, "Add to cart", "Button should show 'Add to cart' after removing item from cart.");
    }

}

