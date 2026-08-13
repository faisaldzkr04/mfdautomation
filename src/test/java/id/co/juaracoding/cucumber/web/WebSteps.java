package id.co.juaracoding.cucumber.web;

import id.co.juaracoding.selenium.pages.ForgotPasswordPage;
import id.co.juaracoding.selenium.pages.LoginPage;
import id.co.juaracoding.selenium.pages.RegisterPage;
import id.co.juaracoding.util.TestConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class WebSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @After
    public void tearDown() {
        if (driver != null) { driver.quit(); driver = null; }
    }

    @Given("user berada di halaman login")
    public void userBeradaDiHalamanLogin() {
        driver.get(TestConfig.BASE_URL + "/login");
        loginPage = new LoginPage(driver);
    }

    @When("user login dengan kredensial yang valid")
    public void userLoginValid() { loginPage.login(TestConfig.VALID_USERNAME, TestConfig.VALID_PASSWORD); }

    @When("user login dengan password yang salah")
    public void userLoginInvalid() { loginPage.login(TestConfig.VALID_USERNAME, "InvalidPassword@123"); }

    @Then("user berhasil masuk ke dashboard")
    public void dashboard() {
        Assert.assertEquals(loginPage.getCurrentUrl(), TestConfig.BASE_URL + "/dashboard");
    }

    @Then("muncul pesan kesalahan login")
    public void loginError() { Assert.assertTrue(loginPage.isErrorMessageDisplayed()); }

    @Given("user berada di halaman registrasi")
    public void userBeradaDiHalamanRegistrasi() {
        driver.get(TestConfig.BASE_URL + "/register");
        registerPage = new RegisterPage(driver);
    }

    @When("user melakukan registrasi dengan data yang valid")
    public void registrasiValid() {
        String u = String.valueOf(System.currentTimeMillis());
        registerPage.fillRegistrationForm("Mohammad Faisal Dzikri", "faisal" + u + "@example.com", "faisal" + u,
                "Test@12345", "Test@12345", "081234567890", "3201234567890123", "1234567890123456");
        registerPage.clickRegister();
    }

    @When("user melakukan registrasi dengan KTP yang tidak valid")
    public void registrasiInvalid() {
        String u = String.valueOf(System.currentTimeMillis());
        registerPage.fillRegistrationForm("Mohammad Faisal Dzikri", "invalid" + u + "@example.com", "invalid" + u,
                "Test@12345", "Test@12345", "081234567890", "1234567890", "1234567890123456");
        registerPage.clickRegister();
    }

    @Then("registrasi berhasil dan pesan sukses ditampilkan")
    public void registrasiSuccess() { Assert.assertTrue(registerPage.isSuccessMessageDisplayed()); }

    @Then("registrasi ditolak dan pesan kesalahan ditampilkan")
    public void registrasiError() { Assert.assertTrue(registerPage.isErrorMessageDisplayed()); }

    @Given("user berada di halaman lupa password")
    public void userBeradaDiHalamanLupaPassword() {
        driver.get(TestConfig.BASE_URL + "/forgot-password");
        forgotPasswordPage = new ForgotPasswordPage(driver);
    }

    @When("user meminta reset password dengan email terdaftar")
    public void forgotValid() { forgotPasswordPage.requestPasswordReset("registered@example.com"); }

    @When("user meminta reset password dengan email tidak terdaftar")
    public void forgotInvalid() { forgotPasswordPage.requestPasswordReset("email.tidak.terdaftar@example.com"); }

    @Then("pesan sukses reset password ditampilkan")
    public void forgotSuccess() { Assert.assertTrue(forgotPasswordPage.isSuccessMessageDisplayed()); }

    @Then("pesan kesalahan reset password ditampilkan")
    public void forgotError() { Assert.assertTrue(forgotPasswordPage.isErrorMessageDisplayed()); }
}
