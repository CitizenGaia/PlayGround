import com.sun.javafx.binding.StringFormatter;

import java.util.ArrayList;
import java.util.List;

public class Row {

    List<Cube> row;

    public Row() {
        row = new ArrayList<Cube>(9);
    }

    public void addCube(int index, Cube cube) {
        row.add(index, cube);
    }

    public Row(List<Cube> cubeList) {
        row = cubeList;
    }

    public List<Integer> getCubeValueList() {
        List<Integer> values = new ArrayList<Integer>(row.size());
        for (Cube cube : row) {
            values.add(cube.getValue());
        }
        return values;
    }

    public void dump() {
        System.out.println("");
        for (Cube cube : row) {
            System.out.print(String.format(" (%02d) ", cube.getValue()));
        }
    }

}
