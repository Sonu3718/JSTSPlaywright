package com.salesforce.tests;

import com.salesforce.base.BaseTest;
import com.salesforce.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidLoginTest extends BaseTest {
    private static final String LOGIN_URL = "https://login.salesforce.com/?locale=in";

    @Test
    public void testValidLoginInterfaceAndSubmission() {
        try {
            driver.get(LOGIN_URL);
            LoginPage loginPage = new LoginPage(driver);

            Assert.assertTrue(loginPage.getPageTitle().contains("Salesforce"), "Page title did not match expected Salesforce title");
            Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button is not displayed on page");

            loginPage.enterUsername("automation_user@salesforce.com");
            loginPage.enterPassword("ValidSecurePassword2026!");
            loginPage.clickRememberMe();
            loginPage.clickLogin();
        } catch (Exception e) {
            Assert.fail("Valid login execution encountered an exception: " + e.getMessage(), e);
        }
    }
}
