package checkout;

import org.junit.Test;
import page_object.*;
import utils.BaseTestClass;

import static org.junit.Assert.*;

public class CheckoutTests extends BaseTestClass {

    @Test
    public void checkoutButtonRedirectsUserToCheckoutYourInformationPage() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginOnPage("standard_user", "secret_sauce");
        productsPage.addProductsToCart();
        YourCartPage yourCartPage = productsPage.clickOnIconCart();
        CheckOutYourInformationPage checkOutYourInformationPage = yourCartPage.clickOnCheckoutButton();
        assertEquals("https://www.saucedemo.com/checkout-step-one.html", driver.getCurrentUrl());
        assertTrue(checkOutYourInformationPage.checkoutYourInformationPageIsDisplayed());
    }

    @Test
    public void validCheckout() {
        CheckOutYourInformationPage checkOutYourInformationPage = new CheckOutYourInformationPage(driver);
        checkOutYourInformationPage.checkout();
        checkOutYourInformationPage.checkoutForm("Walter", "Smith", "700222");
        CheckoutOverviewPage checkoutOverviewPage = checkOutYourInformationPage.clickOnContinueButton();
        assertTrue(checkoutOverviewPage.checkoutOverviewPageIsDisplayed());
        assertEquals("https://www.saucedemo.com/checkout-step-two.html", driver.getCurrentUrl());
    }

    @Test
    public void invalidCheckoutMissingFirstName() {
        CheckOutYourInformationPage checkOutYourInformationPage = new CheckOutYourInformationPage(driver);
        checkOutYourInformationPage.checkout();
        checkOutYourInformationPage.checkoutForm("", "Smith", "700222");
        assertFalse(checkOutYourInformationPage.getClassNames().contains("form_input error"));
        checkOutYourInformationPage.clickOnContinueButton();
        assertTrue(checkOutYourInformationPage.getClassNames().contains("form_input error"));
        assertEquals("Error: First Name is required", checkOutYourInformationPage.checkoutFormErrorMessage());
    }

    @Test
    public void cancelCheckoutRedirectsUserToShoppingCart() {
        CheckOutYourInformationPage checkOutYourInformationPage = new CheckOutYourInformationPage(driver);
        checkOutYourInformationPage.checkout();
        YourCartPage yourCartPage = checkOutYourInformationPage.clickOnCancelButton();
        assertEquals("https://www.saucedemo.com/cart.html", driver.getCurrentUrl());
        assertTrue(yourCartPage.yourCartPageIsDisplayed());
    }

    @Test
    public void allOrderDetailsAreDisplayedOnCheckoutOverviewPage() {
        CheckOutYourInformationPage checkOutYourInformationPage = new CheckOutYourInformationPage(driver);
        checkOutYourInformationPage.checkout();
        checkOutYourInformationPage.checkoutForm("Walter", "Smith", "700222");
        CheckoutOverviewPage checkoutOverviewPage = checkOutYourInformationPage.clickOnContinueButton();
        assertTrue(checkoutOverviewPage.productsListIsDisplayed());
        assertTrue(checkoutOverviewPage.paymentInformationIsDisplayed());
        assertTrue(checkoutOverviewPage.shippingInformationIsDisplayed());
        assertTrue(checkoutOverviewPage.priceTotalIsDisplayed());
        assertTrue(checkoutOverviewPage.totalIsDisplayed());
    }

    @Test
    public void cancelButtonFromCheckoutOverviewRedirectsUserToProductPage() {
        CheckOutYourInformationPage checkOutYourInformationPage = new CheckOutYourInformationPage(driver);
        checkOutYourInformationPage.checkout();
        checkOutYourInformationPage.checkoutForm("Walter", "Smith", "700222");
        CheckoutOverviewPage checkoutOverviewPage = checkOutYourInformationPage.clickOnContinueButton();
        ProductsPage productsPage = checkoutOverviewPage.clickOnCancelButtonFromCheckoutOverviewPage();
        assertTrue(productsPage.productsPageIsDisplayed());
        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }

    @Test
    public void finishButtonRedirectsUserToCheckoutCompletePage() {
        CheckOutYourInformationPage checkOutYourInformationPage = new CheckOutYourInformationPage(driver);
        checkOutYourInformationPage.checkout();
        checkOutYourInformationPage.checkoutForm("Walter", "Smith", "700222");
        CheckoutOverviewPage checkoutOverviewPage = checkOutYourInformationPage.clickOnContinueButton();
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickOnFinishButton();
        assertEquals("https://www.saucedemo.com/checkout-complete.html", driver.getCurrentUrl());
        assertEquals("Thank you for your order!", checkoutCompletePage.getOrderCompleteMessage());
    }

    @Test
    public void backHomeButtonRedirectsUserToProductsPage() {
        CheckOutYourInformationPage checkOutYourInformationPage = new CheckOutYourInformationPage(driver);
        checkOutYourInformationPage.checkout();
        checkOutYourInformationPage.checkoutForm("Walter", "Smith", "700222");
        CheckoutOverviewPage checkoutOverviewPage = checkOutYourInformationPage.clickOnContinueButton();
        CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.clickOnFinishButton();
        ProductsPage productsPage = checkoutCompletePage.clickOnBackHomeButton();
        assertTrue(productsPage.productsPageIsDisplayed());
        assertEquals("https://www.saucedemo.com/inventory.html", driver.getCurrentUrl());
    }
}
