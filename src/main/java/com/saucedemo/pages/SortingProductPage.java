package com.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortingProductPage extends BasePage {
    private By sortDropdown = By.cssSelector(".product_sort_container");
    private By productPrices = By.cssSelector(".inventory_item_price");
    private By productNames = By.cssSelector(".inventory_item_name");

    public SortingProductPage(WebDriver driver) {
        super(driver);
    }

    public void sortBy(String optionVisibleText) {
        new Select(driver.findElement(sortDropdown)).selectByVisibleText(optionVisibleText);
    }

    public boolean isSortedByPriceLowToHigh() {
        List<WebElement> priceElements = driver.findElements(productPrices);
        List<Double> prices = new ArrayList<>();

        for (WebElement el : priceElements) {
            prices.add(Double.parseDouble(el.getText().replace("$", "")));
        }

        List<Double> sorted = new ArrayList<>(prices);
        Collections.sort(sorted);

        return prices.equals(sorted);
    }

    public boolean isSortedByPriceHighToLow() {
        List<WebElement> priceElements = driver.findElements(productPrices);
        List<Double> prices = new ArrayList<>();

        for (WebElement el : priceElements) {
            prices.add(Double.parseDouble(el.getText().replace("$", "")));
        }

        List<Double> sorted = new ArrayList<>(prices);
        sorted.sort(Collections.reverseOrder());

        return prices.equals(sorted);
    }

    public boolean isSortedByNameAtoZ() {
        List<WebElement> nameElements = driver.findElements(productNames);
        List<String> names = new ArrayList<>();

        for (WebElement el : nameElements) {
            names.add(el.getText());
        }

        List<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted);

        return names.equals(sorted);
    }

    public boolean isSortedByNameZtoA() {
        List<WebElement> nameElements = driver.findElements(productNames);
        List<String> names = new ArrayList<>();

        for (WebElement el : nameElements) {
            names.add(el.getText());
        }

        List<String> sorted = new ArrayList<>(names);
        sorted.sort(Collections.reverseOrder());

        return names.equals(sorted);
    }
}

