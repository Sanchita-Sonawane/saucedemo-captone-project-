package com.saucedemo.tests;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.SortingProductPage;
import com.saucedemo.utils.DataSet;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSortingProductPage extends BaseTest {

    @Test
    public void sortByPriceLowToHigh() {
        new LoginPage(driver).navigate().login(DataSet.STANDARD_USER, DataSet.PASSWORD);
        SortingProductPage sp = new SortingProductPage(driver);
        sp.sortBy("Price (low to high)");
        Assert.assertTrue(sp.isSortedByPriceLowToHigh(), "Products are not sorted by price low to high");
    }
    
    @Test
    public void sortByPriceHighToLow() {
        new LoginPage(driver).navigate().login(DataSet.STANDARD_USER, DataSet.PASSWORD);
        SortingProductPage sp = new SortingProductPage(driver);
        sp.sortBy("Price (high to low)");
        Assert.assertTrue(sp.isSortedByPriceHighToLow(), "Products are not sorted by price high to low");
    }

    @Test
    public void sortByNameAtoZ() {
        new LoginPage(driver).navigate().login(DataSet.STANDARD_USER, DataSet.PASSWORD);
        SortingProductPage sp = new SortingProductPage(driver);
        sp.sortBy("Name (A to Z)");
        Assert.assertTrue(sp.isSortedByNameAtoZ(), "Products are not sorted by name A to Z");
    }

    @Test
    public void sortByNameZtoA() {
        new LoginPage(driver).navigate().login(DataSet.STANDARD_USER, DataSet.PASSWORD);
        SortingProductPage sp = new SortingProductPage(driver);
        sp.sortBy("Name (Z to A)");
        Assert.assertTrue(sp.isSortedByNameZtoA(), "Products are not sorted by name Z to A");
    }
}
