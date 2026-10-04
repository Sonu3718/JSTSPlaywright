package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import com.salesforce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidLoginTest extends BaseTest {
    private static final String LOGIN_URL = "https://login.salesforce.com/?locale=in";

    @Test
    public void testInvalidLoginDisplaysErrorMessage() {
        try {
            driver.get(LOGIN_URL);
            LoginPage loginPage = new LoginPage(driver);

            Assert.assertTrue(loginPage.getPageTitle().contains("Salesforce"), "Page title did not match expected Salesforce title");

            loginPage.doLogin("invalid.user@domain.com", "WrongPassword123");

            Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message was not displayed for invalid login");
            String actualError = loginPage.getErrorMessage();
            Assert.assertTrue(actualError.length() > 0, "Error message text is empty");
            Assert.assertTrue(actualError.toLowerCase().contains("check your username and password"), "Error message content mismatch: " + actualError);
        } catch (Exception e) {
            Assert.fail("Invalid login execution encountered an exception: " + e.getMessage(), e);
        }
    }
}
