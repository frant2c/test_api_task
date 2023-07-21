package helpers;

import data.PlayerRequestOne;
import data.PlayerRequest;
import data.PlayerResponse;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PlayerHelper extends HelperBase {
    public String getPlayerRequestBody(String email) {
        PlayerRequestOne requestBody = new PlayerRequestOne(email);
        return gson.toJson(requestBody);
    }

    public String getRequestBodyFromUser(PlayerRequest user) {
        return gson.toJson(user);
    }

    public List<PlayerResponse> getAllPlayers(Response response) {
        return Util.parsePlayersResponseFromJson(response.asString());
    }

    //ID string can be moved to Enum class in future
    public String getPlayerIdByEmail(String email) {
        Response response = given()
                .contentType(ContentType.JSON)
                .body(getPlayerRequestBody(email))
                .post(HelperBase.API_GET_USER_URL);
        return response.jsonPath().getString("id");
    }

    private List<PlayerRequest> getMissingPlayers(List<PlayerResponse> actualPlayerListResp, List<PlayerRequest> expectedPlayerListResp) {
        List<PlayerRequest> missingPlayers = new ArrayList<>();
        for (PlayerRequest expectedPlayer : expectedPlayerListResp) {
            boolean found = false;
            for (PlayerResponse actualPlayer : actualPlayerListResp) {
                if (expectedPlayer.getEmail().equals(actualPlayer.getEmail())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                missingPlayers.add(expectedPlayer);
            }
        }
        return missingPlayers;
    }
    //return expectedPlayerListResp.stream().filter(player -> !actualPlayerListResp.contains(player.getEmail())).toList();

    public boolean isPlayerDeleted(String id) {
        Response response = given()
                .contentType(ContentType.JSON)
                .get(HelperBase.API_GET_All_USERS_URL);

        List<PlayerResponse> actualPlayerListRequest = Util.parsePlayersResponseFromJson(response.asString());
        for (PlayerResponse playerRequest : actualPlayerListRequest) {
            if (playerRequest.getId().equals(id)) {
                return false;
            }
        }
        return true;
    }

    public void deleteAllPlayers(List<PlayerResponse> currentPLayers) {
        for (PlayerResponse playerResponse : currentPLayers) {
            given()
                    .contentType(ContentType.JSON)
                    .delete(HelperBase.API_DELETE_USER_URL + playerResponse.getId())
                    .then()
                    .statusCode(200);
        }
    }

    public boolean isAllPlayersDeleted() {
        Response response = given()
                .contentType(ContentType.JSON)
                .get(HelperBase.API_GET_All_USERS_URL);

        List<PlayerResponse> actualPlayerListRequest = Util.parsePlayersResponseFromJson(response.asString());
        return actualPlayerListRequest.isEmpty();
    }
    //Hard coded strings can be moved to Enum class in future
    public void assertResponseBodyContent(Response responseBody, PlayerRequest playerRequest) {
        SoftAssert softAssert = new SoftAssert();
        var respJson = responseBody.jsonPath();
        softAssert.assertEquals(respJson.get("currency_code"), playerRequest.getCurrency_code(),
                "Currency code is not as expected");
        softAssert.assertEquals(respJson.get("email"), playerRequest.getEmail(),
                "Email is not as expected");
        softAssert.assertEquals(respJson.get("username"), playerRequest.getUsername(),
                "First name is not as expected");
        softAssert.assertEquals(respJson.get("name"), playerRequest.getName(),
                "Last name is not as expected");
        softAssert.assertEquals(respJson.get("surname"), playerRequest.getSurname(),
                "Phone is not as expected");
        softAssert.assertAll();
    }

    public boolean isListSortedByName(List<PlayerResponse> playerResponses) {
        for (int i = 0; i < playerResponses.size() - 1; i++) {
            if (playerResponses.get(i).getName().compareTo(playerResponses.get(i + 1).getName()) > 0) {
                return false;
            }
        }
        return true;
    }

    public void lazyCreatePlayers() {
        Response response = given()
                .contentType(ContentType.JSON)
                .get(HelperBase.API_GET_All_USERS_URL);

        List<PlayerResponse> actualPlayerListRequest = Util.parsePlayersResponseFromJson(response.asString());
        List<PlayerRequest> expectedPlayerListRequest = Util.readPlayerDataFromFile(new File(HelperBase.PLAYER_DATA_FILE_VALID));
        List<PlayerRequest> missingPlayerRequests = getMissingPlayers(actualPlayerListRequest, expectedPlayerListRequest);

        if (!missingPlayerRequests.isEmpty()) {
            for (PlayerRequest playerRequest : missingPlayerRequests) {
                String requestBody = getRequestBodyFromUser(playerRequest);
                given()
                        .contentType(ContentType.JSON)
                        .body(requestBody)
                        .post(HelperBase.API_CREATE_PLAYER_URL);
            }
        }
    }
}
