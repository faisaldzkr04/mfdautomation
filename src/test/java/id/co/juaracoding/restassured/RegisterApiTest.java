package id.co.juaracoding.restassured;

import id.co.juaracoding.restassured.util.RsaHelper;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class RegisterApiTest extends BaseRestAssuredTest {

    private Map<String, Object> buildValidRegistrationBody() {
        String uniqueValue = String.valueOf(System.currentTimeMillis());

        Map<String, Object> body = new HashMap<>();
        body.put("username", "apiuser" + uniqueValue);
        body.put("full_name", "REST Assured User");
        body.put("password", RsaHelper.encrypt("Test@12345"));
        body.put("email", RsaHelper.encrypt("apiuser" + uniqueValue + "@example.com"));
        body.put("birth_date", RsaHelper.encrypt("2003-08-04"));
        body.put("phone_number", RsaHelper.encrypt("081234567890"));
        body.put("id_card_number", RsaHelper.encrypt("320101" + uniqueValue.substring(uniqueValue.length() - 10)));
        body.put("tax_id_number", RsaHelper.encrypt("123456789012345"));
        body.put("address", "Jl. Test API No. 1");
        body.put("gender", "L");
        body.put("last_education", "S1");
        body.put("blood_type", "O");
        body.put("postal_code", "69162");
        body.put("captcha_answer", captchaAnswer());
        body.put("captcha_hash", captchaHash());
        return body;
    }

    @Test
    public void register_berhasil_membuat_akun_baru() {
        Response response = given()
                .contentType(ContentType.JSON)
                .header("X-API-KEY", apiKey())
                .body(buildValidRegistrationBody())
                .when()
                .post(API_BASE_PATH + "/register");

        Assert.assertEquals(response.statusCode(), 200,
                "Registrasi valid harus menghasilkan HTTP 200");
    }

    @Test
    public void register_gagal_id_card_tidak_valid_mengembalikan_error_code() {
        Map<String, Object> body = buildValidRegistrationBody();
        body.put("id_card_number", RsaHelper.encrypt("12345"));

        Response response = given()
                .contentType(ContentType.JSON)
                .header("X-API-KEY", apiKey())
                .body(body)
                .when()
                .post(API_BASE_PATH + "/register");

        Assert.assertNotNull(response.jsonPath().get("error_code"),
                "Response registrasi gagal harus memiliki error_code");
    }
}
