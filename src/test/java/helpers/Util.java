package helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import data.PlayerRequest;
import data.PlayerResponse;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Util {
    static Gson gson = new Gson();
    public static List<PlayerRequest> readPlayerDataFromFile(File userDataFile ) {
        try {
            FileReader fileReader = new FileReader(userDataFile);
            return parsePlayersRequestFromJsonFile(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static List<PlayerRequest> parsePlayersRequestFromJsonFile(Reader reader) {
        return gson.fromJson(reader, new TypeToken<List<PlayerRequest>>() {}.getType());
        //Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        //Map<PlayerRequest, Boolean> mapConfig = gson.fromJson(reader, new TypeToken<Map<PlayerRequest, Boolean>>() {}.getType());

    }

    public static List<PlayerResponse> parsePlayersResponseFromJson(String response) {
        return gson.fromJson(response, new TypeToken<List<PlayerResponse>>() {}.getType());
    }
}
