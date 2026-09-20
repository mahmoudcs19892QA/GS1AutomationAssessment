package com.mahmoud.manager;

import com.mahmoud.pages.*;
import org.openqa.selenium.WebDriver;

public class PageManager {

    private WebDriver driver;
    private HomePage homePage;
    private FileUploadPage fileUploadPage;
    private UploadedFilePage uploadedFilePage;
    private DynamicLoadingPage dynamicLoadingpage;
    private DynamicLoadedPage dynamicLoadedpage;

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
    public DynamicLoadingPage getdynamicLoadingPage() {

        if (dynamicLoadingpage == null) {
            dynamicLoadingpage = new DynamicLoadingPage(driver);
        }

        return dynamicLoadingpage;
    }
    public DynamicLoadedPage getdynamicLoadedPage() {

        if (dynamicLoadedpage == null) {
            dynamicLoadedpage = new DynamicLoadedPage(driver);
        }

        return dynamicLoadedpage;
    }
}
