package tests;

import data.PlayerRequest;
import dp.DpPlayers;
import io.restassured.response.Response;
import org.testng.annotations.Test;


import static helpers.HelperBase.API_CREATE_PLAYER_URL;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class TestCreatePlayerRequest extends AuthBase {

    @Test(priority = 2, dataProvider = "provideValidPlayers", dataProviderClass = DpPlayers.class)
    public void CreateValidPlayerTest(PlayerRequest playerRequest) {

        String requestBody = playerHelper.getRequestBodyFromUser(playerRequest);

        Response response = given()
                .body(requestBody)
                .post(API_CREATE_PLAYER_URL)
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .body(matchesJsonSchemaInClasspath("playerResponseSchema.json"))
                .extract().response();

        playerHelper.assertResponseBodyContent(response, playerRequest);
    }

    @Test(priority = 2, dataProvider = "provideInvalidPlayers", dataProviderClass = DpPlayers.class)
    public void CreateInvalidPlayerTest(PlayerRequest playerRequest) {

        String requestBody = playerHelper.getRequestBodyFromUser(playerRequest);

        given()
                .body(requestBody)
                .post(API_CREATE_PLAYER_URL)
                .then()
                .assertThat()
                .statusCode(400);
    }
}
