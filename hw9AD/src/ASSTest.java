import org.junit.Before;
import org.junit.Test;

import java.lang.Integer;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * @author Adithya Nott
 * @version alpha
 */
public class ASSTest {
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
    public void testAllTheExceptions() {
        somethingIsWrongWithKMP();
        somethingIsWrongWithBuildFailureTable();
        somethingIsWrongWithBoyerMoore();
        somethingIsWrongWithBuildLastTable();
        somethingIsWrongWithGenerateHash();
        somethingIsWrongWithUpdateHash();
    }

    private void somethingIsWrongWithKMP() {
        try {
            stringSearching.kmp(null, kmpText);
            fail("Didn't throw exception for null pattern.");
        } catch (IllegalArgumentException e) {
            assertNotNull("IllegalArgumentException needs a message!");
            assertTrue("Make sure the message is useful.",
                    e.getMessage().length() > 10);
        }
        try {
            stringSearching.kmp("", kmpText);
            fail("Didn't throw exception for empty pattern.");
        } catch (IllegalArgumentException e) {
            assertNotNull("IllegalArgumentException needs a message!");
            assertTrue("Make sure the message is useful.",
                    e.getMessage().length() > 10);
        }
        try {
            stringSearching.kmp(kmpPattern, null);
            fail("Didn't throw exception for null text.");
        } catch (IllegalArgumentException e) {
            assertNotNull("IllegalArgumentException needs a message!");
            assertTrue("Make sure the message is useful.",
                    e.getMessage().length() > 10);
        }
    }

    private void somethingIsWrongWithBuildFailureTable() {
        try {
            stringSearching.buildFailureTable(null);
            fail("Didn't throw exception for null pattern.");
        } catch (IllegalArgumentException e) {
            assertNotNull("IllegalArgumentException needs a message!");
            assertTrue("Make sure the message is useful.",
                    e.getMessage().length() > 10);
        }
    }

    private void somethingIsWrongWithBoyerMoore() {
        try {
            stringSearching.boyerMoore(null, mattText);
            fail("Didn't throw exception for null pattern.");
        } catch (IllegalArgumentException e) {
            assertNotNull("IllegalArgumentException needs a message!");
            assertTrue("Make sure the message is useful.",
                    e.getMessage().length() > 10);
        }
        try {
            stringSearching.boyerMoore("", mattText);
            fail("Didn't throw exception for empty pattern.");
        } catch (IllegalArgumentException e) {
            assertNotNull("IllegalArgumentException needs a message!");
            assertTrue("Make sure the message is useful.",
                    e.getMessage().length() > 10);
        }
        try {
            stringSearching.boyerMoore(matt, null);
            fail("Didn't throw exception for null text.");
        } catch (IllegalArgumentException e) {
            assertNotNull("IllegalArgumentException needs a message!");
            assertTrue("Make sure the message is useful.",
                    e.getMessage().length() > 10);
        }
    }

    private void somethingIsWrongWithBuildLastTable() {
        try {
            stringSearching.buildLastTable(null);
            fail("Didn't throw exception for null pattern.");
        } catch (IllegalArgumentException e) {
            assertNotNull("IllegalArgumentException needs a message!",
                    e.getMessage());
            assertTrue("Make sure the message is useful.",
                    e.getMessage().length() > 10);
        }
    }

    private void somethingIsWrongWithGenerateHash() {
        String randomString = "";
        int randomLength = (int) (Math.random() * 40);
        for (int i = 0; i < randomLength; i++) {
            randomString += (char) (Math.random() * Character.MAX_VALUE);
        }

        try {
            stringSearching.generateHash(null, 0);
            fail("Null character sequence did not throw exception.");
        } catch (IllegalArgumentException e) {
            assertNotNull("IllegalArgumentException needs a message",
                    e.getMessage());
            assertTrue("Make sure the message is useful.",
                    e.getMessage().length() > 10);
        }

        try {
            stringSearching.generateHash(randomString,
                    (int) (Math.random() * Integer.MIN_VALUE));
            fail("Negative length did not throw exception.");
        } catch (IllegalArgumentException e) {
            assertNotNull("IllegalArgumentException needs a message!",
                    e.getMessage());
            assertTrue("Make sure the message is useful.",
                    e.getMessage().length() > 10);
        }

        try {
            stringSearching.generateHash(
                    randomString, randomLength + (int) (Math.random() * 5));
            fail("length greater than randomString.length() did not throw"
                    + "Exception");
        } catch (IllegalArgumentException e) {
            assertNotNull("IllegalArgumentException needs a message!");
            assertTrue("Make sure the message is useful.",
                    e.getMessage().length() > 10);
        }
    }

    private void somethingIsWrongWithUpdateHash() {
        try {
            stringSearching.updateHash(1,
                    (int) (Math.random() * Integer.MIN_VALUE), 'c', 'a');
            fail("Random negative length did not throw Exception");
        } catch (IllegalArgumentException e) {
            assertNotNull("IllegalArgumentException needs a message!");
            assertTrue("Make sure the message is useful.",
                    e.getMessage().length() > 10);
        }
    }
}