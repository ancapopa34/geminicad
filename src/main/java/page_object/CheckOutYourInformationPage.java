package page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutYourInformationPage {
    WebDriver driver;

    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement postalCodeField;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "cancel")
    WebElement cancelButton;

    @FindBy(className = "title")
    WebElement checkoutYourInformationTitle;

    @FindBy(xpath = "//form//h3")
    WebElement checkoutFormError;

    public CheckOutYourInformationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean checkoutYourInformationPageIsDisplayed() {
        return checkoutYourInformationTitle.isDisplayed();
    }

    public CheckOutYourInformationPage checkout() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginOnPage("standard_user", "secret_sauce");
        productsPage.addProductsToCart();
        YourCartPage yourCartPage = productsPage.clickOnIconCart();
        yourCartPage.clickOnCheckoutButton();
        return new CheckOutYourInformationPage(driver);
    }

    public CheckoutOverviewPage checkoutForm(String firstName, String lastName, String postalCode) {
        firstNameField.sendKeys(firstName);
        lastNameField.sendKeys(lastName);
        postalCodeField.sendKeys(postalCode);
        return new CheckoutOverviewPage(driver);
    }

    public CheckoutOverviewPage clickOnContinueButton() {
        continueButton.click();
        return new CheckoutOverviewPage(driver);
    }

    public String checkoutFormErrorMessage() {
        return checkoutFormError.getText();
    }

    public String getClassNames() {
        return firstNameField.getAttribute("class");
    }

    public YourCartPage clickOnCancelButton() {
        cancelButton.click();
        return new YourCartPage(driver);
    }
}
