package com.mahmoud.tests;

import com.mahmoud.base.BaseTest;
import com.mahmoud.pages.FileUploadPage;
import com.mahmoud.pages.HomePage;
import com.mahmoud.pages.UploadedFilePage;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.mahmoud.data.FileUploadDataProvider;

import java.nio.file.Paths;

public class FileUploadTest extends BaseTest {

    @Test(
            dataProvider = "fileUploadData",
            dataProviderClass = FileUploadDataProvider.class
    )
    public void verifyFileUpload(String fileName) {
        openBaseUrl();
        HomePage homePage = pageManager.getHomePage();
        FileUploadPage fileUploadPage = homePage.clickFileUpload();
        String filePath = getDirectory("base.directory",fileName);
        UploadedFilePage uploadedFilePage = fileUploadPage
                .uploadFile(filePath)
                .clickUpload();
        String expectedFileName = Paths.get(fileName)
                .getFileName()
                .toString();

        Assert.assertEquals(
                uploadedFilePage.getUploadedFileName(),
                expectedFileName
        );

    }
}
