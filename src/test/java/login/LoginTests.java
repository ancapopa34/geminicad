package login;

import org.junit.Test;
import page_object.LoginPage;
import utils.BaseTestClass;

import static org.junit.Assert.assertEquals;

public class LoginTests extends BaseTestClass{
    @Test
    public void validLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginOnPage("standard_user","secret_sauce");
        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    @Test
    public void loginWithInvalidUserName() {
        LoginPage loginPage =  new LoginPage(driver);
        loginPage.loginOnPage("standarduser","secret_sauce");
        assertEquals("Epic sadface: Username and password do not match any user in this service", loginPage.errorMsgOnInvalidUsername());
    }
}
