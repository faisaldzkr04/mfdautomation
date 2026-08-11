package id.co.juaracoding.restassured;

import id.co.juaracoding.restassured.util.RsaHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class LoginApiTest extends BaseRestAssuredTest {

    @Test
    public void login_berhasil_mengembalikan_jwt() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("username", System.getProperty("valid.username", "testuser"));
        requestBody.put("password", RsaHelper.encrypt(System.getProperty("valid.password", "Test@12345")));
        requestBody.put("captcha_answer", captchaAnswer());
        requestBody.put("captcha_hash", captchaHash());

        Response response = given()
                .contentType(ContentType.JSON)
                .header("X-API-KEY", apiKey())
                .body(requestBody)
                .when()
                .post(API_BASE_PATH + "/login");

        response.then().statusCode(200);
        Assert.assertNotNull(response.jsonPath().getString("token"),
                "Response login harus berisi JWT pada field token");
    }

    @Test
    public void login_gagal_password_salah_mengembalikan_error_code() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("username", System.getProperty("valid.username", "testuser"));
        requestBody.put("password", RsaHelper.encrypt("PasswordSalah@123"));
        requestBody.put("captcha_answer", captchaAnswer());
        requestBody.put("captcha_hash", captchaHash());

        Response response = given()
                .contentType(ContentType.JSON)
                .header("X-API-KEY", apiKey())
                .body(requestBody)
                .when()
                .post(API_BASE_PATH + "/login");

        Assert.assertNotNull(response.jsonPath().get("error_code"),
                "Response gagal login harus memiliki error_code");
    }
}
