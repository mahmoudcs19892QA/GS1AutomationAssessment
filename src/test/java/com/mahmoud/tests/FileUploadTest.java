package com.mahmoud.tests;

import com.mahmoud.base.BaseTest;
import com.mahmoud.data.FileUploadDataProvider;
import com.mahmoud.pages.UploadedFilePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class FileUploadTest extends BaseTest {

    @Test(
            dataProvider = "fileUploadData",
            dataProviderClass = FileUploadDataProvider.class
    )
    public void verifyFileUpload(String fileName) {
        openBaseUrl();

        String filePath = getDirectory("base.directory", fileName);
        UploadedFilePage uploadedFilePage = pageManager.getHomePage()
                .clickFileUpload()
                .uploadFile(filePath)
                .clickUpload();

        String expectedFileName = Paths.get(fileName)
                .getFileName()
                .toString();

        Assert.assertEquals(
                uploadedFilePage.getHeaderText(),
                "File Uploaded!",
                "Upload confirmation header does not match expected message."
        );
        Assert.assertEquals(
                uploadedFilePage.getUploadedFileName(),
                expectedFileName,
                "Uploaded file name does not match expected file name."
        );
    }
}
