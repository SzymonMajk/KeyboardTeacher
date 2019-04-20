import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LineTest {

    private Line line;
    private String redLine = "Lorem ipsum dolor sit amet,";
    private String additionalSpace = "Lorem ipsum  dolor sit amet,";
    private String wrongLetter = "Lorem itsum dolor sit amet,";
    private String additionalLetter = "Lorem iptsum dolor sit amet,";

    @Before
    public void setUp() {
        line = new Line(redLine);
    }

    @Test
    public void compareWithNull() {
        Assert.assertFalse(line.compare(null));
    }

    @Test
    public void getFirstWrongWithNull() {
        line.compare(null);
        Assert.assertEquals("Lorem", line.getFirstWrongWord());
    }

    @Test
    public void getFirstErrorIndexWithNull() {
        line.compare(null);
        Assert.assertEquals(java.util.Optional.of(1).get(), line.getFirstErrorIndex());
    }

    @Test
    public void compareWithBlank() {
        Assert.assertFalse(line.compare(""));
    }

    @Test
    public void getFirstWrongWthBlank() {
        line.compare("");
        Assert.assertEquals("Lorem", line.getFirstWrongWord());
    }

    @Test
    public void getFirstErrorIndexWithBlank() {
        line.compare("");
        Assert.assertEquals(java.util.Optional.of(1).get(), line.getFirstErrorIndex());
    }

    @Test
    public void compareWithTheSame() {
        Assert.assertTrue(line.compare(redLine));
    }

    @Test
    public void getFirstWrongWordWithTheSame() {
        line.compare(redLine);
        Assert.assertEquals("", line.getFirstWrongWord());
    }

    @Test
    public void getFirstErrorIndexWithTheSame() {
        line.compare(redLine);
        Assert.assertEquals(java.util.Optional.of(0).get(), line.getFirstErrorIndex());
    }

    @Test
    public void compareWithAdditionalSpace() {
        Assert.assertFalse(line.compare(additionalSpace));
    }

    @Test
    public void getFirstWrongWordWithAdditionalSpace() {
        line.compare(additionalSpace);
        Assert.assertEquals("dolor", line.getFirstWrongWord());
    }

    @Test
    public void getFirstErrorIndexWithAdditionalSpace() {
        line.compare(additionalSpace);
        Assert.assertEquals(java.util.Optional.of(13).get(), line.getFirstErrorIndex());
    }

    @Test
    public void compareWithWrongLetter() {
        Assert.assertFalse(line.compare(wrongLetter));
    }

    @Test
    public void getFirstWrongWordWithWrongLetter() {
        line.compare(wrongLetter);
        Assert.assertEquals("ipsum", line.getFirstWrongWord());
    }

    @Test
    public void getFirstErrorIndexWithWrongLetter() {
        line.compare(wrongLetter);
        Assert.assertEquals(java.util.Optional.of(8).get(), line.getFirstErrorIndex());
    }

    @Test
    public void compareWithAdditionalLetter() {
        Assert.assertFalse(line.compare(additionalLetter));
    }

    @Test
    public void getFirstWrongWordWithAdditionalLetter() {
        line.compare(additionalLetter);
        Assert.assertEquals("ipsum", line.getFirstWrongWord());
    }

    @Test
    public void getFirstErrorIndexWithAdditionalLetter() {
        line.compare(additionalLetter);
        Assert.assertEquals(java.util.Optional.of(9).get(), line.getFirstErrorIndex());
    }
}
