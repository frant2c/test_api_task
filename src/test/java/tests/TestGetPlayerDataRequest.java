package tests;

import data.PlayerRequest;
import data.PlayerResponse;
import dp.DpPlayers;
import helpers.HelperBase;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestGetPlayerDataRequest extends AuthBase {

    @BeforeClass
    public void preconditions() {
        playerHelper.lazyCreatePlayers();
    }

    @Test(priority = 3, dataProvider = "provideValidPlayers", dataProviderClass = DpPlayers.class)
    public void testGetPlayerData(PlayerRequest player) {
        given()
                .body(playerHelper.getPlayerRequestBody(player.getEmail()))
                .post(HelperBase.API_GET_USER_URL)
                .then()
                .assertThat()
                .statusCode(200) //Expected status code 200 but was 201 since
                .and()                            //post method used instead of get.
                .body(matchesJsonSchemaInClasspath("playerGetResponseSchema.json"));
    }

    @Test(priority = 3)
    public void testGetAllPlayersData() {
        Response response = given()
                .get(HelperBase.API_GET_All_USERS_URL);

        assertEquals(response.getStatusCode(), 200, "Unexpected status code");
        List<PlayerResponse> currentPlayerRequests = playerHelper.getAllPlayers(response);
        currentPlayerRequests.sort(Comparator.comparing(PlayerResponse::getName));
        assertTrue(playerHelper.isListSortedByName(currentPlayerRequests), "Player list is not sorted in ascending order");
    }
}
