package id.co.juaracoding.selenium;

import id.co.juaracoding.selenium.pages.LoginPage;
import id.co.juaracoding.util.TestConfig;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test automation untuk fitur Login.
 */
public class LoginTest extends BaseSeleniumTest {

    private LoginPage loginPage;

    /**
     * Inisialisasi LoginPage sebelum setiap test.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUpLoginPage() {
        loginPage = new LoginPage(driver);
    }

    /**
     * Positive test:
     * Username dan password valid harus diarahkan ke dashboard.
     */
    @Test
    public void login_success_with_valid_credentials() {

        loginPage.login(
                TestConfig.VALID_USERNAME,
                TestConfig.VALID_PASSWORD
        );

        String expectedUrl = TestConfig.BASE_URL + "/dashboard";
        String actualUrl = loginPage.getCurrentUrl();

        Assert.assertEquals(
                actualUrl,
                expectedUrl,
                "Login berhasil tetapi URL tidak mengarah ke halaman dashboard"
        );
    }

    /**
     * Negative test:
     * Password tidak valid harus menghasilkan error message.
     */
    @Test
    public void login_failed_with_invalid_password() {

        loginPage.login(
                TestConfig.VALID_USERNAME,
                "InvalidPassword@123"
        );

        Assert.assertTrue(
                loginPage.isErrorMessageDisplayed(),
                "Error message tidak muncul ketika password tidak valid"
        );
    }
}