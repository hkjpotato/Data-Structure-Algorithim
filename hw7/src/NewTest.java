/**
 * Created by kaijiehuang on 15-3-13.
 */
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Test created by Timothy Lee-O'Connor
 *
 * @author Timothy
 * @version 1.0
 *
 * If you find an issue, reply to my piazza post or
 * send me an email at tjlo3@gatech.edu
 *
 *
 *
 */
public class NewTest {
    private AVL<MagicString> avlTree;

    public static final int TIMEOUT = Integer.MAX_VALUE;

    @Before
    public void setup() {
        avlTree = new AVL<MagicString>();
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addNull() {avlTree.add(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void removeNull() {avlTree.remove(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void getNull() {avlTree.get(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void containsNull() {avlTree.contains(null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void depthNull() {avlTree.depth(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void removeNoElement() {
        avlTree.add(new MagicString("lima", 5));
        avlTree.remove(new MagicString("fake", 4));
    }

    @Test(timeout = TIMEOUT)
    public void getElementExists() {
        avlTree.add(new MagicString("Bronco", 6));
        avlTree.add(new MagicString("Eagle", 7));
        avlTree.add(new MagicString("Truth", 5));

        assertTrue(new MagicString("Bronco", 6).equals(avlTree.get(new MagicString("Bronco", 6))));
        assertTrue(new MagicString("Bronco", 7).equals(avlTree.get(new MagicString("Bronco", 7))));
        assertTrue(new MagicString("Bronco", 5).equals(avlTree.get(new MagicString("Bronco", 5))));
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void getElementNotExists() {
        avlTree.add(new MagicString("Bronco", 6));
        avlTree.add(new MagicString("Eagle", 7));
        avlTree.add(new MagicString("Truth", 5));

        avlTree.get(new MagicString("Truth", 4));

    }

    @Test(timeout = TIMEOUT)
    public void containsElementNotExists() {
        avlTree.add(new MagicString("Bronco", 6));
        avlTree.add(new MagicString("Eagle", 7));
        avlTree.add(new MagicString("Truth", 5));

        assertFalse(avlTree.contains(new MagicString("Truth", 4)));
    }

    @Test(timeout = TIMEOUT)
    public void containsElementExists() {
        avlTree.add(new MagicString("Bronco", 6));
        avlTree.add(new MagicString("Eagle", 7));
        avlTree.add(new MagicString("Truth", 5));

        assertTrue(avlTree.contains(new MagicString("N/a", 6)));
        assertTrue(avlTree.contains(new MagicString("N/a", 7)));
        assertTrue(avlTree.contains(new MagicString("N/a", 5)));
    }

    @Test(timeout = TIMEOUT)
    public void depthExists() {
        avlTree.add(new MagicString("Bronco", 6));
        avlTree.add(new MagicString("Eagle", 7));
        avlTree.add(new MagicString("Truth", 5));

        assertEquals(1, avlTree.depth(new MagicString("N/a", 6)));
        assertEquals(2, avlTree.depth(new MagicString("N/a", 7)));
        assertEquals(2, avlTree.depth(new MagicString("N/a", 5)));
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void depthNotExists() {
        avlTree.add(new MagicString("Bronco", 6));
        avlTree.add(new MagicString("Eagle", 7));
        avlTree.add(new MagicString("Truth", 5));

        avlTree.depth(new MagicString("N/A", 4));
    }

    @Test(timeout = TIMEOUT)
    public void heightExists() {
        avlTree.add(new MagicString("Bronco", 6));

        assertEquals(0, avlTree.height());

        avlTree.add(new MagicString("Eagle", 7));
        avlTree.add(new MagicString("Truth", 5));

        assertEquals(1, avlTree.height());
    }

    @Test(timeout = TIMEOUT)
    public void heightEmptyTree() {
        assertEquals(-1, avlTree.height());
    }

    @Test(timeout = TIMEOUT)
    public void preOrder() {
        avlTree.add(new MagicString("Bronco", 6));
        avlTree.add(new MagicString("Eagle", 8));
        avlTree.add(new MagicString("Truth", 4));
        avlTree.add(new MagicString("New York",5));
        avlTree.add(new MagicString("Jersey", 7));
        List<MagicString> preOrder = new ArrayList<MagicString>();
        preOrder.add(new MagicString("Bronco", 6));
        preOrder.add(new MagicString("Eagle", 4));
        preOrder.add(new MagicString("Truth", 5));
        preOrder.add(new MagicString("New York",8));
        preOrder.add(new MagicString("Jersey", 7));
        assertEquals(avlTree.preorder(), preOrder);
    }

    @Test(timeout = TIMEOUT)
    public void postOrder() {
        avlTree.add(new MagicString("Bronco", 6));
        avlTree.add(new MagicString("Eagle", 8));
        avlTree.add(new MagicString("Truth", 4));
        avlTree.add(new MagicString("New York",5));
        avlTree.add(new MagicString("Jersey", 7));
        List<MagicString> postOrder = new ArrayList<MagicString>();
        postOrder.add(new MagicString("Bronco", 5));
        postOrder.add(new MagicString("Eagle", 4));
        postOrder.add(new MagicString("Truth", 7));
        postOrder.add(new MagicString("New York",8));
        postOrder.add(new MagicString("Jersey", 6));
        assertEquals(avlTree.postorder(), postOrder);
    }

    @Test(timeout = TIMEOUT)
    public void inOrder() {
        avlTree.add(new MagicString("Bronco", 6));
        avlTree.add(new MagicString("Eagle", 8));
        avlTree.add(new MagicString("Truth", 4));
        avlTree.add(new MagicString("New York",5));
        avlTree.add(new MagicString("Jersey", 7));
        List<MagicString> inOrder = new ArrayList<MagicString>();
        inOrder.add(new MagicString("Bronco", 4));
        inOrder.add(new MagicString("Eagle", 5));
        inOrder.add(new MagicString("Truth", 6));
        inOrder.add(new MagicString("New York",7));
        inOrder.add(new MagicString("Jersey", 8));
        assertEquals(avlTree.inorder(), inOrder);
    }

    @Test(timeout = TIMEOUT)
    public void levelOrder() {
        avlTree.add(new MagicString("Bronco", 6));
        avlTree.add(new MagicString("Eagle", 8));
        avlTree.add(new MagicString("Truth", 4));
        avlTree.add(new MagicString("New York",5));
        avlTree.add(new MagicString("Jersey", 7));
        List<MagicString> levelOrder = new ArrayList<MagicString>();
        levelOrder.add(new MagicString("Bronco", 6));
        levelOrder.add(new MagicString("Eagle", 4));
        levelOrder.add(new MagicString("Truth", 8));
        levelOrder.add(new MagicString("New York",5));
        levelOrder.add(new MagicString("Jersey", 7));
        assertEquals(avlTree.levelorder(), levelOrder);
    }

    @Test(timeout = TIMEOUT)
    public void preOrderEmptyTree() {
        assertEquals(0, avlTree.preorder().size());
    }

    @Test(timeout = TIMEOUT)
    public void postOrderEmptyTree() {
        assertEquals(0, avlTree.postorder().size());
    }

    @Test(timeout = TIMEOUT)
    public void inOrderEmptyTree() {
        assertEquals(0, avlTree.inorder().size());
    }

    @Test(timeout = TIMEOUT)
    public void levelOrderEmptyTree() {
        assertEquals(0, avlTree.levelorder().size());
    }

    @Test(timeout = TIMEOUT)
    public void clear() {
        avlTree.add(new MagicString("Bronco", 6));
        avlTree.add(new MagicString("Eagle", 8));
        avlTree.add(new MagicString("Truth", 4));
        avlTree.add(new MagicString("New York",5));
        avlTree.add(new MagicString("Jersey", 7));

        assertEquals(5, avlTree.size());
        assertEquals(avlTree.getRoot().getData(), new MagicString("N/A", 6));

        avlTree.clear();

        assertEquals(0, avlTree.size());
        assertEquals(avlTree.getRoot(), null);

    }

    private class MagicString implements Comparable<MagicString> {
        private final String magicString;
        private final int number;

        /**
         * Create a MagicString.
         *
         * @param magicString random string to store
         * @param number number to store
         */
        public MagicString(String magicString, int number) {
            this.magicString = magicString;
            this.number = number;
        }

        @Override
        public int hashCode() {
            return number;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (!(other instanceof MagicString)) {
                return false;
            }
            MagicString that = (MagicString) other;
            return that.number == number;
        }

        @Override
        public int compareTo(MagicString other) {
            return number - other.number;
        }

        @Override
        public String toString() {
            return "MagicString: " + magicString + ", " + number;
        }
    }

}