package dp;

import data.PlayerRequest;
import helpers.Util;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.List;

import static helpers.HelperBase.PLAYER_DATA_FILE_INVALID;
import static helpers.HelperBase.PLAYER_DATA_FILE_VALID;


public class DpPlayers {
    
    @DataProvider(name = "provideValidPlayers")
    public static Object[][] provideValidPlayers() {
        File userDataFile = new File(PLAYER_DATA_FILE_VALID);
        List<PlayerRequest> userList = Util.readPlayerDataFromFile(userDataFile);

        // Convert List<User> to Object[][] for DataProvider
        Object[][] userDataArray = new Object[userList.size()][1];
        for (int i = 0; i < userList.size(); i++) {
            userDataArray[i][0] = userList.get(i);
        }
        return userDataArray;
    }

    @DataProvider(name = "provideInvalidPlayers")
    public static Object[][] provideInvalidPlayers() {
        File userDataFile = new File(PLAYER_DATA_FILE_INVALID);
        List<PlayerRequest> userList = Util.readPlayerDataFromFile(userDataFile);

        // Convert List<User> to Object[][] for DataProvider
        Object[][] userDataArray = new Object[userList.size()][1];
        for (int i = 0; i < userList.size(); i++) {
            userDataArray[i][0] = userList.get(i);
        }
        return userDataArray;
    }

}
