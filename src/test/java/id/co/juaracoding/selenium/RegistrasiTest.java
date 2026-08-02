package id.co.juaracoding.selenium;

import id.co.juaracoding.selenium.pages.RegisterPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test automation untuk fitur Registrasi.
 */
public class RegistrasiTest extends BaseSeleniumTest {

    private RegisterPage registerPage;

    /**
     * Inisialisasi RegisterPage sebelum setiap test.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUpRegisterPage() {
        registerPage = new RegisterPage(driver);
    }

    /**
     * Positive test:
     * Registrasi dengan data lengkap dan KTP/NPWP 16 digit.
     */
    @Test
    public void registration_success_with_valid_data() {

        String uniqueSuffix = String.valueOf(System.currentTimeMillis());

        String fullName = "Mohammad Faisal Dzikri";
        String email = "faisal" + uniqueSuffix + "@example.com";
        String username = "faisal" + uniqueSuffix;
        String password = "Test@12345";
        String phone = "081234567890";

        String validKtp = "3201234567890123";
        String validNpwp = "1234567890123456";

        registerPage.fillRegistrationForm(
                fullName,
                email,
                username,
                password,
                password,
                phone,
                validKtp,
                validNpwp
        );

        registerPage.clickRegister();

        Assert.assertTrue(
                registerPage.isSuccessMessageDisplayed(),
                "Pesan sukses registrasi tidak muncul untuk data yang valid"
        );
    }

    /**
     * Negative test:
     * KTP hanya memiliki 10 digit sehingga registrasi harus ditolak.
     */
    @Test
    public void registration_failed_with_invalid_ktp() {

        String uniqueSuffix = String.valueOf(System.currentTimeMillis());

        String fullName = "Mohammad Faisal Dzikri";
        String email = "invalid" + uniqueSuffix + "@example.com";
        String username = "invalid" + uniqueSuffix;
        String password = "Test@12345";
        String phone = "081234567890";

        String invalidKtp = "1234567890";
        String validNpwp = "1234567890123456";

        registerPage.fillRegistrationForm(
                fullName,
                email,
                username,
                password,
                password,
                phone,
                invalidKtp,
                validNpwp
        );

        registerPage.clickRegister();

        Assert.assertTrue(
                registerPage.isErrorMessageDisplayed(),
                "Pesan error tidak muncul ketika KTP hanya memiliki 10 digit"
        );
    }
}