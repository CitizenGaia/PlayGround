import org.junit.Test;

import static org.junit.Assert.*;

public class CubeListTest {

    @Test
    public void addCubeWithACorrectOrderUsingVerticalLayout() {
        CubeList cubeList = new CubeList(CubeList.Layout.VERTICAL, 1);
        for (int index = 1; index <= 9; index++) {
            assertEquals(true, cubeList.addCube(index, new Cube()));
        }
        assertEquals(cubeList.getLayout(), CubeList.Layout.VERTICAL);
    }

    @Test
    public void addCubeWithAnIncorrectOrder() {
        CubeList cubeList = new CubeList(CubeList.Layout.SQUARED, 1);
        assertEquals(false, cubeList.addCube(2, new Cube()));
        assertEquals(true, cubeList.addCube(1, new Cube()));
        assertEquals(true, cubeList.addCube(2, new Cube()));
        assertEquals(false, cubeList.addCube(1, new Cube()));
    }

    @Test
    public void addCubeWithAnIncorrectIndexValue() {
        String msg = "Valid Cube added by use of an incorrect index";
        CubeList cubeList = new CubeList(CubeList.Layout.HORIZONTAL, 1);
        assertFalse(msg, cubeList.addCube(0, new Cube()));
        assertFalse(msg, cubeList.addCube(10, new Cube()));
        assertFalse(msg, cubeList.addCube(-1, new Cube()));
    }

    @Test
    public void addCubeWithAnNullCube() {
        String msg = "An invalid Cube added by the correct index";
        CubeList cubeList = new CubeList(CubeList.Layout.HORIZONTAL, 1);
        assertFalse(msg, cubeList.addCube(1, null));
        assertEquals(cubeList.getLayout(), CubeList.Layout.HORIZONTAL);
    }

    @Test
    public void getCubeValueList() {
        CubeList cubeList = new CubeList(CubeList.Layout.HORIZONTAL, 1);
        for (int index = 1; index <= 9; index++) {
            assertTrue("Valid Cube added by the correct index", cubeList.addCube(index, new Cube()));
            assertTrue("The index matches the number of Cubes", index == cubeList.getCubeValueList().size());
        }
    }

    @Test
    public void dumpFullInitilizedRow() {
        CubeList cubeList = new CubeList(CubeList.Layout.HORIZONTAL, 1);
        for (int index = 1; index <= 9; index++) {
            cubeList.addCube(index, new Cube());
        }
        assertTrue("The CubeList contains only initialized values (-1)", "HORIZONTAL".equals((cubeList.dump().replaceAll(" \\(-1\\) ", "")).trim()));
        assertEquals(cubeList.getLayout(), CubeList.Layout.HORIZONTAL);
    }
}