import java.util.Map;
import java.util.logging.Logger;

public class GameEngineer {

    private final static Logger logger = Logger.getLogger(GameEngineer.class.getName());

    private CVSUtils utils;

    public GameEngineer() {
        logger.info("Construct");
        utils = new CVSUtils();
    }

    public Map<Integer, Integer> fetchGame() {
        logger.info("Fetch Game");
        throw new RuntimeException("Not Implemented");
    }

}
