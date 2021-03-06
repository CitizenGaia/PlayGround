package dk.lejengnaver.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: refactor code
public class CVSUtils {

    private final static Logger logger = Logger.getLogger(CVSUtils.class.getName());

    private char separator = ',';
    private char quote = '"';

    private List<String> extracted;
    private List<List<String>> extractedData;
    private boolean loaded = false;

    private String details = "";

    /**
     * Load CSV formatted 'file'
     * @param cvsFile the CVS fomatted File
     * @return true if succes
     */
    public boolean load(File cvsFile, String regexp) {
        try {
            Scanner scanner = new Scanner(cvsFile, "UTF-8");
            loaded = extractData(scanner, regexp);
            if (!loaded) {
                logger.warning((String.format("Line:\n%s\n does'nt match RegExp:%s", details, regexp)));
            }
        } catch (FileNotFoundException e) {
            logger.warning("File not found" + e.getMessage());
        }
        return loaded;
    }

    /**
     * Load CSV formatted 'file' as InputStream
     * @param inputstream based on the CSV formatted 'file'
     * @return true if succes
     */
    public boolean load(InputStream inputstream, String regexp) {
        Scanner scanner = new Scanner(inputstream, "UTF-8");
        loaded = extractData(scanner, regexp);
        if (!loaded) {
            logger.warning((String.format("Line:\n%s\n does'nt match RegExp:%s", details, regexp)));
        }
        return loaded;
    }

    /**
     * Load CSV formatted 'file' as InputStream
     * @param string based on the CSV formatted 'file'
     * @return true if succes
     */
    public boolean load(String string, String regexp) {
        Scanner scanner = new Scanner(string);
        loaded = extractData(scanner, regexp);
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

    private boolean extractData(Scanner scanner, String regexp) throws IllegalFormatException {
        extracted = new ArrayList<>();
        extractedData = new ArrayList<>();
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
                logger.info(matcher.group(0));
            }
            extractedData.add(parsedLine);
            extracted.add(builder.toString());
        }
        scanner.close();
        return isValid;
    }

    public List<List<String>> getExtractedData() {
        return extractedData;
    }

    public List<String> getExtracted() {
        return extracted;
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
        List<String> result = new ArrayList<>();
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