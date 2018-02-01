
public class Cube {

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

    public boolean validate(int value) {
        return (-1 == value) || (0 < value && value <= 9);
    }
}
