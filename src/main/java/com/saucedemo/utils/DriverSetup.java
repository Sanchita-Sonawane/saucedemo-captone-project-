package com.saucedemo.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSetup {

    // Thread-safe WebDriver storage
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver getDriver(String browser) {
        if (driver.get() == null) {
            switch (browser.toLowerCase()) {
                case "firefox":
                    driver.set(new FirefoxDriver());
                    break;

                case "edge":
                    driver.set(new EdgeDriver());
                    break;

                case "chrome":
                default:
                    ChromeOptions options = new ChromeOptions();

                    // ✅ Disable password manager
                    Map<String, Object> prefs = new HashMap<>();
                    prefs.put("credentials_enable_service", false);
                    prefs.put("profile.password_manager_enabled", false);
                    options.setExperimentalOption("prefs", prefs);

                    // Optional: Incognito to avoid caching saved logins
                    options.addArguments("--incognito");

                    driver.set(new ChromeDriver(options));
                    break;
            }
        }

        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove(); // ✅ Clears ThreadLocal to prevent memory leaks
        }
    }
}
