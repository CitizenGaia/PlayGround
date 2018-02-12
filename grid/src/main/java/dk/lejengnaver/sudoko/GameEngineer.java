package dk.lejengnaver.sudoko;

import dk.lejengnaver.sudoko.validation.ValueValidator;
import dk.lejengnaver.sudoko.validation.ValueValidator;
import dk.lejengnaver.util.CVSUtils;
import dk.lejengnaver.util.GameFormat;

import java.util.*;
import java.util.logging.Logger;

public class GameEngineer {

    private final static Logger logger = Logger.getLogger(GameEngineer.class.getName());

    private ValueValidator validator;
    private BoardMapper mapper;

    public GameEngineer() {
        logger.info("Construct");
        validator = new ValueValidator();
        mapper = new BoardMapper();
    }

    public Map<Integer, Integer> fetchGame() {
        logger.info("Fetch Game");
        try {
            return mapper.map(randomLayout(), BoardMapper.MapperFormat.SQUAREBASED );
        } catch (IllegalArgumentException e) {
            logger.info("Fetching Game failed due to an exception");
        }
        return null;
    }

    private List<List<String>> randomLayout() throws IllegalArgumentException {
        List<List<String>> rawGame = new ArrayList<>();
        Integer[] shuffledValues = shuffledValues();

        CVSUtils utils = new CVSUtils();
        if (!utils.load(Games.unclassified, GameFormat.REGEXP_SQUAREBASED)) {
            logger.info("Load Raw Game failed");
        }
        List<List<String>> gameOfSquareFormattedLines = utils.getExtractedData();
        for (List<String> lineValues : gameOfSquareFormattedLines) {
            Integer squareIndex = getInteger(lineValues.get(0));
            Integer cubeIndex = getInteger(lineValues.get(1));
            Integer cubeValue = getInteger(lineValues.get(2));
            rawGame.add(Arrays.asList(
                    String.valueOf(squareIndex),
                    String.valueOf(cubeIndex),
                    // Use shuffled value to minimize visually alike games (cheat)
                    String.valueOf((cubeValue==-1) ? cubeValue : shuffledValues[cubeValue-1]))
            );
        }
        return rawGame;
    }

    private Integer getInteger(String string) throws IllegalArgumentException {
        Integer validvalue = validator.getInteger(string);
        if (validvalue==null) {
            String msg = String.format("Parsing value[%s] in gage failed", string);
            logger.warning(msg);
            throw new IllegalArgumentException(msg);
        } else {
            return validvalue;
        }
    }

    private Integer[] shuffledValues() {
        List<Integer> ordinaireValues = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            ordinaireValues.add(i);
        }
        Collections.shuffle(ordinaireValues);
        return ordinaireValues.toArray(new Integer[ordinaireValues.size()]);
    }
}
