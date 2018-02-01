import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CVSUtils {

    private Scanner scanner;
    private boolean loaded = false;

    private char separator = ',';
    private char quote = '"';

    public boolean load(File cvsFile) {
        try {
            scanner = new Scanner(cvsFile);
            loaded = true;
        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
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

    public String dump() {
        StringBuilder builder = new java.lang.StringBuilder();
        while (scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine());
            int i = 0;
            for (String value: line) {
                builder.append("[");
                builder.append(value);
                builder.append("]");
            }
            builder.append("\n")
        }
        scanner.close();
        return builder.toString();
    }

    private List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, separator, quote);
    }

    private List<String> parseLine(String cvsLine, char separators, char customQuote) {
        List<String> result = new ArrayList<String>();
        //if empty, return!
        if (cvsLine == null && cvsLine.isEmpty()) {
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