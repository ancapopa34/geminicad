package shopping_cart;

import org.junit.Test;
import page_object.*;
import utils.BaseTestClass;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShoppingCartTests extends BaseTestClass {
    @Test
    public void clickOnCartIconRedirectsToYourCartPage(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginOnPage("standard_user","secret_sauce");
        productsPage.addProductsToCart();
        YourCartPage yourCartPage = productsPage.clickOnIconCart();
        assertTrue(yourCartPage.yourCartPageIsDisplayed());
        assertEquals("https://www.saucedemo.com/cart.html", driver.getCurrentUrl());
    }

    @Test
    public void cartNumberBadgeIncreasesCorrectly(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginOnPage("standard_user","secret_sauce");
        productsPage.addProductsToCart();
        YourCartPage youCartPage = productsPage.clickOnIconCart();
        assertEquals("4", youCartPage.CartBadgeText());
    }

    @Test
    public  void removeProductFromCart(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginOnPage("standard_user", "secret_sauce");
        productsPage.addProductsToCart();
        YourCartPage youCartPage = productsPage.clickOnIconCart();
        assertEquals("4", youCartPage.CartBadgeText());
        youCartPage.clickOnRemoveButton();
        assertEquals("3", youCartPage.CartBadgeText());
    }

    @Test
    public void productDetailsAreDisplayed(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginOnPage("standard_user", "secret_sauce");
        productsPage.addProductsToCart();
        YourCartPage yourCartPage = productsPage.clickOnIconCart();
        assertTrue(yourCartPage.productQuantityIsDisplayed());
        assertTrue(yourCartPage.productTitleIsDisplayed());
        assertTrue(yourCartPage.productDescriptionIsDisplayed());
        assertTrue(yourCartPage.productPriceIsDisplayed());
    }

    @Test
    public  void continueShoppingButtonRedirectsUserToProductsPage(){
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginOnPage("standard_user", "secret_sauce");
        productsPage.addProductsToCart();
        YourCartPage yourCartPage = productsPage.clickOnIconCart();
        yourCartPage.clickOnContinueShoppingButton();
        assertTrue(productsPage.productsPageIsDisplayed());
        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }
}
