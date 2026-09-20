package com.mahmoud.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {
    private final By fileUploadLink = By.linkText("File Upload");
    private final By dynamicLoadingLink = By.linkText("Dynamic Loading");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public FileUploadPage clickFileUpload() {
        click(fileUploadLink);
        return new FileUploadPage(driver);
    }

    public DynamicLoadingPage clickDynamicLoading() {
        click(dynamicLoadingLink);
        return new DynamicLoadingPage(driver);
    }
}
