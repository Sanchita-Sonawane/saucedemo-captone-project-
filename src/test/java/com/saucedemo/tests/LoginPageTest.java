package com.saucedemo.tests;

import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.LogoutPage;
import com.saucedemo.utils.DataSet;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
	
	
    @Test
    public void validLogin() {
    	LoginPage loginpage = new LoginPage(driver).navigate();
        loginpage.login(DataSet.STANDARD_USER, DataSet.PASSWORD);
        HomePage homepage = new HomePage(driver);
        Assert.assertEquals(homepage.getHeader(), "Products");
    }

    @Test
    public void lockedOutUserShowsError() {
    	LoginPage loginpage = new LoginPage(driver).navigate();
        loginpage.login(DataSet.LOCKED_OUT_USER, DataSet.PASSWORD);
        Assert.assertTrue(loginpage.getError().toLowerCase().contains("locked"));
    }

    @Test
    public void testPasswordRequiredError() {
    	LoginPage loginpage = new LoginPage(driver).navigate();
        loginpage.login(DataSet.STANDARD_USER, "");
        String error = loginpage.getError();

        Assert.assertTrue(error.contains("Password is required"),"Expected 'Password is required' error message" );
    }

    @Test
    public void testUsernameRequiredError() {
    	LoginPage loginpage = new LoginPage(driver).navigate();
        loginpage.login("", DataSet.PASSWORD);
        String error = loginpage.getError();

        Assert.assertTrue(error.contains("Username is required"),"Expected 'Username is required' error message");
    }


    @Test
    public void invalidUserShowsError() {
    	LoginPage loginpage = new LoginPage(driver).navigate();
        loginpage.login("invalid_user", "invalid_pass");
        String error = loginpage.getError();
        Assert.assertTrue(error.contains("do not match"), "Expected invalid credentials error");
    }

    @Test
    public void allValidUsersShouldLoginSuccessfully() {
        String[][] users = {
            {"Problem User", DataSet.PROBLEM_USER},
            {"Performance Glitch User", DataSet.PERFORMANCE_GLITCH_USER},
            {"Error User", DataSet.ERROR_USER},
            {"Visual User", DataSet.VISUAL_USER}
        };

        for (String[] user : users) {
            String userType = user[0];
            String username = user[1];

            System.out.println("Testing login for: " + userType);

            LoginPage loginPage = new LoginPage(driver).navigate();
            loginPage.login(username, DataSet.PASSWORD);

            HomePage homePage = new HomePage(driver);
            Assert.assertEquals(homePage.getHeader(), "Products", userType + " login failed");

            new LogoutPage(driver).logout();
        }
    }
}

