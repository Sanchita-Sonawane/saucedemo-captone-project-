package com.saucedemo.tests;

import com.saucedemo.pages.CartBadgeCountValidationPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.DataSet;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestCartBadgeCountValidationPage extends BaseTest {

    private CartBadgeCountValidationPage page;

    @BeforeMethod
    public void loginAndSetup() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login(DataSet.STANDARD_USER, DataSet.PASSWORD);

        page = new CartBadgeCountValidationPage(driver);
    }

    @Test
    public void badgeShowsTwoWhenAddingTwoItems() {
        page.addTwoItems();
        Assert.assertEquals(page.getBadgeCount(), "2");
    }

    /*@Test
    public void badgeShowsZeroWhenNoItemsAdded() {
        Assert.assertEquals(page.getBadgeCount(), "0");
    }*/

    @Test
    public void badgeIncrementsWhenAddingItems() {
        page.addBackpackOnly();
        //Assert.assertEquals(page.getBadgeCount(), "1");

        page.addBoltOnly();
        Assert.assertEquals(page.getBadgeCount(), "2");
    }

    @Test
    public void badgeUpdatesAfterRemovingItem() {
        page.addTwoItems();
        page.removeBackpack();
        Assert.assertEquals(page.getBadgeCount(), "1");
    }

    /*@Test
    public void badgeDoesNotIncrementForSameItem() {
        page.addBackpackOnly();
        page.addBackpackOnly(); // Shouldn't increase badge count
        Assert.assertEquals(page.getBadgeCount(), "1");
    }*/
}
