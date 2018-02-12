package dk.lejengnaver.sudoko;

import java.util.HashMap;
import java.util.Map;

public class Cube {

    private Map<CubeList.Layout, CubeList> registrations = new HashMap<>();
    private int value;

    public Cube() {
        value = -1;
    }

    public int getValue() {
        return value;
    }

    public boolean setValue(int value) {
        if (!validate(value)) {
            return false;
        }
        this.value = value;
        return true;
    }

    public void register(CubeList cubeList) {
        if (!registrations.containsKey(cubeList.getLayout())) {
            registrations.put(cubeList.getLayout(), cubeList);
        }
    }

    public CubeList getRegister(CubeList.Layout layout) {
        if (registrations.containsKey(layout)) {
            return registrations.get(layout);
        }
        return null;
    }


    public static boolean validate(int value) {
        return (-1 == value) || (0 < value && value <= 9);
    }
}
