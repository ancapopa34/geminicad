package sort;

import org.junit.Test;
import page_object.LoginPage;
import page_object.ProductsPage;
import utils.BaseTestClass;
import static org.junit.Assert.*;

public class SortTests extends BaseTestClass {
    @Test
    public void sortItemsFromZtoA() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage =  loginPage.loginOnPage("standard_user", "secret_sauce");
        assertEquals(1, productsPage.compare());
    }

    @Test
    public void sortItemsHighToLow() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginOnPage("standard_user", "secret_sauce");

        assertTrue(productsPage.comparePrice());
    }

    @Test
    public void listOfProductsIsDisplayedCorrectly() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginOnPage("standard_user", "secret_sauce");

        assertTrue(productsPage.productsList());
    }
}
