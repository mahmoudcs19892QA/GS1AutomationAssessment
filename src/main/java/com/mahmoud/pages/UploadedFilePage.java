package com.mahmoud.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadedFilePage extends BasePage{

    private final By uploadedFileName = By.id("uploaded-files");

    public UploadedFilePage(WebDriver driver){
        super(driver);
    }
    public String getUploadedFileName() {
        return getText(uploadedFileName);
    }
}
