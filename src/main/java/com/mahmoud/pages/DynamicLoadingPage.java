package com.mahmoud.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoadingPage extends BasePage {
    private final By exampleTwoLink = By.linkText("Example 2: Element rendered after the fact");

    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
    }

    public DynamicLoadedPage clickExampleTwoLink() {
        click(exampleTwoLink);
        return new DynamicLoadedPage(driver);
    }
}
