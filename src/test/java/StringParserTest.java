import org.junit.Assert;
import org.junit.Test;


public class StringParserTest {
    StringParser parser = new StringParser();

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyArgument () {

        parser.parse("");

    }@Test(expected = IllegalArgumentException.class)
    public void testNullArgument () {

//        System.setIn(null);
        parser.parse(null);

    }
    @Test(expected = IllegalArgumentException.class)
    public void testNonValidArgument () {
        parser.parse("+w");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testWithoutDigitArgument () {
        parser.parse("[xxyw]xy");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonBrasesArgument () {
        parser.parse("[w");
    }

    @Test
    public void testResultLineOfSubstrings () {
        Assert.assertEquals("xyzxyzxyzxyxyxyxyz", parser.parse("3[xyz]4[xy]z"));
    }

    @Test
    public void testResultMultilineSubstrings () {
        Assert.assertEquals("xxxyxxxy", parser.parse("2[3[x]y]"));
    }
}
