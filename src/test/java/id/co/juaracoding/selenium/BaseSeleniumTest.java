package id.co.juaracoding.selenium;

import id.co.juaracoding.util.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base class untuk seluruh Selenium UI Test.
 *
 * Seluruh test Selenium wajib extends class ini.
 */
public abstract class BaseSeleniumTest {

    protected WebDriver driver;

    /**
     * Setup browser sebelum setiap test.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUpBrowser() {
        driver = new ChromeDriver();

        driver.manage()
                .window()
                .maximize();

        driver.manage()
                .timeouts()
                .implicitlyWait(java.time.Duration.ofSeconds(10));

        driver.get(TestConfig.BASE_URL);
    }

    /**
     * Menutup browser setelah setiap test.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDownBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}