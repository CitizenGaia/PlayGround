import org.junit.Test;

import static org.junit.Assert.*;

public class RowTest {

    @Test
    public void addCubeWithACorrectOrder() {
        Row row = new Row();
        for (int index = 1; index <= 9; index++) {
            assertEquals(true, row.addCube(index, new Cube()));
        }
    }

    @Test
    public void addCubeWithAnIncorrectOrder() {
        Row row = new Row();
        assertEquals(false, row.addCube(2, new Cube()));
        assertEquals(true, row.addCube(1, new Cube()));
        assertEquals(true, row.addCube(2, new Cube()));
        assertEquals(false, row.addCube(1, new Cube()));
    }

    @Test
    public void addCubeWithAnIncorrectIndexValue() {
        String msg = "Valid Cube added by use of an incorrect index";
        Row row = new Row();
        assertFalse(msg, row.addCube(0, new Cube()));
        assertFalse(msg, row.addCube(10, new Cube()));
        assertFalse(msg, row.addCube(-1, new Cube()));
    }

    @Test
    public void addCubeWithAnNullCube() {
        String msg = "An invalid Cube added by the correct index";
        Row row = new Row();
        assertFalse(msg, row.addCube(1, null));
    }

    @Test
    public void getCubeValueList() {
        Row row = new Row();
        for (int index = 1; index <= 9; index++) {
            assertTrue("Valid Cube added by the correct index", row.addCube(index, new Cube()));
            assertTrue("The index matches the number of Cubes", index == row.getCubeValueList().size());
        }
    }

    @Test
    public void dumpFullInitilizedRow() {
        Row row = new Row();
        for (int index = 1; index <= 9; index++) {
            row.addCube(index, new Cube());
        }
        assertTrue("The Row contains only initialized values (-1)", "".equals((row.dump().replaceAll("\\(-1\\)", "")).trim()));
    }
}