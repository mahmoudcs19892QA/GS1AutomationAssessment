package com.mahmoud.data;

import org.testng.annotations.DataProvider;

public class FileUploadDataProvider {

    @DataProvider(name = "fileUploadData")
    public Object[][] fileUploadData() {

        return new Object[][]{
                {"sample.jpg"}
        };
    }
}
