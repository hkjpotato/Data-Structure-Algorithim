/**
 * Created by kaijiehuang on 15-4-3.
 */
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;

/**
 * Tests some stuff; won't test number of times charAt() is called
 * @author Nate G.
 * @version Ice Cream Sandwhich
 */

public class kDSortTest {
    private StringSearching ss;
    private List<Integer> answer;
    private SearchableString string;
    private SearchableString stringNotThere;
    private SearchableString text;

    private List<Integer> hardAnswer;
    private SearchableString hardString;
    private SearchableString hardText;

    private List<Integer> singleAnswer;
    private SearchableString singleString;
    private SearchableString singleText;
    private SearchableString singleTextNot;

    private List<Integer> kmpAnswer;
    private SearchableString kmpPattern;
    private SearchableString kmpText;
    private SearchableString kmpNotThere;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        ss = new StringSearching();

        string = new SearchableString("akbar");
        stringNotThere = new SearchableString("Take evasive action! Green group, stick close to holding section MV-7! It's a trap!");
        text = new SearchableString("akcakbe gak I'm Admiral Akbar, that's akbar, not akcbar or akbear.");
        answer = new ArrayList<>();
        answer.add(38);


        hardString = new SearchableString("abc");
        hardText = new SearchableString("abcabcaaaaabaaaaaabc");
        Integer[] hsa = {0, 3, 17};
        hardAnswer = java.util.Arrays.asList(hsa);


        singleString = new SearchableString("x"); //marks the spot
        singleText = new SearchableString("xysxx nxXzbwxqpc2zwuxxx");
        Integer[] sa = {0, 3, 4, 7, 12, 20, 21, 22};
        singleAnswer = java.util.Arrays.asList(sa);
        singleTextNot =
                new SearchableString("ak1jfoiXaoiea8wopgXijsdjsCfQZq");


        kmpPattern = new SearchableString("abba");
        kmpText = new SearchableString("abcabaabbabbabba");
        kmpNotThere = new SearchableString("abaabaaabbbc");
        kmpAnswer = new ArrayList<>();
        kmpAnswer.add(6);
        kmpAnswer.add(9);
        kmpAnswer.add(12);

    }

    @Test(timeout = TIMEOUT)
    public void testBM() {
        List<Integer> bm = ss.boyerMoore(string, text);
        assertEquals(answer, bm);

        bm.clear();
        bm = ss.boyerMoore(string, stringNotThere);
        assertEquals(bm, new ArrayList<Integer>());
    }

    @Test(timeout = TIMEOUT)
    public void testRK() {
        List<Integer> rk = ss.rabinKarp(string, text);
        assertEquals(answer, rk);

        rk.clear();
        rk = ss.rabinKarp(string, stringNotThere);
        assertEquals(rk, new ArrayList<Integer>());
    }

    @Test(timeout = TIMEOUT)
    public void testKnuthMorrisPratt() {
        List<Integer> kmp = ss.kmp(kmpPattern, kmpText);
        assertEquals(kmpAnswer, kmp);

        kmp.clear();
        kmp = ss.kmp(kmpPattern, kmpNotThere);
        assertEquals(kmp, new ArrayList<Integer>());
    }

    @Test(timeout = TIMEOUT)
    public void testBMSingle() {
        List<Integer> bm = ss.boyerMoore(singleString, singleText);
        assertEquals(singleAnswer, bm);

        bm.clear();
        bm = ss.boyerMoore(singleString, singleTextNot);
        assertEquals(bm, new ArrayList<Integer>());
    }

    @Test(timeout = TIMEOUT)
    public void testRKSingle() {
        List<Integer> rk = ss.rabinKarp(singleString, singleText);
        assertEquals(singleAnswer, rk);

        rk.clear();
        rk = ss.rabinKarp(singleString, singleTextNot);
        assertEquals(rk, new ArrayList<>());
    }

    @Test(timeout = TIMEOUT)
    public void testKMPSingle() {
        System.out.println("KMP_SINGLE");
        List<Integer> kmp = ss.kmp(singleString, singleText);
        assertEquals(singleAnswer, kmp);

        kmp.clear();
        kmp = ss.rabinKarp(singleString, singleTextNot);
        assertEquals(kmp, new ArrayList<>());
    }

    @Test(timeout = TIMEOUT)
    public void testHardBM() {
        List<Integer> bm = ss.boyerMoore(hardString, hardText);
        assertEquals(hardAnswer, bm);
    }

    @Test(timeout = TIMEOUT)
    public void testHardRK() {
        List<Integer> rk = ss.rabinKarp(hardString, hardText);
        assertEquals(hardAnswer, rk);
    }

    @Test(timeout = TIMEOUT)
    public void testHardKMP() {
        List<Integer> kmp = ss.kmp(hardString, hardText);
        assertEquals(hardAnswer, kmp);
    }
}