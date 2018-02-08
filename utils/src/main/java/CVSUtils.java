import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Consider fix buggy code from www.mkyong.com
public class CVSUtils {

    private final static Logger logger = Logger.getLogger(CVSUtils.class.getName());

    private char separator = ',';
    private char quote = '"';
    private String regexp = "^(\\[[1-9]\\]){3}(\\n)?$";

    private List<String> extracted;
    private boolean loaded = false;

    private String details = "";

    /**
     * Load CSV formatted 'file'
     * @param cvsFile the CVS fomatted File
     * @return true if succes
     */
    public boolean load(File cvsFile) {
        try {
            Scanner scanner = new Scanner(cvsFile, "UTF-8");
            loaded = extractData(scanner);
            if (!loaded) {
                System.out.println((String.format("Line:\n%s\n does'nt match RegExp:%s", details, regexp)));
            }
        } catch (FileNotFoundException e) {
            logger.warning("File not found" + e.getMessage());
        }
        return loaded;
    }

    /**
     * Load CSV formatted 'file' as InputStream
     * @param inputstream based on the 'file', 'String', byte array a.o.
     * @return true if succes
     */
    public boolean load(InputStream inputstream) {
        Scanner scanner = new Scanner(inputstream, "UTF-8");
        loaded = extractData(scanner);
        if (!loaded) {
            logger.warning((String.format("Line:\n%s\n does'nt match RegExp:%s", details, regexp)));
        }
        return loaded;
    }

    public char getSeparator() {
        return separator;
    }

    public void setSeparator(char separator) {
        this.separator = separator;
    }

    public char getQuote() {
        return quote;
    }

    public void setQuote(char quote) {
        this.quote = quote;
    }

    public String getRegexp() {
        return regexp;
    }

    public void setRegexp(String regexp) {
        this.regexp = regexp;
    }

    private boolean extractData(Scanner scanner) throws IllegalFormatException {
        extracted = new ArrayList<String>();
        Matcher matcher;
        Pattern pattern = Pattern.compile(regexp);

        boolean isValid = true;
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            List<String> parsedLine = parseLine(line);
            StringBuilder builder = new java.lang.StringBuilder();
            for (String value : parsedLine) {
                builder.append("[");
                builder.append(value);
                builder.append("]");
            }
            builder.append("\n");

            matcher = pattern.matcher(builder.toString());
            if (!matcher.matches()) {
                isValid = false;
                details = builder.toString();
            } else {
                System.out.print(matcher.group(0));
            }
            extracted.add(builder.toString());
        }
        scanner.close();
        return isValid;
    }

    public String dump() {
        StringBuilder builder = new java.lang.StringBuilder();
        for (String anExtracted : extracted) {
            List<String> valuesInLine = parseLine(anExtracted);
            for (String value : valuesInLine) {
                builder.append(value);
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    private List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, separator, quote);
    }

    private List<String> parseLine(String cvsLine, char separators, char customQuote) {
        List<String> result = new ArrayList<String>();
        //if empty, return!
        if (cvsLine == null || cvsLine.length()==0) {
            return result;
        }
        StringBuffer curVal = new StringBuffer();
        boolean inQuotes = false;
        boolean startCollectChar = false;
        boolean doubleQuotesInColumn = false;
        char[] chars = cvsLine.toCharArray();
        for (char ch : chars) {
            if (inQuotes) {
                startCollectChar = true;
                if (ch == customQuote) {
                    inQuotes = false;
                    doubleQuotesInColumn = false;
                } else {
                    //Fixed : allow "" in custom quote enclosed
                    if (ch == '\"') {
                        if (!doubleQuotesInColumn) {
                            curVal.append(ch);
                            doubleQuotesInColumn = true;
                        }
                    } else {
                        curVal.append(ch);
                    }
                }
            } else {
                if (ch == customQuote) {
                    inQuotes = true;
                    //Fixed : allow "" in empty quote enclosed
                    if (chars[0] != '"' && customQuote == '\"') {
                        curVal.append('"');
                    }
                    //double quotes in column will hit this!
                    if (startCollectChar) {
                        curVal.append('"');
                    }
                } else if (ch == separators) {
                    result.add(curVal.toString());
                    curVal = new StringBuffer();
                    startCollectChar = false;
                } else if (ch == '\r') {
                    //ignore LF characters
                    continue;
                } else if (ch == '\n') {
                    //the end, break!
                    break;
                } else {
                    curVal.append(ch);
                }
            }
        }
        result.add(curVal.toString());
        return result;
    }
}