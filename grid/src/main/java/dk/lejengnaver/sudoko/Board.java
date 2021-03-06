package dk.lejengnaver.sudoko;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Board {

    private List<Cube> board = new ArrayList<>();
    private List<CubeList> verticalRows = new ArrayList<>();
    private List<CubeList> horizontalColumns = new ArrayList<>();
    private List<CubeList> orderedSquaresOfCubes = new ArrayList<>();

    private Map<Integer, Integer> setup = new HashMap<>();

    public Board() {
        load();
    }

    public Board(Map<Integer, Integer> setup) {
        this.setup = setup;
        load();
    }

    private void load() {
        for (int horizontal = 0; horizontal < 9; horizontal++) {
            Cube cube;
            CubeList horizontalColumn = new CubeList(CubeList.Layout.HORIZONTAL, 1 + horizontal);
            for (int position = 0; position < 9; position++) {
                cube = new Cube();
                int index = (9 * horizontal) + position;
                cube.setValue(setup.getOrDefault(1 + index, -1));
                board.add(index, cube);
                horizontalColumn.addCube(1 + position, cube);
            }
            horizontalColumns.add(horizontalColumn);
        }
        for (int vertical = 0; vertical < 9; vertical++) {
            CubeList verticalRow = new CubeList(CubeList.Layout.VERTICAL, 1 + vertical);
            for (int position = 0; position < 9; position++) {
                int index = (9 * position) + vertical;
                Cube cube = board.get(index);
                verticalRow.addCube(1 + position, cube);
            }
            verticalRows.add(verticalRow);
        }
        int[] indexes = new int[]{0, 3, 6, 27, 30, 33, 54, 57, 60};
        for (int index : indexes) {
            int position = 0;
            CubeList orderedSquare = new CubeList(CubeList.Layout.SQUARED, 1 + index);
            for (int horizontal = 0; horizontal < 3; horizontal++) {
                orderedSquare.addCube(1 + position++, board.get(index + horizontal));
            }
            index += 9;
            for (int horizontal = 0; horizontal < 3; horizontal++) {
                orderedSquare.addCube(1 + position++, board.get(index + horizontal));
            }
            index += 9;
            for (int horizontal = 0; horizontal < 3; horizontal++) {
                orderedSquare.addCube(1 + position++, board.get(index + horizontal));
            }
            orderedSquaresOfCubes.add(orderedSquare);
        }
    }

    /**
     * Missing squares
     * @return number of squares on the board without a value.
     */
    public int missing() {
        return board.stream().filter(t -> t.getValue() == -1).collect(Collectors.toList()).size();
    }

    public String dump() {
        StringBuilder builder = new StringBuilder();
        builder.append("dk.lejengnaver.sudoko.Board:\n");
        for (Cube cube : board) {
            builder.append(" ");
            builder.append(cube.getValue());
            builder.append(" ");
        }
        builder.append("\n");

        builder.append("Horizontal columns:\n");
        for (CubeList horizontalColumn : horizontalColumns) {
            builder.append(horizontalColumn.dump());
            builder.append("\n");
        }
        builder.append("\n");

        builder.append("Vertical rows:\n");
        for (CubeList verticalRow : verticalRows) {
            builder.append(verticalRow.dump());
            builder.append("\n");
        }
        builder.append("\n");

        builder.append("Ordered squares:\n");
        for (CubeList orderedSquare : orderedSquaresOfCubes) {
            builder.append(orderedSquare.dump());
            builder.append("\n");
        }
        builder.append("\n");

        return builder.toString();
    }

}
