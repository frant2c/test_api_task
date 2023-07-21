package helpers;

import com.google.gson.Gson;

public class HelperBase {
    Gson gson = new Gson();
    public static final String RESOURCES_PATH = "src/test/resources/";
    public static final String CREDENTIALS_FILE = RESOURCES_PATH + "credentials.json";
    public static final String PLAYER_DATA_FILE_VALID = RESOURCES_PATH + "playerDataValid.json";

    public static final String PLAYER_DATA_FILE_INVALID = RESOURCES_PATH + "playerDataInvalid.json";

    public static final String PLAYER_SCHEMA_FILE = RESOURCES_PATH + "playerResponseSchema.json";
    public static final String API_URL = "https://testslotegrator.com/api/";
    public static final String API_AUTH_URL = API_URL + "tester/login";
    public static final String API_CREATE_PLAYER_URL = "automationTask/create";
    public static final String API_GET_USER_URL = API_URL + "automationTask/getOne";
    public static final String API_GET_All_USERS_URL = "automationTask/getAll";
    public static final String API_DELETE_USER_URL = "automationTask/deleteOne/";

}
