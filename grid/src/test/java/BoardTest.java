import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static junit.framework.TestCase.assertTrue;

public class BoardTest {

    private final static Logger logger = Logger.getLogger(BoardTest.class.getName());

    private static Map<Integer, Integer> setupData = new HashMap<>();

    @org.junit.Before
    public void setup() {
        int horizontal = 0;
        for (int key = 0; key < 81; key++) {
            if (horizontal < 9) {
                horizontal++;
            } else {
                horizontal = 1;
            }
            logger.info("Key:" + (1 + key) + " value:" + (horizontal));
            setupData.put((1 + key), horizontal);
        }
    }

    @org.junit.Test
    public void missingIsZero() {
        Board board = new Board(setupData);
        assertTrue("All 81 values have been set", 0==board.missing());
    }

}