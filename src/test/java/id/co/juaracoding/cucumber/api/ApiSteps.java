package id.co.juaracoding.cucumber.api;

import id.co.juaracoding.restassured.ForgotPasswordApiTest;
import id.co.juaracoding.restassured.LoginApiTest;
import id.co.juaracoding.restassured.RegisterApiTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class ApiSteps {
    private boolean completed;
    private Throwable failure;

    @Given("request API login siap digunakan")
    public void loginReady() { reset(); }

    @When("client mengirim request login dengan kredensial valid")
    public void loginValid() { execute(() -> { LoginApiTest t = new LoginApiTest(); t.setUpRestAssured(); t.login_berhasil_mengembalikan_jwt(); }); }

    @When("client mengirim request login dengan password salah")
    public void loginInvalid() { execute(() -> { LoginApiTest t = new LoginApiTest(); t.setUpRestAssured(); t.login_gagal_password_salah_mengembalikan_error_code(); }); }

    @Then("response login berhasil")
    public void loginSuccess() { verify(); }

    @Then("response login memiliki error code")
    public void loginError() { verify(); }

    @Given("request API registrasi siap digunakan")
    public void registerReady() { reset(); }

    @When("client mengirim request registrasi dengan data valid")
    public void registerValid() { execute(() -> { RegisterApiTest t = new RegisterApiTest(); t.setUpRestAssured(); t.register_berhasil_membuat_akun_baru(); }); }

    @When("client mengirim request registrasi dengan KTP tidak valid")
    public void registerInvalid() { execute(() -> { RegisterApiTest t = new RegisterApiTest(); t.setUpRestAssured(); t.register_gagal_id_card_tidak_valid_mengembalikan_error_code(); }); }

    @Then("response registrasi berhasil")
    public void registerSuccess() { verify(); }

    @Then("response registrasi memiliki error code")
    public void registerError() { verify(); }

    @Given("request API lupa password siap digunakan")
    public void forgotReady() { reset(); }

    @When("client mengirim request lupa password dengan email terdaftar")
    public void forgotValid() { execute(() -> { ForgotPasswordApiTest t = new ForgotPasswordApiTest(); t.setUpRestAssured(); t.forgot_password_berhasil_mengirim_reset_email(); }); }

    @When("client mengirim request lupa password dengan email tidak terdaftar")
    public void forgotInvalid() { execute(() -> { ForgotPasswordApiTest t = new ForgotPasswordApiTest(); t.setUpRestAssured(); t.forgot_password_gagal_email_tidak_terdaftar_mengembalikan_error_code(); }); }

    @Then("response lupa password berhasil")
    public void forgotSuccess() { verify(); }

    @Then("response lupa password memiliki error code")
    public void forgotError() { verify(); }

    private void reset() { completed = false; failure = null; }

    private void execute(CheckedAction action) {
        try { action.run(); completed = true; }
        catch (Throwable throwable) { failure = throwable; }
    }

    private void verify() {
        if (failure != null) Assert.fail("API scenario gagal: " + failure.getMessage(), failure);
        Assert.assertTrue(completed, "Request API belum berhasil dijalankan");
    }

    @FunctionalInterface
    private interface CheckedAction { void run() throws Exception; }
}
