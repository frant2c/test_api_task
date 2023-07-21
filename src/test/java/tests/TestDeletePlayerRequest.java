package tests;

import data.PlayerRequest;
import data.PlayerResponse;
import dp.DpPlayers;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import static helpers.HelperBase.API_DELETE_USER_URL;
import static helpers.HelperBase.API_GET_All_USERS_URL;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class TestDeletePlayerRequest extends AuthBase {

    @BeforeClass
    public void preconditions() {
        playerHelper.lazyCreatePlayers();
    }

    //This implementation of delete method removes only players were created within playerDataValid.json file.
    @Test(priority = 4, dataProvider = "provideValidPlayers", dataProviderClass = DpPlayers.class)
    public void DeleteUserById(PlayerRequest playerRequest) {
        playerRequest.setId(playerHelper.getPlayerIdByEmail(playerRequest.getEmail()));

        given()
                .delete(API_DELETE_USER_URL + playerRequest.getId())
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Verify the response code
        assertTrue(playerHelper.isPlayerDeleted(playerRequest.getId()), "Player is not deleted");
    }

    @Test(priority = 6)
    public void DeleteAllUsers() {
        Response response = given()
                .get(API_GET_All_USERS_URL);

        List<PlayerResponse> currentPLayers = playerHelper.getAllPlayers(response);
        playerHelper.deleteAllPlayers(currentPLayers);

        // Verify the response code
        assertTrue(playerHelper.isAllPlayersDeleted(), "Players are not deleted");
    }
}
