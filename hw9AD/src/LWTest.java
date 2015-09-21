import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

/**
 * Created by lovissahuigita on 4/2/2015.
 * v1.2
 */
public class LWTest {

    private StringSearching stringSearching = new StringSearching();
    public static final int TIMEOUT = 200;

    @Test(timeout = TIMEOUT)
    public void FailureTable() {
        SearchableString pattern = new SearchableString("abcabc");
        int[] failureTable = {0, 0, 0, 1, 2, 3};
        assertArrayEquals(failureTable, stringSearching.buildFailureTable(pattern));
    }

    @Test(timeout = TIMEOUT)
    public void FailureTable2() {
        SearchableString pattern = new SearchableString("lovlvolovlolovl");
        int[] failureTable = {0, 0, 0, 1, 0, 0, 1, 2, 3, 4, 2, 1, 2, 3, 4};
        assertArrayEquals(failureTable, stringSearching.buildFailureTable(pattern));
    }

    @Test(timeout = TIMEOUT)
    public void kmpTest() {
        SearchableString pattern = new SearchableString("banana");
        SearchableString text = new SearchableString("bababa babanana bababa babanana");
        List<Integer> answer = new ArrayList<>();
        answer.add(9);
        answer.add(25);
        assertEquals(answer, stringSearching.kmp(pattern, text));
        assertTrue("text count was " + text.getCount()
                + ". Should be <= 44.", text.getCount() <= 44);
    }

    @Test(timeout = TIMEOUT)
    public void kmpTest2() {
        SearchableString pattern = new SearchableString("xxyyxx");
        SearchableString text = new SearchableString("xxyyxxyyxxyyxxyyxxyyxxyyxx");
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(4);
        answer.add(8);
        answer.add(12);
        answer.add(16);
        answer.add(20);
        assertEquals(answer, stringSearching.kmp(pattern, text));
        assertTrue("text count was " + text.getCount()
                + ". Should be <= 44.", text.getCount() <= 56);
    }

    @Test(timeout = TIMEOUT)
    public void kmpTest3() {
        SearchableString pattern = new SearchableString("is");
        SearchableString text = new SearchableString("Knuth-Morris-Pratt algorithm that relies on the failure table " +
                "(also called failure function). Make sure to implement the function before implementing this method." +
                " Works better with small alphabets.");
        List<Integer> answer = new ArrayList<>();
        answer.add(10);
        answer.add(152);
        assertEquals(answer, stringSearching.kmp(pattern, text));
        assertTrue("text count was " + text.getCount()
                + ". Should be <= 208.", text.getCount() <= 208);
    }
    @Test(timeout = TIMEOUT)
    public void lastOccurenceTable() {
        SearchableString pattern = new SearchableString("abcbabc");
        int[] lastTable = stringSearching.buildLastTable(pattern);
        assertEquals(Character.MAX_VALUE + 1, lastTable.length);
        for (int i = 0; i < lastTable.length; i++) {
            switch (i) {
                case 'a':
                    assertEquals(4, lastTable[i]);
                    break;
                case 'b':
                    assertEquals(5, lastTable[i]);
                    break;
                case 'c':
                    assertEquals(6, lastTable[i]);
                    break;
            }
        }
        assertEquals(7, pattern.getCount());
    }

    @Test(timeout = TIMEOUT)
    public void lastOccurenceTable2() {
        SearchableString pattern = new SearchableString("thisisapatternsearchinghomework");
        int[] lastTable = stringSearching.buildLastTable(pattern);
        assertEquals(Character.MAX_VALUE + 1, lastTable.length);
        for (int i = 0; i < lastTable.length; i++) {
            switch (i) {
                case 'k':
                    assertEquals(30, lastTable[i]);
                    break;
                case 'r':
                    assertEquals(29, lastTable[i]);
                    break;
                case 'o':
                    assertEquals(28, lastTable[i]);
                    break;
                case 'w':
                    assertEquals(27, lastTable[i]);
                    break;
                case 'e':
                    assertEquals(26, lastTable[i]);
                    break;
                case 'm':
                    assertEquals(25, lastTable[i]);
                    break;
                case 'h':
                    assertEquals(23, lastTable[i]);
                    break;
                case 'g':
                    assertEquals(22, lastTable[i]);
                    break;
                case 'n':
                    assertEquals(21, lastTable[i]);
                    break;
                case 'i':
                    assertEquals(20, lastTable[i]);
                    break;
                case 'c':
                    assertEquals(18, lastTable[i]);
                    break;
                case 'a':
                    assertEquals(16, lastTable[i]);
                    break;
                case 's':
                    assertEquals(14, lastTable[i]);
                    break;
                case 't':
                    assertEquals(10, lastTable[i]);
                    break;
                case 'p':
                    assertEquals(7, lastTable[i]);
                    break;
            }
        }
        assertEquals(pattern.length(), pattern.getCount());
    }

    @Test(timeout = TIMEOUT)
    public void boyerMoore() {
        SearchableString pattern = new SearchableString("is");
        SearchableString text = new SearchableString("thisisapatternsearchinghomework");
        List<Integer> answer = new ArrayList<>();
        answer.add(2);
        answer.add(4);
        assertEquals(answer, stringSearching.boyerMoore(pattern, text));
        assertTrue("text count was " + text.getCount()
                + ". Should be <= 20.", text.getCount() <= 20);
    }

