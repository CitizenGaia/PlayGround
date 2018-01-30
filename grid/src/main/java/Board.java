import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<Cube> board;
    List<Row> verticalRowList = new ArrayList<Row>(9);
    List<Row> horizontalColsList = new ArrayList<Row>(9);

    public Board() {
        board = new ArrayList<Cube>(81);

        for(int horizontal=0; horizontal<9; horizontal++) {
            Cube cube;
            List<Cube> verticalRow = new ArrayList<Cube>(9);
            for (int vertical = 0; vertical < 9; vertical++) {
                cube = new Cube();
                int index = ( 9 * horizontal ) + vertical;
                cube.setValue(1 + index);
                board.add(index, cube);
                verticalRow.add(cube);
            }
            verticalRowList.add(new Row(verticalRow));
        }
    }

    public void dump() {
        for (Cube cube : board) {
            System.out.print(" " + cube.getValue() + " ");
        }

        for (Row verticalRow : verticalRowList) {
            verticalRow.dump();
        }

    }

}
