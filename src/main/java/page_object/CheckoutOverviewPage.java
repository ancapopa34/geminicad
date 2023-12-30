package page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutOverviewPage {

    WebDriver driver;

    @FindBy(className = "title")
    WebElement checkoutOverviewTitle;

    @FindBy(className = "cart_list")
    WebElement productsList;

    @FindBy(className = "summary_value_label")
    WebElement paymentInformation;

    @FindBy(className = "summary_info_label")
    WebElement shippingInformation;

    @FindBy(className = "summary_subtotal_label")
    WebElement priceTotal;

    @FindBy(className = "summary_total_label")
    WebElement total;

    @FindBy(id = "cancel")
    WebElement cancelButtonCheckoutOverviewPage;

    @FindBy(id = "finish")
    WebElement finishButton;


    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public boolean checkoutOverviewPageIsDisplayed() {
        return checkoutOverviewTitle.isDisplayed();
    }

    public boolean productsListIsDisplayed() {
        return productsList.isDisplayed();
    }

    public boolean paymentInformationIsDisplayed() {
        return paymentInformation.isDisplayed();
    }

    public boolean shippingInformationIsDisplayed() {
        return shippingInformation.isDisplayed();
    }

    public boolean priceTotalIsDisplayed() {
        return priceTotal.isDisplayed();
    }

    public boolean totalIsDisplayed() {
        return total.isDisplayed();
    }

    public ProductsPage clickOnCancelButtonFromCheckoutOverviewPage() {
        cancelButtonCheckoutOverviewPage.click();
        return new ProductsPage(driver);
    }

    public CheckoutCompletePage clickOnFinishButton() {
        finishButton.click();
        return new CheckoutCompletePage(driver);
    }

}
