import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

public class StringSearchingStudentTests {
    private StringSearching stringSearching;
    private List<Integer> mattAnswer;
    private List<Integer> emptyList;
    private SearchableString matt;
    private SearchableString mattNotThere;
    private SearchableString mattText;

    private List<Integer> kmpAnswer;
    private SearchableString kmpPattern;
    private SearchableString kmpText;
    private SearchableString kmpNotThere;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        stringSearching = new StringSearching();

        matt = new SearchableString("matt");
        mattNotThere = new SearchableString("I have a friend karen who has "
                + "a parakeet.");
        mattText = new SearchableString("I have a friend matt who has "
                + "a mattress.");

        mattAnswer = new ArrayList<>();
        mattAnswer.add(16);
        mattAnswer.add(31);

        emptyList = new ArrayList<>();

        kmpPattern = new SearchableString("ababa");
        kmpText = new SearchableString("ababaaababa");
        kmpNotThere = new SearchableString("ababbaba");

        kmpAnswer = new ArrayList<>();
        kmpAnswer.add(0);
        kmpAnswer.add(6);
    }

    @Test(timeout = TIMEOUT)
    public void testBuildFailureTable() {
        int[] failureTable = stringSearching.buildFailureTable(kmpPattern);
        int[] expected = {0, 0, 1, 2, 3};
        assertArrayEquals(expected, failureTable);
        assertTrue("kmpPattern count was " + kmpPattern.getCount()
                + ". Should be <= 10", kmpPattern.getCount() <= 10);
    }

    @Test(timeout = TIMEOUT)
    public void testKMPThere() {
        assertEquals(kmpAnswer, stringSearching.kmp(kmpPattern, kmpText));
        assertTrue("kmpText count was " + kmpText.getCount()
                + ". Should be <= 18", kmpText.getCount() <= 18);
    }

    @Test(timeout = TIMEOUT)
    public void testKMPNotThere() {
        assertEquals(emptyList, stringSearching.kmp(kmpPattern, kmpNotThere));
        assertTrue("kmpNotThere count was " + kmpNotThere.getCount()
                + ". Should be <= 10", kmpNotThere.getCount() <= 10);
    }


    @Test(timeout = TIMEOUT)
    public void testBuildLastTable() {
        int[] lastTable = stringSearching.buildLastTable(matt);
        assertEquals(Character.MAX_VALUE + 1, lastTable.length);
        for (int i = 0; i < lastTable.length; i++) {
            switch (i) {
            case 'm':
                assertEquals(0, lastTable[i]);
                break;
            case 'a':
                assertEquals(1, lastTable[i]);
                break;
            case 't':
                assertEquals(3, lastTable[i]);
                break;
            default:
                assertEquals(-1, lastTable[i]);
                break;
            }
        }
        assertEquals(4, matt.getCount());
    }

    @Test(timeout = TIMEOUT)
    public void testBooyerMooreThere() {
        System.out.print(matt.charAt(2));
        assertEquals(mattAnswer, stringSearching.boyerMoore(matt, mattText));
        assertEquals(19, mattText.getCount());
        assertTrue("mattText count was " + mattText.getCount()
                + ". Should be <= 19.", mattText.getCount() <= 19);
    }

    @Test(timeout = TIMEOUT)
    public void testBooyerMooreNotThere() {
        assertEquals(emptyList, stringSearching.boyerMoore(matt, mattNotThere));
        assertTrue("mattNotThere count was " + mattNotThere.getCount()
                + ". Should be <= 12.", mattNotThere.getCount() <= 12);
    }

    @Test(timeout = TIMEOUT)
    public void testBooyerMooreUnicode() {
        // Written as hex codes so that non-UTF-8 OS'es can read this file
        String unicodeText =
                "\u2612\u2145\u212F\u212F\u24D3\u267B\u2682\u2612\u24D8\u24A2"
                + "\u2244\u25D7\u249F\u24A4\u24A2\u2145\u212F\u212F\u249F";
        String unicodePattern = "\u24A4\u24A2\u2145\u212F";
        SearchableString text = new SearchableString(unicodeText);
        SearchableString pattern = new SearchableString(unicodePattern);
        List<Integer> matches = new ArrayList<Integer>();
        matches.add(13);
        assertEquals(matches, stringSearching.boyerMoore(pattern, text));
        assertTrue("text count was " + text.getCount()
                + ". Should be <= 12.", text.getCount() <= 12);
    }

    @Test(timeout = TIMEOUT)
    public void testGenerateHash() {
        assertEquals(277220518, stringSearching.generateHash(
                    "matt is my friend", 4));
    }

    @Test(timeout = TIMEOUT)
    public void testUpdateHash() {
        assertEquals(731294060, stringSearching.updateHash(99342732, 5, 'a',
                    'q'));
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpThere() {
        assertEquals(mattAnswer, stringSearching.rabinKarp(matt, mattText));
        assertTrue("mattText count was " + mattText.getCount()
                + ". Should be <= 86.", mattText.getCount() <= 86);
    }

    @Test(timeout = TIMEOUT)
    public void testRabinKarpNotThere() {
        assertEquals(emptyList, stringSearching.rabinKarp(matt, mattNotThere));
        assertTrue("mattNotThere count was " + mattNotThere.getCount()
                + ". Should be <= 80.", mattNotThere.getCount() <= 80);
    }
}
