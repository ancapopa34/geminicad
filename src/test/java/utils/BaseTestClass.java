package utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTestClass {
    public WebDriver driver;
    private static final String URL = "https://www.saucedemo.com/";

    @Before
    public void setUpTest() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/drivers/windows/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get(URL);
    }

   @After
   public void tearDown() {
        driver.quit();
    }
}