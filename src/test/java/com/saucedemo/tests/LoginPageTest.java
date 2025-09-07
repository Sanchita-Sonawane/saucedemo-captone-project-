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
        LoginPage lp = new LoginPage(driver).navigate();
        lp.login(DataSet.STANDARD_USER, DataSet.PASSWORD);
        HomePage hp = new HomePage(driver);
        Assert.assertEquals(hp.getHeader(), "Products");
    }

    @Test
    public void lockedOutUserShowsError() {
        LoginPage lp = new LoginPage(driver).navigate();
        lp.login(DataSet.LOCKED_OUT_USER, DataSet.PASSWORD);
        Assert.assertTrue(lp.getError().toLowerCase().contains("locked"));
    }

    @Test
    public void testUsernameOrPasswordRequiredErrors() {
        LoginPage lp = new LoginPage(driver).navigate();

        lp.login(DataSet.STANDARD_USER, "");
        String error1 = lp.getError();
        boolean isPasswordErrorDisplayed = error1.contains("Password is required");

        lp.navigate();
        lp.login("", DataSet.PASSWORD);
        String error2 = lp.getError();
        boolean isUsernameErrorDisplayed = error2.contains("Username is required");

 
        Assert.assertTrue(
            isPasswordErrorDisplayed || isUsernameErrorDisplayed,
            "Expected either 'Password is required' or 'Username is required' error to be displayed"
        );
    }


    @Test
    public void invalidUserShowsError() {
        LoginPage lp = new LoginPage(driver).navigate();
        lp.login("invalid_user", "invalid_pass");
        String error = lp.getError();
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

            // Step 1: Login
            LoginPage loginPage = new LoginPage(driver).navigate();
            loginPage.login(username, DataSet.PASSWORD);

            // Step 2: Verify successful login
            HomePage homePage = new HomePage(driver);
            Assert.assertEquals(homePage.getHeader(), "Products", userType + " login failed");

            // Step 3: Logout before next user
            new LogoutPage(driver).logout();
        }
    }
}
