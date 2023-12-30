package page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutCompletePage {
    WebDriver driver;

    @FindBy(id = "back-to-products")
    WebElement backHomeButton;

    @FindBy(xpath = "//div//h2")
    WebElement orderCompleteMessage;


    public CheckoutCompletePage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public ProductsPage clickOnBackHomeButton() {
        backHomeButton.click();
        return new ProductsPage(driver);
    }

    public String getOrderCompleteMessage() {
        return  orderCompleteMessage.getText();
    }
}
