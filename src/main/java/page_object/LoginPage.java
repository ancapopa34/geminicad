package page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(id = "user-name")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(name = "login-button")
    WebElement loginButton;
    @FindBy(xpath = "//form//h3")
    WebElement errorOnInvalidUsername;

    public LoginPage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public ProductsPage loginOnPage(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        return new ProductsPage(driver);
    }

    public String errorMsgOnInvalidUsername() {
        return errorOnInvalidUsername.getText();
    }
}
