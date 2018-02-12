import java.util.logging.Logger;

public class ValueValidator {

    private final static Logger logger = Logger.getLogger(ValueValidator.class.getName());

    /**
     * Validates integer value
     * @param string representing an integer value [1..9} or -1.
     * @return null if string argument doesn't contain integer value [1..9} or -1.
     */
    public Integer getInteger(String string) {
        try {
            Integer val = Integer.parseInt(string);
            if ((val >= 1 && val <= 9) || val == -1) {
                return val;
            }
            String msg = String.format("Wrong Value [%s]", string);
            logger.warning(msg);
            return null;
        } catch (NumberFormatException e) {
            String msg = String.format("Wrong Format [%s]", string);
            logger.warning(msg);
            return null;
        }
    }

}
