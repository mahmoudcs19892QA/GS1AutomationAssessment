package com.mahmoud.data;

import com.mahmoud.utils.ConfigReader;
import org.testng.annotations.DataProvider;

public class FileUploadDataProvider {

    @DataProvider(name = "fileUploadData")
    public Object[][] fileUploadData() {
        String fileName = ConfigReader.get("upload.file.name");
        if (fileName == null || fileName.trim().isEmpty()) {
            fileName = "sample.jpg";
        }
        return new Object[][]{
                {fileName}
        };
    }
}
