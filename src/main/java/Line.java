
import org.apache.commons.lang.StringUtils;
import java.util.Arrays;
import java.util.List;

public class Line {

    private final String rawLine;
    private final List<String> words;
    private Integer errorIndex = -1;

    Line(String line) {
        rawLine = line;
        words = Arrays.asList(line.split("\\s"));
    }

    /**
     * Got index is less unsurprised for tutored user, because casual people indexes starting from one, not zero.
     * @return current index of first error character increased by one.
     */
    public Integer getFirstErrorIndex() {
        return errorIndex + 1;
    }

    /**
     * This will help user to faster localize his error.
     * @return whole word with first error occurrence.
     */
    public String getFirstWrongWord() {
        if (errorIndex == -1) {
            return "";
        }

        int lettersSum = 0;

        for (String word : words) {

            if (word.length() + lettersSum > errorIndex) {
                return word;
            } else {
                lettersSum += word.length();
            }
        }

        return "";
    }

    /**
     * Uses string stored during object construction to find differences with one provided
     * by the user.
     * @param userInput string from user, which will be compared with already stored one.
     * @return only information about complete characters compatibility.
     */
    public boolean compare(String userInput) {
        this.errorIndex =  StringUtils.indexOfDifference(rawLine, userInput);
        return this.errorIndex == -1;
    }
}
