package page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourCartPage {
    WebDriver driver;

    @FindBy(className = "header_secondary_container")
    WebElement yourCartTitle;

    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement removeButton;

    @FindBy(className = "shopping_cart_badge")
    WebElement shoppingCartBadge;

    @FindBy(className = "cart_quantity")
    WebElement productQuantity;

    @FindBy(className = "inventory_item_name")
    WebElement productTitle;

    @FindBy(className = "inventory_item_desc")
    WebElement productDescription;

    @FindBy(className = "inventory_item_price")
    WebElement productPrice;

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    WebElement checkoutButton;


    public YourCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean yourCartPageIsDisplayed() {
        return yourCartTitle.isDisplayed();
    }

    public void clickOnRemoveButton() {
        removeButton.click();
    }

    public String CartBadgeText() {
        return shoppingCartBadge.getText();
    }

    public boolean productQuantityIsDisplayed() {
        return productQuantity.isDisplayed();
    }

    public boolean productTitleIsDisplayed() {
        return productTitle.isDisplayed();
    }

    public boolean productDescriptionIsDisplayed() {
        return productDescription.isDisplayed();
    }

    public boolean productPriceIsDisplayed() {
        return productPrice.isDisplayed();
    }

    public void clickOnContinueShoppingButton() {
        continueShoppingButton.click();
    }

    public CheckOutYourInformationPage clickOnCheckoutButton() {
        checkoutButton.click();
        return new CheckOutYourInformationPage(driver);
    }
}
