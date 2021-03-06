package dk.lejengnaver.sudoko;

import java.util.ArrayList;
import java.util.List;

public class CubeList {

    enum Layout {
        HORIZONTAL(9),
        VERTICAL(1),
        SQUARED(3);

        private int value;

        Layout(int value) {
            this.value = value;
        }

        int value() {
            return value;
        }
    }

    private List<Cube> list;
    private Layout layout;
    private Integer id;

    CubeList(Layout layout, Integer id) {
        this.layout = layout;
        this.id = id;
        list = new ArrayList<>();
    }

    public boolean addCube(int index, Cube cube) {
        if (!validate(index, cube)) {
            return false;
        }
        list.add(index - 1, cube);
        cube.register(this);
        return true;
    }

    public List<Integer> getCubeValueList() {
        List<Integer> values = new ArrayList<>(list.size());
        for (Cube cube : list) {
            values.add(cube.getValue());
        }
        return values;
    }

    private boolean validate(int index, Cube cube) {
        return (cube != null) && (index == (1 + list.size())) && 0 < index && index <= 9;
    }

    public String dump() {
        int breakAt = layout.value();
        int cubeNo = 0;
        StringBuilder builder = new StringBuilder();
        builder.append(layout.name()).append("\n");

        for (Cube cube : list) {
            builder.append(String.format(" (%02d) ", cube.getValue()));
            if (++cubeNo >= breakAt) {
                cubeNo = 0;
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    public Layout getLayout() {
        return layout;
    }

    public Integer getId() {
        return id;
    }

}
