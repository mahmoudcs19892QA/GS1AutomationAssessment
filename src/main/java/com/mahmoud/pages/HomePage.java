package com.mahmoud.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{
    private final By fileUploadLink = By.linkText("File Upload");
    public HomePage(WebDriver driver){
        super(driver);
    }
    public FileUploadPage clickFileUpload() {
        click(fileUploadLink);
        return new FileUploadPage(driver);
    }}
