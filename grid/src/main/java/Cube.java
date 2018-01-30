
public class Cube {

    private int value;

    public Cube() {
        value = -1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean validate(int value) {
        if (0 < value && value <= 9) {
            return true;
        }
        return false;
    }
}
