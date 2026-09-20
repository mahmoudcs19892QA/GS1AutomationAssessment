package com.mahmoud.manager;

import com.mahmoud.pages.*;
import org.openqa.selenium.WebDriver;

public class PageManager {

    private final WebDriver driver;
    private HomePage homePage;
    private FileUploadPage fileUploadPage;
    private UploadedFilePage uploadedFilePage;
    private DynamicLoadingPage dynamicLoadingPage;
    private DynamicLoadedPage dynamicLoadedPage;

    public PageManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver);
        }
        return homePage;
    }

    public FileUploadPage getFileUploadPage() {
        if (fileUploadPage == null) {
            fileUploadPage = new FileUploadPage(driver);
        }
        return fileUploadPage;
    }

    public UploadedFilePage getUploadedFilePage() {
        if (uploadedFilePage == null) {
            uploadedFilePage = new UploadedFilePage(driver);
        }
        return uploadedFilePage;
    }

    public DynamicLoadingPage getDynamicLoadingPage() {
        if (dynamicLoadingPage == null) {
            dynamicLoadingPage = new DynamicLoadingPage(driver);
        }
        return dynamicLoadingPage;
    }

    public DynamicLoadedPage getDynamicLoadedPage() {
        if (dynamicLoadedPage == null) {
            dynamicLoadedPage = new DynamicLoadedPage(driver);
        }
        return dynamicLoadedPage;
    }
}
