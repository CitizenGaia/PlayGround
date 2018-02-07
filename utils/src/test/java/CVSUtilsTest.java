import org.junit.Test;

import java.io.File;
import java.util.logging.Logger;

import static junit.framework.TestCase.assertTrue;

public class CVSUtilsTest {

    private final static Logger logger = Logger.getLogger(CVSUtilsTest.class.getName());

    private String match = "" +
            "[1][2][3]" + "\n" +
            "[4][5][6]" + "\n" +
            "[7][8][9]" + "\n";

    @Test
    public void loadDefaultExampleFile() {
        CVSUtils utils = new CVSUtils();
        File cvsFile = new File("src/test/resources/csvFile");
        assertTrue("cvsFile is fine" , utils.load(cvsFile));
        String dump = utils.dump();
        assertTrue("The dumped values are not as expected", match.equals(dump));
    }

    @Test
    public void loadSemicolonExampleFile() {
        CVSUtils utils = new CVSUtils();
        utils.setSeparator(';');
        File cvsFile = new File("src/test/resources/csvFileSemicolon");
        assertTrue("csvFileSemicolon is fine" , utils.load(cvsFile)) ;
        String dump = utils.dump();
        assertTrue("The dumped values are not as expected", match.equals(dump));
    }

    @Test
    public void loadSingleQuoteExampleFile() {
        CVSUtils utils = new CVSUtils();
        utils.setQuote('\'');
        File cvsFile = new File("src/test/resources/csvFileSingleQuote");
        assertTrue("csvFileSingleQuote is fine" , utils.load(cvsFile)) ;
        String dump = utils.dump();
        logger.info(dump);
        assertTrue("The dumped values are not as expected", match.equals(dump));
    }

    @Test
    public void dump() {
    }
}