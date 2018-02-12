package dk.lejengnaver.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class GSONUtils {

    private Gson gson;

    public GSONUtils() {
        gson = new Gson();
    }

    public String gameToJson(Map<Integer, Integer> game) {
        return gson.toJson(game);
    }

    public Map<Integer, Integer> jsonToGame(String sourceGame) {
        return gson.fromJson(sourceGame, new TypeToken<Map<Integer, Integer>>(){}.getType());
    }
}
