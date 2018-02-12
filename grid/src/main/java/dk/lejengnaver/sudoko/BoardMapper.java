package dk.lejengnaver.sudoko;

import dk.lejengnaver.sudoko.validation.ValueValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class BoardMapper {

    public enum MapperFormat {
        SQUAREBASED,
        BOARDBASED
    }

    private final static Logger logger = Logger.getLogger(BoardMapper.class.getName());

    private ValueValidator validator = new ValueValidator();

    /**
     * Mapper that requires List of 'values to the board' that accepts two formats:
     * 1) Square based format (three elements describing each 'value to the board'
     * [0] The square number of the board (nine squares) starting from top leftmost
     * [11 The cube number of the square (nine cubes) starting from top leftmost
     * [2] The value of [1-9] for the cube, -1 is default implying left empty.
     * 2) board based format (two elements describing each 'value of the board':
     * [0] The cube number of the board (eighty one cubes) starting from top leftmost
     * [1] The value of [1-9] for the cube, -1 is default implying left empty.
     *
     * @param listOfValuesInLine formatted as 'values to the board'
     * @param format             (SQUAREBASED or BOARDBASED)
     * @return Map<Integer       ,               Integer> that return a 'board based format' that contains
     * Key: The cube number of the board [1..81]
     * Value: The value of [1-9] for the cube, -1 is default implying left empty
     * No matching Key implies that the cube has no value e.g. -1 when board is loaded
     */
    public Map<Integer, Integer> map(List<List<String>> listOfValuesInLine, MapperFormat format) {
        Map<Integer, Integer> map = new HashMap<>();

        for (List<String> lineValues : listOfValuesInLine) {
            if (format.equals(MapperFormat.BOARDBASED) && lineValues.size() == 2) {
                Integer boardIndex = getInteger(lineValues.get(0));
                Integer cubeValue = getInteger(lineValues.get(1));
                map.put(boardIndex, cubeValue);
            } else if (format.equals(MapperFormat.SQUAREBASED) && lineValues.size() == 3) {
                Integer squareIndex = getInteger(lineValues.get(0));
                Integer cubeIndex = getInteger(lineValues.get(1));
                Integer cubeValue = getInteger(lineValues.get(2));

                Integer boardIndex = toBoardIndex(squareIndex, cubeIndex);

                map.put(boardIndex, cubeValue);
            } else {
                String msg = String.format("Number of values in data [%d] doesn't match Format [%s]", lineValues.size(), format.toString());
                logger.warning(msg);
                throw new RuntimeException(msg);
            }
        }
        return map;
    }

    private Integer toBoardIndex(Integer square, Integer cube) {
        int leftSideSiblings[] = {0, 0, 1, 2, 0, 1, 2, 0, 1, 2};
        // Calculate number of horizontal line of squares of three above current square,
        // which represent three horizontal lines of nine cubes.
        int _squaresAboveCurrentSquare = Math.floorDiv((square - 1), 3);
        //
        int cubeLinesAboveCurrentSquare = 3 * _squaresAboveCurrentSquare;
        // Calculate the number of horizontal lines of cubes above the current cube but within the current square.
        int cubeLinesAboveCurrentCube = Math.floorDiv((cube - 1), 3);
        // Calculate squares on the left side of current square as each of those represent
        // three horizontal cubes.
        int _squaresOnTheLeftSideOfCurrentSquare = leftSideSiblings[square];
        int cubesOnTheLeftSideOfCurrentSquare = 3 * _squaresOnTheLeftSideOfCurrentSquare;
        // Calculate cubes on the left side of current cube within same square.
        int cubesOnTheLeftSideOfCurrentCube = leftSideSiblings[cube];

        int cubesAbove = 9 * (cubeLinesAboveCurrentSquare + cubeLinesAboveCurrentCube);
        int cubesOnLeftSide = cubesOnTheLeftSideOfCurrentSquare + cubesOnTheLeftSideOfCurrentCube;
        // Sum cubes above and of the left side PLUS the cubes itself an index are given
        return 1 + cubesAbove + cubesOnLeftSide;
    }

    private Integer getInteger(String string) {
        return validator.getInteger(string);
    }

}
