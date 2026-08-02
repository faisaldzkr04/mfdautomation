package id.co.juaracoding.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object untuk halaman Registrasi.
 */
public class RegisterPage {

    private final WebDriver driver;

    @FindBy(id = "fullname")
    private WebElement txtFullName;

    @FindBy(id = "email")
    private WebElement txtEmail;

    @FindBy(id = "username")
    private WebElement txtUsername;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(id = "confirm-password")
    private WebElement txtConfirmPassword;

    @FindBy(id = "phone")
    private WebElement txtPhone;

    @FindBy(id = "ktp")
    private WebElement txtKtp;

    @FindBy(id = "npwp")
    private WebElement txtNpwp;

    @FindBy(id = "btn-register")
    private WebElement btnRegister;

    @FindBy(css = "[data-testid='registration-success']")
    private WebElement successMessage;

    @FindBy(css = "[data-testid='registration-error']")
    private WebElement errorMessage;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterFullName(String fullName) {
        txtFullName.clear();
        txtFullName.sendKeys(fullName);
    }

    public void enterEmail(String email) {
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }

    public void enterUsername(String username) {
        txtUsername.clear();
        txtUsername.sendKeys(username);
    }

    public void enterPassword(String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        txtConfirmPassword.clear();
        txtConfirmPassword.sendKeys(confirmPassword);
    }

    public void enterPhone(String phone) {
        txtPhone.clear();
        txtPhone.sendKeys(phone);
    }

    /**
     * Mengisi nomor KTP.
     *
     * @param ktp nomor KTP
     */
    public void enterKtp(String ktp) {
        txtKtp.clear();
        txtKtp.sendKeys(ktp);
    }

    /**
     * Mengisi nomor NPWP.
     *
     * @param npwp nomor NPWP
     */
    public void enterNpwp(String npwp) {
        txtNpwp.clear();
        txtNpwp.sendKeys(npwp);
    }

    /**
     * Klik tombol registrasi.
     */
    public void clickRegister() {
        btnRegister.click();
    }

    /**
     * Mengisi seluruh form registrasi.
     */
    public void fillRegistrationForm(
            String fullName,
            String email,
            String username,
            String password,
            String confirmPassword,
            String phone,
            String ktp,
            String npwp) {

        enterFullName(fullName);
        enterEmail(email);
        enterUsername(username);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        enterPhone(phone);
        enterKtp(ktp);
        enterNpwp(npwp);
    }

    /**
     * Mengecek pesan sukses registrasi.
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
     * Mengecek pesan error registrasi.
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