    @Test(timeout = TIMEOUT)
    public void boyerMoore2() {
        SearchableString pattern = new SearchableString("is");
        SearchableString text = new SearchableString("thisisapatternsearchinghomework");
        List<Integer> answer = new ArrayList<>();
        answer.add(2);
        answer.add(4);
        assertEquals(answer, stringSearching.boyerMoore(pattern, text));
        assertTrue("text count was " + text.getCount()
                + ". Should be <= 20.", text.getCount() <= 20);
    }

    @Test(timeout = TIMEOUT)
    public void boyerMoore3() {
        SearchableString pattern = new SearchableString(";");
        SearchableString text = new SearchableString("Boyer Moore algorithm that relies on last table. Make sure to" +
                " implement the table before implementing this method. Works better with large alphabets.");
        List<Integer> answer = new ArrayList<>();
        assertEquals(answer, stringSearching.boyerMoore(pattern, text));
        assertTrue("text count was " + text.getCount()
                + ". Should be <= 149.", text.getCount() <= 149);
    }

    @Test(timeout = TIMEOUT)
    public void generateHashTest() {
        int BASE = 433;
        SearchableString pattern = new SearchableString("abcde");
        int answer = ('a' * exp(BASE, 4)) + ('b' * exp(BASE, 3)) + ('c' * exp(BASE, 2)) + ('d' * exp(BASE, 1)) + ('e' * exp(BASE, 0));
        assertEquals(answer, stringSearching.generateHash(pattern, pattern.length()));
    }

    @Test(timeout = TIMEOUT)
    public void generateHashTest2() {
        int BASE = 433;
        SearchableString pattern = new SearchableString("tryandhashthisstring");
        assertEquals(-1564278299, stringSearching.generateHash(pattern, pattern.length()));
    }

    @Test(timeout = TIMEOUT)
    public void updateHashTest() {
        SearchableString pattern = new SearchableString("aa");
        int oldHash = stringSearching.generateHash(pattern, 1);
        assertEquals(oldHash, stringSearching.updateHash(oldHash, 1, 'a', 'a'));

        SearchableString pattern2 = new SearchableString("aaaa");
        int oldHash2 = stringSearching.generateHash(pattern2, 3);
        assertEquals(oldHash2, stringSearching.updateHash(oldHash2, 3, pattern2.charAt(0), pattern2.charAt(3)));

        SearchableString pattern3 = new SearchableString("zabcde");
        int oldHash3 = stringSearching.generateHash(pattern3, pattern3.length() - 1);
        assertEquals(stringSearching.generateHash("abcde", 5), stringSearching.updateHash(oldHash3, 5, pattern3.charAt(0), pattern3.charAt(5)));
    }

    @Test(timeout = TIMEOUT)
    public void RabinKarp() {
        SearchableString pattern = new SearchableString("banana");
        SearchableString text = new SearchableString("bababa babanana bababa babanana");

        List<Integer> answer = new ArrayList<>();
        answer.add(9);
        answer.add(25);
        assertEquals(answer, stringSearching.rabinKarp(pattern, text));
        assertTrue("text count was " + text.getCount()
                + ". Should be <= 68.", text.getCount() <= 68);
    }

    @Test(timeout = TIMEOUT)
    public void RabinKarp2() {
        SearchableString pattern = new SearchableString("th");
        SearchableString text = new SearchableString("the eighth thunder");

        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(8);
        answer.add(11);
        assertEquals(answer, stringSearching.rabinKarp(pattern, text));
        assertTrue("text count was " + text.getCount()
                + ". Should be <= 68.", text.getCount() <= 40);
    }

    @Test(timeout = TIMEOUT)
    public void RabinKarp3() {
        SearchableString pattern = new SearchableString("al");
        SearchableString text = new SearchableString("Runs Rabin-Karp algorithm. Generate initial hash, and compare " +
                "it with hash from substring of text same length as pattern. If the two hashes match compare their" +
                " individual characters, else update hash and continue.");

        List<Integer> answer = new ArrayList<>();
        answer.add(16);
        answer.add(41);
        answer.add(168);
        assertEquals(answer, stringSearching.rabinKarp(pattern, text));
        assertTrue("text count was " + text.getCount()
                + ". Should be <= 430.", text.getCount() <= 430);
    }

    @Test(timeout = TIMEOUT)
    public void emptyResultTest() {
        SearchableString pattern = new SearchableString(";");
        SearchableString text = new SearchableString("This is my first time making a junit test.");

        List<Integer> answer = new ArrayList<>();
        assertEquals(answer, stringSearching.kmp(pattern, text));
        assertEquals(answer, stringSearching.boyerMoore(pattern, text));
        assertEquals(answer, stringSearching.rabinKarp(pattern, text));
    }

    /**
     * This function raise a number to the power of another number
     * @param a the number
     * @param b the power
     * @return the result
     */
    private static int exp(int a, int b) {
        if (b == 0) {
            return 1;
        } else {
            int c = a;
            while (b > 1) {
                c *= a;
                b--;
            }
            return c;
        }
    }

}