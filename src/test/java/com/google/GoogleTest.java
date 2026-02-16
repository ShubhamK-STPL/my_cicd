package com.google;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class GoogleTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() {

        ChromeOptions options = new ChromeOptions();

        // ✅ REQUIRED for CI (Linux runners)
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        // ✅ CRITICAL: Prevent infinite load wait
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        // ✅ Handle possible SSL issues
        options.setAcceptInsecureCerts(true);

        driver = new ChromeDriver(options);

        // ✅ Override default 5 min timeout
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

        System.out.println("🔧 Browser launched");
    }

    @AfterClass
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("✅ Browser closed");
        }
    }

    @Test
    public void testOpenAndClick() {

        driver.get("https://jalvitaran.erpguru.in/home");
        System.out.println("🌐 Opened URL");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[contains(text(),'Corporation Login')]")
        ));

        System.out.println("🧩 Login button visible: " + loginBtn.isDisplayed());

        loginBtn.click();
        System.out.println("🔘 Clicked on login");

        String url = driver.getCurrentUrl();
        System.out.println("🔗 Current URL: " + url);

        assert url.contains("jalvitaran");

        System.out.println("✅ Test Passed");
    }
}
