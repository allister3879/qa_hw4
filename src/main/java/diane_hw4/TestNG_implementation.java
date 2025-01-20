package diane_hw4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TestNG_implementation {
    WebDriver driver;
    WebDriverWait wait; 

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.edge.driver", "C:\\Program Files\\msedgedriver.exe");
        driver = new EdgeDriver();
        
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test(priority = 1)
    public void openWebsite() {
        driver.get("https://www.saucedemo.com/");
        pause(2000); 

        wait.until(ExpectedConditions.titleIs("Swag Labs"));
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
    }

    @Test(priority = 2)
    public void login() {
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login-button")));

        username.sendKeys("standard_user");
        pause(1000); 

        password.sendKeys("secret_sauce");
        pause(1000); 

        loginButton.click();
        pause(2000); 

        wait.until(ExpectedConditions.urlContains("inventory"));
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test(priority = 3)
    public void addToCart() {
        WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".inventory_item:first-child .btn_inventory")));
        pause(2000); 
        firstProduct.click();
        
        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
        pause(2000); 

        Assert.assertTrue(cartBadge.isDisplayed());
    }

    @AfterClass
    public void teardown() {
        pause(2000); 
        driver.quit();
    }

    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


