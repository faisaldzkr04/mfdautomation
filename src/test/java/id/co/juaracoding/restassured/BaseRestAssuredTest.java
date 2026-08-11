package id.co.juaracoding.restassured;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

/** Base configuration untuk seluruh test REST API. */
public abstract class BaseRestAssuredTest {

    protected static final String API_BASE_PATH = "/api/v1";

    @BeforeClass(alwaysRun = true)
    public void setUpRestAssured() {
        RestAssured.baseURI = System.getProperty("base.api.url", "http://localhost:8080");
    }

    protected String apiKey() {
        return System.getProperty("x.api.key", "");
    }

    protected String captchaAnswer() {
        return System.getProperty("captcha.answer", "");
    }

    protected String captchaHash() {
        return System.getProperty("captcha.hash", "");
    }
}
