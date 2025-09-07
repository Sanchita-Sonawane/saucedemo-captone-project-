package com.saucedemo.tests;

import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.DataSet;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestHomePage extends BaseTest {
    @Test
    public void headerVisibleAfterLogin() {
        new LoginPage(driver).navigate().login(DataSet.STANDARD_USER, DataSet.PASSWORD);
        HomePage hp = new HomePage(driver);
        Assert.assertEquals(hp.getHeader(),"Products");
    }
}
