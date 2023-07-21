package tests;

import helpers.HelperBase;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;

import java.io.File;

import static helpers.HelperBase.API_URL;
import static io.restassured.RestAssured.given;

public class AuthBase extends TestBase {

    public static String accessToken;

    @BeforeTest
    public void auth() {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(new File(HelperBase.CREDENTIALS_FILE))
                .post(HelperBase.API_AUTH_URL);
        accessToken = response.jsonPath().get("accessToken");

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .build().header("Authorization", "Bearer " + accessToken).contentType(ContentType.JSON).baseUri(API_URL);
    }
}
