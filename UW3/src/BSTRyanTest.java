/**
 * Created by kaijiehuang on 2/6/15.
 */

import org.junit.Before;
        import org.junit.Test;

        import java.util.*;

        import static junit.framework.Assert.assertEquals;
        import static junit.framework.Assert.assertTrue;

public class BSTRyanTest {

    private class Letter implements Comparable<Letter> {
        private char letter;
        public Letter(char letter) {
            this.letter = letter;
        }

        //TA said we are not guaranteed to have an equals() method

        public int compareTo(Letter compare) {
            int int1 = letter - 'a' + 1;
            int int2 = compare.letter - 'a' + 1;
            return int1 - int2;
        }
        public String toString() {return Character.toString(letter);}
    }

    private BST<Letter> bst;
    private Letter[] letters;

    @Before
    public void setup() {
        bst = new BST<>();
        letters = new Letter[26];
        for(int i = 0; i < 26; i++){
            letters[i] = new Letter( (char)(i + 'a'));
        }
        //[a b c d e f g h i j k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z ]
        //[0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25]
    }

    @Test
    public void testConstructor(){
        Collection<Letter> collection = new ArrayList<Letter>();


        collection.add(letters[13]);
        collection.add(letters[10]);
        collection.add(letters[22]);
        collection.add(letters[12]);
        collection.add(letters[0]);
        collection.add(letters[1]);
        BST<Letter> bst2 = new BST<Letter>(collection);

        assertEquals(6,bst2.size());
        assertTrue(bst2.contains(letters[13]));
        assertTrue(bst2.contains(letters[10]));
        assertTrue(bst2.contains(letters[22]));
        assertTrue(bst2.contains(letters[12]));
        assertTrue(bst2.contains(letters[0]));
        assertTrue(bst2.contains(letters[1]));
    }

    @Test
    public void testAddDuplicates(){
        bst.add(letters[6]);
        bst.add(letters[17]);
        bst.add(letters[0]);
        bst.add(letters[7]);
        bst.add(letters[25]);
        bst.add(letters[17]);
        bst.add(letters[7]);
        bst.add(letters[6]);

        assertEquals("Size of BST not correct: Check adding of duplicates",
                5,bst.size());
    }

    @Test
    public void testAddAndRemove(){
        Random rand = new Random();
        Letter current;
        //Creates a random tree of 26 letters
        //Tree will not be the same every time
        for(int i = 0;i<1000;i++){
            current = letters[rand.nextInt(26)];
            bst.add(current);
        }
        assertEquals(26,bst.size());

        for(int i = 0;i<26;i++){
            assertTrue(bst.contains(letters[i]));
            bst.remove(letters[i]);
            assertTrue(!bst.contains(letters[i]));
            assertEquals(25-i,bst.size());
        }

    }

    @Test
    public void testClear(){
        for(int i = 0; i<26; i++){
            bst.add(letters[i]);
        }
        assertEquals(26,bst.size());

        bst.clear();

        assertEquals("BST size not 0 after clear",0,bst.size());
        assertTrue("BST root not null after clear", bst.getRoot() == null);
    }

    @Test
    public void testRemoveRoot(){
        /*
                   13
               10      22
            0     12
              1
         */
        bst.add(letters[13]);
        bst.add(letters[10]);
        bst.add(letters[22]);
        bst.add(letters[12]);
        bst.add(letters[0]);
        bst.add(letters[1]);

        assertEquals(6,bst.size());

        Letter removed = bst.remove(letters[13]);
        assertEquals(removed,letters[13]);
        assertEquals(bst.getRoot().getData(),letters[22]);
        assertEquals(5,bst.size());

        removed = bst.remove(letters[22]);
        assertEquals(removed,letters[22]);
        assertEquals(bst.getRoot().getData(),letters[10]);
        assertEquals(4,bst.size());

        removed = bst.remove(letters[10]);
        assertEquals(removed,letters[10]);
        assertEquals(bst.getRoot().getData(),letters[12]);
        assertEquals(3,bst.size());

        removed = bst.remove(letters[12]);
        assertEquals(removed,letters[12]);
        assertEquals(bst.getRoot().getData(),letters[0]);
        assertEquals(2,bst.size());

        removed = bst.remove(letters[0]);
        assertEquals(removed,letters[0]);
        assertEquals(bst.getRoot().getData(),letters[1]);
        assertEquals(1,bst.size());

        removed = bst.remove(letters[1]);
        assertEquals(removed,letters[1]);
        assertTrue(bst.getRoot()==null);
        assertEquals(0,bst.size());
    }

    @Test
    public void testTraversalNull(){
        List<Letter> postList = bst.postorder();
        assertEquals(0,postList.size());

        List<Letter> preList = bst.preorder();
        assertEquals(0,preList.size());

        List<Letter> inList = bst.inorder();
        assertEquals(0,inList.size());

        List<Letter> levelList = bst.levelorder();
        assertEquals(0,levelList.size());
    }

    @Test(timeout = 200, expected = IllegalArgumentException.class)
    public void constructorNullException(){
        Collection<Letter> collection = new ArrayList<Letter>();
        collection.add(letters[5]);
        collection.add(letters[7]);
        collection.add(null);
        collection.add(letters[25]);
        BST<Letter> bst2 = new BST<Letter>(collection);
    }

}