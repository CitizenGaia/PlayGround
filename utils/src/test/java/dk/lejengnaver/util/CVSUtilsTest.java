package dk.lejengnaver.util;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class CVSUtilsTest {

    private final static String EINS = "[1][2][3]";
    private final static String ZWEI = "[4][5][6]";
    private final static String DREI = "[7][8][9]";
    private final static String LF = "\n";

    private String match = "" +
            EINS + LF +
            ZWEI + LF +
            DREI + LF;

    @Test
    public void loadDefaultExampleFile() {
        CVSUtils utils = new CVSUtils();
        File cvsFile = new File("src/test/resources/csvFile");
        assertTrue("cvsFile will not load fine", utils.load(cvsFile, GameFormat.REGEXP_SQUAREBASED));
        String dump = utils.dump();
        assertTrue("The dumped values are not as expected", match.equals(dump));
    }

    @Test
    public void loadSemicolonExampleFile() {
        CVSUtils utils = new CVSUtils();
        utils.setSeparator(';');
        File cvsFile = new File("src/test/resources/csvFileSemicolon");
        assertTrue("csvFileSemicolon will not load fine", utils.load(cvsFile, GameFormat.REGEXP_SQUAREBASED));
        String dump = utils.dump();
        assertTrue("The dumped values are not as expected", match.equals(dump));
    }

    @Test
    public void loadSingleQuoteExampleFile() {
        CVSUtils utils = new CVSUtils();
        utils.setQuote('\'');
        File cvsFile = new File("src/test/resources/csvFileSingleQuote");
        assertTrue("csvFileSingleQuote will not load fine", utils.load(cvsFile, GameFormat.REGEXP_SQUAREBASED));
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
        assertTrue("cvsFile as inputStream will not load fine", utils.load(inputStream, GameFormat.REGEXP_SQUAREBASED));
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
        assertTrue("cvsFile as string will not load fine", utils.load(string, GameFormat.REGEXP_SQUAREBASED));
        String dump = utils.dump();
        assertTrue("The dumped values are not as expected", match.equals(dump));

        List<String> extracted = utils.getExtracted();
        assertTrue("1.st entry doesn't match", extracted.get(0).equals(EINS + LF));
        assertTrue("2.nd entry doesn't match", extracted.get(1).equals(ZWEI + LF));
        assertTrue("3.rd entry doesn't match", extracted.get(2).equals(DREI + LF));
    }

}