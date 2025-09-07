package com.saucedemo.runners;

import org.testng.annotations.AfterClass;

import com.saucedemo.utils.DriverSetup;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.saucedemo.stepDefinitions",
    plugin = {"pretty","html:reports/cucumber-reports/cucumber.html"},
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
	 @AfterClass
	    public static void cleanUp() {
	        System.out.println("All tests completed. Closing the browser...");
	        DriverSetup.quitDriver();
	    }
}
