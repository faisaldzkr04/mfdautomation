package id.co.juaracoding.restassured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ForgotPasswordApiTest extends BaseRestAssuredTest {

    @Test
    public void forgot_password_berhasil_mengirim_reset_email() {
        Map<String, Object> body = new HashMap<>();
        body.put("email", System.getProperty("valid.email", "testuser@example.com"));

        Response response = given()
                .contentType(ContentType.JSON)
                .header("X-API-KEY", apiKey())
                .body(body)
                .when()
                .post(API_BASE_PATH + "/forgot-password");

        Assert.assertEquals(response.statusCode(), 200,
                "Forgot password untuk email terdaftar harus menghasilkan HTTP 200");
    }

    @Test
    public void forgot_password_gagal_email_tidak_terdaftar_mengembalikan_error_code() {
        Map<String, Object> body = new HashMap<>();
        body.put("email", "tidak-terdaftar-" + System.currentTimeMillis() + "@example.com");

        Response response = given()
                .contentType(ContentType.JSON)
                .header("X-API-KEY", apiKey())
                .body(body)
                .when()
                .post(API_BASE_PATH + "/forgot-password");

        Assert.assertNotNull(response.jsonPath().get("error_code"),
                "Response email tidak terdaftar harus memiliki error_code");
    }
}
