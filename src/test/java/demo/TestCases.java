package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
// import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;

// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("http://www.flipkart.com/");

    }

    @Test
    public void testCase01() throws InterruptedException {
        System.out.println("Start Test case: testCase01");
        driver.get("http://www.flipkart.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='q']")));
        // Eneter the keyword and click on search button
        Wrappers.enterText(searchBox, "Washing Machine");
        Thread.sleep(3000);
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        WebElement popularityButton = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[normalize-space()='Popularity']")));
        // Applying sorting on the basis popularity
        popularityButton.click();
        wait.until(ExpectedConditions.urlContains("popularity"));
        Thread.sleep(5000);

        List<WebElement> ratings = driver.findElements(By.xpath("//div[@class='yKfJKb row']//span/div"));
        // pring no of product having rating <= 4.0
        Wrappers.captureTheProductHavingRatingLessthanOrEqualToFour(ratings, driver);
        System.out.println("end Test case: testCase01");
    }

    @Test
    public void testCase02() throws InterruptedException {
        System.out.println("Start Test case: testCase02");
        driver.get("http://www.flipkart.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='q']")));
        // Eneter the keyword and click on search button
        Wrappers.enterText(searchBox, "iPhone");
        Thread.sleep(3000);
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        List<WebElement> discounts = driver
                .findElements(By.xpath("//div[@class='yKfJKb row']/div[2]/div/div/div[3]/span"));
        // storing title and discount value in list
        Wrappers.StoreTitleAndDiscount(driver, discounts);
        System.out.println("end Test case: testCase02");
    }

    @Test
    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='q']")));
        // Eneter the keyword and click on search button
        Wrappers.enterText(searchBox, "Coffee Mug");
        Thread.sleep(2000);
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        // apply filter "4★ & above" ratings
        Wrappers.selectCheckBox(driver, "4");
        Thread.sleep(3000);

        List<WebElement> viewsList = driver
                .findElements(By.xpath("//div[contains(@class,'afFzxY')]/span[@class='Wphh3N']"));
        // Print the top five viewed product
        Wrappers.topFiveRatedProduct(driver, viewsList);
        System.out.println("end Test case: testCase03");
    }

    @AfterTest
    public void endTest() {
        //driver.close();
        driver.quit();
    }
}