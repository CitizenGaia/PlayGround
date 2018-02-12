package dk.lejengnaver.sudoko;

import dk.lejengnaver.sudoko.Cube;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CubeTest {

    @Test
    public void getValueWhenJustInitilized() {
        Cube cube = new Cube();
        assertEquals(-1, cube.getValue());
    }

    @Test
    public void setValueAsInitialized() {
        assertTrue("valid integer value [as initialized ~ meaning no value]", Cube.validate(-1));
    }

    @Test
    public void setValueWithValidInteger() {
        Cube cube = new Cube();
        assertTrue("valid integer value", cube.setValue(1));
        assertTrue("valid integer value", cube.setValue(2));
        assertTrue("valid integer value", cube.setValue(8));
        assertTrue("valid integer value", cube.setValue(9));
    }

    @Test
    public void setValueWithInvalidInteger() {
        Cube cube = new Cube();
        assertEquals(false, cube.setValue(0));
        assertEquals(false, cube.setValue(10));
    }

    @Test
    public void setValueWithValidIntegerAndVerify() {
        Cube cube = new Cube();
        for (int value = 1; value <= 9; value++) {
            assertTrue("valid integer value", cube.setValue(value));
            assertTrue("The valid integer is as expected (same as set)", value == cube.getValue());
        }
    }

    @Test
    public void validateWithValidInteger() {
        for (int value = 1; value <= 9; value++) {
            assertTrue("valid integer value", Cube.validate(value));
        }
        assertTrue("valid integer value [as initialized ~ meaning no value]", Cube.validate(-1));
    }

    @Test
    public void validateWithInvalidInteger() {
        Cube cube = new Cube();
        assertFalse("is an invalid integer value", cube.setValue(-2));
        assertFalse("is an invalid integer value", cube.setValue(0));
        assertFalse("is an invalid integer value", cube.setValue(10));
    }

}