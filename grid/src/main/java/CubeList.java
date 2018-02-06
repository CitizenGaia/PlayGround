import java.util.ArrayList;
import java.util.List;

public class CubeList {

    enum Layout {
        HORIZONTAL(9),
        VERTICAL(1),
        RECTANGLE(3);

        private int value;

        Layout(int value) {
            this.value = value;
        }

        int value() {
            return value;
        }
    }

    private List<Cube> row;
    private Layout layout = Layout.HORIZONTAL;

    public CubeList() {
        new CubeList(Layout.HORIZONTAL);
    }

    public CubeList(CubeList.Layout layout) {
        this.layout = layout;
        row = new ArrayList<Cube>();
    }

    public CubeList(List<Cube> cubeList) {
        new CubeList(Layout.HORIZONTAL, cubeList);
    }

    public CubeList(CubeList.Layout layout, List<Cube> cubeList) {
        this.layout = layout;
        row = cubeList;
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
        int breakAt = layout.value();
        int cubeNo = 0;
        StringBuilder builder = new StringBuilder();
        for (Cube cube : row) {
            builder.append(String.format(" (%02d) ", cube.getValue()));
            if (++cubeNo >= breakAt) {
                cubeNo = 0;
                builder.append("\n");
            }
        }
        return builder.toString();
    }

}
