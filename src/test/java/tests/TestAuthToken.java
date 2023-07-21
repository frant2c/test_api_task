package tests;

import helpers.HelperBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestAuthToken extends TestBase {

    @Test(priority = 1)
    public void testLogin() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(new File(HelperBase.CREDENTIALS_FILE))
                .post(HelperBase.API_AUTH_URL);
        assertEquals(response.getStatusCode(), 200, "Unexpected status code");
        // Verify the access token length is more than 1 (can be adjusted to required length)
        assertTrue(response.jsonPath().get("accessToken").toString().length() > 1, "Unexpected token length");
    }
}
