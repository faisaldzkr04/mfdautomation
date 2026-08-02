package id.co.juaracoding.selenium;

import id.co.juaracoding.selenium.pages.ForgotPasswordPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Test automation untuk fitur Lupa Password.
 */
public class LupaPasswordTest extends BaseSeleniumTest {

    private ForgotPasswordPage forgotPasswordPage;

    /**
     * Inisialisasi ForgotPasswordPage sebelum setiap test.
     */
    @BeforeMethod(alwaysRun = true)
    public void setUpForgotPasswordPage() {
        forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    /**
     * Positive test:
     * Email terdaftar harus mendapatkan pesan bahwa link reset terkirim.
     */
    @Test
    public void forgot_password_success_with_registered_email() {

        String registeredEmail = "registered@example.com";

        forgotPasswordPage.requestPasswordReset(registeredEmail);

        Assert.assertTrue(
                forgotPasswordPage.isSuccessMessageDisplayed(),
                "Pesan bahwa link reset password terkirim tidak muncul"
        );
    }

    /**
     * Negative test:
     * Email yang tidak terdaftar harus menghasilkan pesan error.
     */
    @Test
    public void forgot_password_failed_with_unregistered_email() {

        String unregisteredEmail =
                "email.tidak.terdaftar@example.com";

        forgotPasswordPage.requestPasswordReset(unregisteredEmail);

        Assert.assertTrue(
                forgotPasswordPage.isErrorMessageDisplayed(),
                "Pesan email tidak ditemukan tidak muncul"
        );
    }
}