package dk.lejengnaver.sudoko;

import dk.lejengnaver.util.CVSUtils;
import dk.lejengnaver.util.GameFormat;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;

public class BoardMapperTest {

    private Map<Integer, Integer> game;
    private List<List<String>> rawSquaredFormatGameData;
    private BoardMapper boardMapper;

    @Before
    public void initialize() {
        CVSUtils utils = new CVSUtils();
        TestCase.assertTrue("This raw string of the game should load fine", utils.load(Games.unclassified, GameFormat.REGEXP_SQUAREBASED));
        rawSquaredFormatGameData = utils.getExtractedData();
        game = Games.unclassifiedData();
        boardMapper = new BoardMapper();
    }

    @Test
    public void map() {
        Map<Integer, Integer> mappedGame = boardMapper.map(rawSquaredFormatGameData, BoardMapper.MapperFormat.SQUAREBASED);
        assertTrue("This game should contain data", !game.isEmpty());
        for (Integer mappedKey : mappedGame.keySet()) {
            assertTrue("The mapped key from the mapper should be as the source example", game.containsKey(mappedKey));
            assertTrue("The mapped key and value should be as the source example key and value", mappedGame.get(mappedKey).equals(game.get(mappedKey)));
        }
    }
}