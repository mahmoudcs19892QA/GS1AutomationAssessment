package com.mahmoud.manager;

import com.mahmoud.pages.FileUploadPage;
import com.mahmoud.pages.HomePage;
import com.mahmoud.pages.UploadedFilePage;
import org.openqa.selenium.WebDriver;

public class PageManager {

    private WebDriver driver;
    private HomePage homePage;
    private FileUploadPage fileUploadPage;
    private UploadedFilePage uploadedFilePage;

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
}
