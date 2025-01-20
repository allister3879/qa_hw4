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

