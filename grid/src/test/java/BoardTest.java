import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static junit.framework.TestCase.assertTrue;

public class BoardTest {

    private final static Logger logger = Logger.getLogger(BoardTest.class.getName());

    @org.junit.Test
    public void boardHas_0_EmptySquares() {
        Board board = new Board(setupFullBoard());
        assertTrue("All 81 values have been set", 0==board.missing());
    }

    @org.junit.Test
    public void boardHas_81_EmptySquares() {
        Board board = new Board();
        assertTrue("None values have been set", 81==board.missing());
    }

    private Map<Integer, Integer> setupFullBoard() {
        Map<Integer, Integer> setupData = new HashMap<>();
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
        return setupData;
    }

}