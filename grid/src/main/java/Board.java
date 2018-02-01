import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Cube> board = new ArrayList<Cube>(81);
    private List<Row> verticalRowList = new ArrayList<Row>(9);
    private List<Row> horizontalColumnList = new ArrayList<Row>(9);

    public Board() {
        load();
    }

    private void load() {
        for(int horizontal=0; horizontal<9; horizontal++) {
            Cube cube;
            List<Cube> horizontalColumn = new ArrayList<Cube>(9);
            for (int vertical = 0; vertical < 9; vertical++) {
                cube = new Cube();
                int index = ( 9 * horizontal ) + vertical;
                cube.setValue(1 + horizontal);
                board.add(index, cube);
                horizontalColumn.add(cube);
            }
            horizontalColumnList.add(new Row(horizontalColumn));
        }
        for(int horizontal=0; horizontal<9; horizontal++) {
            List<Cube> verticalRow = new ArrayList<Cube>(9);
            for (int vertical = 0; vertical < 9; vertical++) {
                int index = ( 9 * vertical ) + horizontal;
                Cube cube = board.get(index);
                verticalRow.add(cube);
            }
            verticalRowList.add(new Row(verticalRow));
        }
    }

    public String dump() {
        StringBuilder builder = new StringBuilder();
        for (Cube cube : board) {
            builder.append(" ");
            builder.append(cube.getValue());
            builder.append(" ");
        }

        for (Row horizontalColumn : horizontalColumnList) {
            builder.append(horizontalColumn.dump());
        }

        for (Row verticalRow : verticalRowList) {
            builder.append(verticalRow.dump());
        }

        builder.append("\n");
        return builder.toString();
    }

}
