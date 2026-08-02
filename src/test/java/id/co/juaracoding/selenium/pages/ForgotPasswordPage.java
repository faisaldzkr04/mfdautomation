package id.co.juaracoding.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object untuk halaman Lupa Password.
 */
public class ForgotPasswordPage {

    private final WebDriver driver;

    @FindBy(id = "email")
    private WebElement txtEmail;

    @FindBy(id = "btn-reset-password")
    private WebElement btnResetPassword;

    @FindBy(css = "[data-testid='reset-success']")
    private WebElement successMessage;

    @FindBy(css = "[data-testid='reset-error']")
    private WebElement errorMessage;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Mengisi email.
     *
     * @param email email pengguna
     */
    public void enterEmail(String email) {
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    /**
     * Klik tombol reset password.
     */
    public void clickResetPassword() {
        btnResetPassword.click();
    }

    /**
     * Mengirim request reset password.
     *
     * @param email email pengguna
     */
    public void requestPasswordReset(String email) {
        enterEmail(email);
        clickResetPassword();
    }

    /**
     * Mengecek pesan sukses.
     *
     * @return true jika pesan sukses tampil
     */
    public boolean isSuccessMessageDisplayed() {
        try {
            return successMessage.isDisplayed();
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * Mengecek pesan error.
     *
     * @return true jika pesan error tampil
     */
    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * Mengambil pesan sukses.
     *
     * @return text pesan sukses
     */
    public String getSuccessMessage() {
        return successMessage.getText();
    }

    /**
     * Mengambil pesan error.
     *
     * @return text pesan error
     */
    public String getErrorMessage() {
        return errorMessage.getText();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}