package com.saucedemo.stepDefinitions;

import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.LogoutPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import com.saucedemo.utils.DriverSetup;
import com.saucedemo.utils.DataSet;
import org.testng.Assert;

public class LoginSteps {
	private WebDriver driver = DriverSetup.getDriver("chrome");
    LoginPage loginPage;
    HomePage homePage;

        @Given("I am on the SauceDemo login page")
        public void i_am_on_the_sauce_demo_login_page() {
            loginPage = new LoginPage(driver).navigate();
        }

        @When("I login with username {string} and password {string}")
        public void i_login_with_username_and_password(String username, String password) {
            loginPage.login(username, password);
        }

        @Then("I should see the Products page")
        public void i_should_see_the_products_page() {
            homePage = new HomePage(driver);
            Assert.assertEquals(homePage.getHeader(), "Products", "User should land on Products page");
        }

        @Then("I should see an error message containing {string}")
        public void i_should_see_an_error_message_containing(String expectedMessage) {
            String actualMessage = loginPage.getError().toLowerCase();
            Assert.assertTrue(actualMessage.contains(expectedMessage.toLowerCase()),
                    "Expected error message to contain: '" + expectedMessage + "', but got: '" + actualMessage + "'");
        }

        @When("I try to login with username {string} and no password")
        public void i_try_to_login_with_username_and_no_password(String username) {
            loginPage.login(username, "");
        }

        @When("I try to login with no username and password {string}")
        public void i_try_to_login_with_no_username_and_password(String password) {
            loginPage.login("", password);
        }

        @And("I logout")
        public void i_logout() {
            new LogoutPage(driver).logout();
        }
}
