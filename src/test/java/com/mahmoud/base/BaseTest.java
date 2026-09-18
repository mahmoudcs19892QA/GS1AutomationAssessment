package com.mahmoud.base;

import com.mahmoud.manager.PageManager;
import com.mahmoud.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected WebDriver driver;
    protected PageManager pageManager;


    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();

        pageManager = new PageManager(driver);
    }

    protected void openBaseUrl() {
        driver.get(ConfigReader.get("base.url"));
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}

