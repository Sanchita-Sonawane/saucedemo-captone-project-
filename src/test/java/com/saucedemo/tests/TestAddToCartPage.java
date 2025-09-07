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
   
    @BeforeMethod
    public void loginBeforeEachTest() {
        new LoginPage(driver).navigate().login(DataSet.STANDARD_USER, DataSet.PASSWORD);
    }

    @Test
    public void addItemUpdatesCartBadge() {
        AddToCartPage atc = new AddToCartPage(driver);
        atc.addItem();
        HomePage hp = new HomePage(driver);
        Assert.assertEquals(hp.getCartBadgeCount(), "1", "Cart badge should be 1 after adding an item.");
    }

    @Test
    public void removeItemUpdatesCartBadgeToZero() {
        AddToCartPage atc = new AddToCartPage(driver);
        atc.addItem();
        atc.removeItem();
        HomePage hp = new HomePage(driver);
        Assert.assertEquals(hp.getCartBadgeCount(), "0", "Cart badge should be 0 after removing the item.");
    }

    @Test
    public void cartBadgeAccumulatesItems() {
        AddToCartPage atc = new AddToCartPage(driver);
        atc.addItem(); // 1st item
        // Simulate adding another item (if more items are available, add them)
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click(); // Example second item
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

    /*@Test
    public void addingSameItemDoesNotDuplicateInBadge() {
        AddToCartPage atc = new AddToCartPage(driver);
        atc.addItem();
        // Try to add same item again (shouldn't increment cart badge further)
        // You may skip this if the site disables the button after one click.
        atc.addItem(); // no effect expected
        HomePage hp = new HomePage(driver);
        Assert.assertEquals(hp.getCartBadgeCount(), "1", "Cart badge should not increment when same item is added again.");
    }*/

    @Test
    public void cartBadgeNotVisibleWhenEmpty() {
        HomePage hp = new HomePage(driver);
        Assert.assertEquals(hp.getCartBadgeCount(), "0", "Cart badge should be 0 or not visible when no items added.");
    }
    
    /*@Test
    public void addToCartButtonChangesToRemove() {
        AddToCartPage atc = new AddToCartPage(driver);
        atc.addItem();
        String buttonText = atc.getAddRemoveButtonText();
        Assert.assertEquals(buttonText, "Remove", "Button should show 'Remove' after adding item to cart.");
    }*/

    /*@Test
    public void removeFromCartButtonChangesToAddToCart() {
        AddToCartPage atc = new AddToCartPage(driver);
        atc.addItem();
        atc.removeItem();
        String buttonText = atc.getAddRemoveButtonText();
        Assert.assertEquals(buttonText, "Add to cart", "Button should show 'Add to cart' after removing item from cart.");
    }*/

}
