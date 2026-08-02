package id.co.juaracoding.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Page Object untuk halaman Login.
 */
public class LoginPage {

    private final WebDriver driver;

    @FindBy(id = "username")
    private WebElement txtUsername;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(id = "btn-login")
    private WebElement btnLogin;

    @FindBy(css = "[data-testid='toast-icon-error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Mengisi username.
     *
     * @param username username yang digunakan
     */
    public void enterUsername(String username) {
        txtUsername.clear();
        txtUsername.sendKeys(username);
    }

    /**
     * Mengisi password.
     *
     * @param password password yang digunakan
     */
    public void enterPassword(String password) {
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    /**
     * Melakukan login.
     */
    public void clickLogin() {
        btnLogin.click();
    }

    /**
     * Melakukan login menggunakan username dan password.
     *
     * @param username username
     * @param password password
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    /**
     * Mengecek apakah pesan error login ditampilkan.
     *
     * @return true jika error message tampil
     */
    public boolean isErrorMessageDisplayed() {
        try {
            return errorMessage.isDisplayed();
        } catch (Exception exception) {
            return false;
        }
    }

    /**
     * Mengambil text error message.
     *
     * @return text error
     */
    public String getErrorMessage() {
        return errorMessage.getText();
    }

    /**
     * Mengambil URL halaman saat ini.
     *
     * @return current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}