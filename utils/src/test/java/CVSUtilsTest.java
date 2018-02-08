import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class CVSUtilsTest {

    private String match = "" +
            "[1][2][3]" + "\n" +
            "[4][5][6]" + "\n" +
            "[7][8][9]" + "\n";

    @Test
    public void loadDefaultExampleFile() {
        CVSUtils utils = new CVSUtils();
        File cvsFile = new File("src/test/resources/csvFile");
        assertTrue("cvsFile is fine", utils.load(cvsFile));
        String dump = utils.dump();
        assertTrue("The dumped values are not as expected", match.equals(dump));
    }

    @Test
    public void loadSemicolonExampleFile() {
        CVSUtils utils = new CVSUtils();
        utils.setSeparator(';');
        File cvsFile = new File("src/test/resources/csvFileSemicolon");
        assertTrue("csvFileSemicolon is fine", utils.load(cvsFile));
        String dump = utils.dump();
        assertTrue("The dumped values are not as expected", match.equals(dump));
    }

    @Test
    public void loadSingleQuoteExampleFile() {
        CVSUtils utils = new CVSUtils();
        utils.setQuote('\'');
        File cvsFile = new File("src/test/resources/csvFileSingleQuote");
        assertTrue("csvFileSingleQuote is fine", utils.load(cvsFile));
        String dump = utils.dump();
        assertTrue("The dumped values are not as expected", match.equals(dump));
    }

    @Test
    public void loadDefaultExampleFileAsInputStream() {
        CVSUtils utils = new CVSUtils();
        File cvsFile = new File("src/test/resources/csvFile");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(cvsFile);
        } catch (FileNotFoundException e) {
            fail("FileNotFoundException should not occur");
        }
        assertTrue("cvsFile as inputStream is fine", utils.load(inputStream));
        String dump = utils.dump();
        assertTrue("The dumped values are not as expected", match.equals(dump));
    }

    @Test
    public void loadDefaultExampleFileAsString() {
        CVSUtils utils = new CVSUtils();
        String string = null;
        try {
            string = new String(Files.readAllBytes(Paths.get(".", "src/test/resources/csvFile")));
        } catch (IOException e) {
            fail("IOException should not occur");
        }
        assertTrue("cvsFile as string is fine", utils.load(string));
        String dump = utils.dump();
        assertTrue("The dumped values are not as expected", match.equals(dump));
    }

}