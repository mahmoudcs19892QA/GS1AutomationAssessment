package com.mahmoud.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FileUploadPage extends BasePage{
    private final By fileInput = By.id("file-upload");
    private final By uploadButton = By.id("file-submit");
    public FileUploadPage(WebDriver driver){
        super(driver);
    }
    public FileUploadPage uploadFile(String filePath) {
        type(fileInput, filePath);
        return this;
    }
    public UploadedFilePage clickUpload() {
        click(uploadButton);
        return new UploadedFilePage(driver);
    }
}
