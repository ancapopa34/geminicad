package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductsPage {
    WebDriver driver;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement addToCartBackpackButton;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    WebElement addToCartBikeButton;

    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    WebElement addToCartJacketButton;

    @FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
    WebElement addToCartTshirtRedButton;

    @FindBy(id = "shopping_cart_container")
    WebElement cartIcon;

    @FindBy(className = "title")
    WebElement productsTitle;

    @FindBy(className = "product_sort_container")
    WebElement sortButton;


    @FindBy(css = ".inventory_list > div:nth-of-type(1)")
    WebElement firstProduct;

    @FindBy(css = ".inventory_list > div:nth-of-type(2)")
    WebElement secondProduct;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addProductsToCart() {
        addToCartBackpackButton.click();
        addToCartBikeButton.click();
        addToCartJacketButton.click();
        addToCartTshirtRedButton.click();
    }

    public YourCartPage clickOnIconCart() {
        cartIcon.click();
        return new YourCartPage(driver);
    }

    public boolean productsPageIsDisplayed() {
        return productsTitle.isDisplayed();
    }

    public int compare() {
        sortButton.click();
        Select name = new Select(sortButton);
        name.selectByVisibleText("Name (Z to A)");

        String firstProductTitle = firstProduct.findElement(By.className("inventory_item_name")).getText();
        String secondProductTitle = secondProduct.findElement(By.className("inventory_item_name")).getText();

        return firstProductTitle.compareTo(secondProductTitle);
    }

    public boolean comparePrice() {
        sortButton.click();
        Select name = new Select(sortButton);
        name.selectByVisibleText("Price (high to low)");

        String firstProductPrice = firstProduct.findElement(By.className("inventory_item_price")).getText().substring(1);
        String secondProductPrice = secondProduct.findElement(By.className("inventory_item_price")).getText().substring(1);

        return Float.parseFloat(firstProductPrice) > Float.parseFloat(secondProductPrice);
    }

    public boolean productsList() {
        List<WebElement> elements = driver.findElements(By.className("inventory_item"));

        for (WebElement element : elements) {
            boolean image = element.findElement(By.className("inventory_item_img")).isDisplayed();
            boolean title = element.findElement(By.className("inventory_item_name")).isDisplayed();
            boolean description = element.findElement(By.className("inventory_item_desc")).isDisplayed();
            boolean price = element.findElement(By.className("inventory_item_price")).isDisplayed();
            boolean addToCartBtn = element.findElement(By.className("btn_inventory")).isDisplayed();

            if (!image || !title || !description || !price || !addToCartBtn) {
                return false;
            }
        }

        return true;
    }
}
