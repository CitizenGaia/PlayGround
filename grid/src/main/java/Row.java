import java.util.ArrayList;
import java.util.List;

public class Row {

    private List<Cube> row;

    public Row() {
        row = new ArrayList<Cube>(9);
    }

    public int size() {
        return row.size();
    }

    public boolean addCube(int index, Cube cube) {
        if (!validate(index, cube)) {
            return false;
        }
        row.add(index - 1, cube);
        return true;
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

    private boolean validate(int index, Cube cube) {
        return (cube != null) && (index == (1 + row.size())) && 0 < index && index <= 9;
    }

    public String dump() {
        StringBuilder builder = new StringBuilder();
        for (Cube cube : row) {
            builder.append(String.format(" (%02d) ", cube.getValue()));
        }
        return builder.toString();
    }

}
