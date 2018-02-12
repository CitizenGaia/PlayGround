package dk.lejengnaver.util;

import dk.lejengnaver.util.GSONUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class GSONUtilsTest {

    private GSONUtils utils;
    private final static Map<Integer, Integer> game = Collections.unmodifiableMap(new HashMap<Integer, Integer>() {
        {
            put(1, 1); put(2, 2); put(3, 3);
            put(10, 2); put(11, 3); put(12, 4);
            put(20, 3); put(21, 4); put(22, 5);
        }
    });

    @Before
    public void setup() {
        utils = new GSONUtils();
    }

    @Test
    public void gameToJsonAndBack() {
        String jsonFormattedGame = utils.gameToJson(game);
        Map<Integer, Integer> gameFromJson = utils.jsonToGame(jsonFormattedGame);
        assertTrue("game should be the same after transformation to JSON and back again", game.equals(gameFromJson));
    }

}