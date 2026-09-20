package com.mahmoud.tests;

import com.mahmoud.base.BaseTest;
import com.mahmoud.pages.DynamicLoadedPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicLoadingTest extends BaseTest {

    @Test
    public void verifyDynamicLoading() {
        openBaseUrl();

        DynamicLoadedPage dynamicLoadedPage = pageManager.getHomePage()
                .clickDynamicLoading()
                .clickExampleTwoLink()
                .clickStartButton();

        Assert.assertEquals(
                dynamicLoadedPage.getTextAfterLoading(),
                "Hello World!",
                "The dynamically loaded text does not match the expected message."
        );
    }
}
