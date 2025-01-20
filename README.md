### Diana Narynbekova, SE-2217

## Task 1 : Implement TestNG annotations

This Java program uses **Selenium WebDriver** and **TestNG** to automate tests on the **SauceDemo** website using **Microsoft Edge**. It opens the website, logs in with a test account, and adds a product to the cart. The code includes **explicit waits** for synchronization and **assertions** to verify expected outcomes.

```java
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
```
https://github.com/user-attachments/assets/4b16ba31-37c3-49a0-a8ee-41cb83d2add0

## Task 2 - Configure and implement logging
This Java program uses **Selenium WebDriver** and **TestNG** to automate interactions with the **SauceDemo** website on **Microsoft Edge**. It performs three main actions: opening the website, logging in, and adding a product to the cart. The code includes **explicit waits** for synchronization, **delays for visibility**, and **assertions** to verify successful execution.
```java
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
```
https://github.com/user-attachments/assets/474bac1e-7b66-444d-9659-d2ef751b37e4

## Task 3 - Implement Extent Report and Screenshots
This Java program uses **Selenium WebDriver**, **TestNG**, and **Extent Reports** to automate testing on **YouTube** using **Microsoft Edge**. It performs two actions: opening YouTube and searching for "Rasputin Dance." Screenshots are captured at each step and saved in the "reports/screenshots" folder. The program generates an HTML report with logs and screenshots using **Extent Reports** to track test results.
```java
package diane_hw4;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class ExtentReport_test {
    WebDriver driver;
    WebDriverWait wait;
    ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void setup() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/extent.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Browser", "Edge");
        extent.setSystemInfo("Tester", "Diane");

        System.setProperty("webdriver.edge.driver", "C:\\Program Files\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void openYouTube() {
        test = extent.createTest("Open YouTube", "Verify that YouTube opens successfully");
        driver.get("https://www.youtube.com/");
        sleep(2000);  
        test.addScreenCaptureFromPath(takeScreenshot("YouTube_Home"));

        Assert.assertTrue(driver.getTitle().contains("YouTube"));
        test.log(Status.PASS, "YouTube opened successfully.");
    }

    @Test(priority = 2)
    public void searchRasputinDance() {
        test = extent.createTest("Search Rasputin Dance", "Search for 'Rasputin Dance' on YouTube");

        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search_query")));
        searchBox.sendKeys("Rasputin Dance");
        test.log(Status.INFO, "Entered 'Rasputin Dance' in the search box.");

        searchBox.submit();
        test.log(Status.INFO, "Clicked the search button.");
        sleep(2000);  

        test.addScreenCaptureFromPath(takeScreenshot("Search_Results"));
        Assert.assertTrue(driver.getTitle().contains("Rasputin Dance"));
        test.log(Status.PASS, "Search completed successfully.");
    }

    @AfterClass
    public void teardown() {
        test = extent.createTest("Teardown", "Close the browser");
        driver.quit();
        test.log(Status.INFO, "Browser closed.");
        extent.flush();
    }

    public String takeScreenshot(String fileName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String filePath = "reports/screenshots/" + fileName + ".png";
        File destFile = new File(filePath);
        try {
            FileHandler.copy(srcFile, destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
https://github.com/user-attachments/assets/93400004-4887-44c9-a8e6-a8d184a5b672

### Screenshots ->

![Image](https://github.com/user-attachments/assets/1db57852-f930-4314-9fde-edc41c40bb80)

![Image](https://github.com/user-attachments/assets/6985753f-9a1d-4e23-90bb-e6afbf0f64bc)
