
import org.apache.commons.lang.StringUtils;
import java.util.Arrays;
import java.util.List;

public class Line {

    private final String rawLine;
    private final List<String> words;
    private Integer errorIndex;

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
        int firstWrongWordIndex = 0;
        int lettersSum = 0;

        for (int i = 0; i < words.size(); ++i) {
            int wordLength = words.get(i).length();

            if (wordLength + lettersSum > errorIndex) {
                firstWrongWordIndex = i;
                break;
            } else {
                lettersSum += wordLength;
            }
        }

        return words.get(firstWrongWordIndex);
    }

    /**
     * Uses string stored during object construction to find differences with one provided
     * by the user.
     * @param userInput string from user, which will be compared with already stored one.
     * @return only information about complete characters compatibility.
     */
    public boolean compare(String userInput) {

        int indexOfDifference = StringUtils.indexOfDifference(rawLine, userInput);

        if (indexOfDifference == -1) {
            return true;
        } else {
            this.errorIndex = indexOfDifference;
            return false;
        }
    }
}
