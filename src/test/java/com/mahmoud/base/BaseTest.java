package com.mahmoud.base;

import com.mahmoud.manager.PageManager;
import com.mahmoud.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Paths;

public class BaseTest {
    protected WebDriver driver;
    protected PageManager pageManager;

    @BeforeMethod
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        pageManager = new PageManager(driver);
    }

    protected String getDirectory(String directoryKey, String fileName) {
        return Paths.get(ConfigReader.get(directoryKey), fileName)
                .toAbsolutePath()
                .toString();
    }

    protected void openBaseUrl() {
        driver.get(ConfigReader.get("base.url"));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
