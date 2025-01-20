package diane_hw4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Log4_test {
    WebDriver driver;
    WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(Log4_test.class);

    @BeforeClass
    public void setup() {
        logger.info("Starting Edge WebDriver...");
        System.setProperty("webdriver.edge.driver", "C:\\Program Files\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(priority = 1)
    public void openYouTube() {
        driver.get("https://www.youtube.com/");
        pause(2000);
        logger.info("Opened YouTube successfully.");
        Assert.assertTrue(driver.getTitle().contains("YouTube"));
    }

    @Test(priority = 2)
    public void searchVideo() {
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("search_query")));
        searchBox.sendKeys("asmr");
        logger.info("Entered search query: asmr");

        searchBox.submit();
        logger.info("Clicked on search button.");

        pause(2000);
        Assert.assertTrue(driver.getTitle().contains("asmr"));
    }

    @Test(priority = 3)
    public void playFirstVideo() {
        WebElement firstVideo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//ytd-video-renderer)[1]")));
        firstVideo.click();
        logger.info("Clicked on the first video.");
        
        pause(3000); 
        Assert.assertTrue(driver.getCurrentUrl().contains("watch"));
    }

    @AfterClass
    public void teardown() {
        logger.info("Test completed. Closing browser...");
        pause(1000);
        driver.quit();
    }

    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            logger.error("Thread interrupted: " + e.getMessage());
        }
    }
}
