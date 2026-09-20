package com.mahmoud.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoadedPage extends BasePage {
    private final By startButton = By.xpath("//button[normalize-space()='Start']");
    private final By loadedText = By.cssSelector("#finish h4");

    public DynamicLoadedPage(WebDriver driver) {
        super(driver);
    }

    public DynamicLoadedPage clickStartButton() {
        click(startButton);
        return this;
    }

    public String getTextAfterLoading() {
        return getText(loadedText);
    }
}
