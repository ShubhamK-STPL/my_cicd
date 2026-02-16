package com.google;

import org.openqa.selenium.By;
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
        options.addArguments("--headless"); // Uncomment for CI/CD
        options.addArguments("window-size=1920,1080");
        driver = new ChromeDriver(options);
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
        try {
            // driver.get("https://demo-jalvitaran.shauryatechnosoft.com/home");
             driver.get("https://jalvitaran.erpguru.in/home");
            // driver.get("http://localhost:3000");
            System.out.println("🌐 Opened URL");

            boolean loginVisible = driver.findElement(By.xpath("//*[contains(text(),'Corporation Login')]")).isDisplayed();
            System.out.println("🧩 Login button visible: " + loginVisible);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[contains(text(),'Corporation Login')]")
            ));
            loginBtn.click();
            System.out.println("🔘 Clicked on login");

            String url = driver.getCurrentUrl();
            System.out.println("🔗 Current URL: " + url);
            assert url.contains("jalvitaran");
            // assert url.contains("localhost");
            System.out.println("✅ Test Passed");

        } catch (Exception e) {
            System.out.println("❌ Test Failed: " + e.getMessage());
            assert false;
            // extra commit
        }
    }
}
