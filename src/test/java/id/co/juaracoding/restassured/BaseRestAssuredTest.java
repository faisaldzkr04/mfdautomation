package id.co.juaracoding.restassured;

import id.co.juaracoding.util.TestConfig;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

/** Base configuration untuk seluruh test REST API. */
public abstract class BaseRestAssuredTest {

    protected static final String API_BASE_PATH = "/api/v1";

    @BeforeClass(alwaysRun = true)
    public void setUpRestAssured() {
        RestAssured.baseURI = TestConfig.BASE_API_URL;
    }

    protected String apiKey() {
        return TestConfig.X_API_KEY;
    }
}
