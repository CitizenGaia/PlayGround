import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class BoardTest {

    private final static Logger logger = Logger.getLogger(BoardTest.class.getName());

    private static Map<Integer, Integer> setupData = new HashMap<>();

    @org.junit.Before
    public void setup() {
        int horizontal = 0;
        for (int key = 0; key<81; key++){
            if (horizontal<9) {
                horizontal++;
            } else {
                horizontal = 1;
            }
            System.out.println("Key:" + (1 + key)+ " value:" + (horizontal));
            setupData.put((1 + key), horizontal);
        }
    }

    @org.junit.Test
    public void dump() {
        Board board = new Board(setupData);
        logger.info(board.dump());
    }


}