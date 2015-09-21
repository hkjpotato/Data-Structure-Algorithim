import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AVLStudentTests {
    private AVL<MagicString> avlTree;

    @Before
    public void setup() {
        avlTree = new AVL<MagicString>();
    }

    @Test(timeout = 250)
    public void testAddLeftRotation() {
        avlTree.add(new MagicString("lima", 5));
        avlTree.add(new MagicString("hotel", 4));
        avlTree.add(new MagicString("echo", 3));

        assertEquals(3, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("hotel", 4), root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("echo", 3), root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("lima", 5), root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = 250)
    public void testAddRightLeftRotationRoot() {
        avlTree.add(new MagicString("echo", 3));
        avlTree.add(new MagicString("lima", 5));
        avlTree.add(new MagicString("hotel", 4));

        assertEquals(3, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("hotel", 4), root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("echo", 3), root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("lima", 5), root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = 250)
    public void testRemove() {
        avlTree.add(new MagicString("first", 646));
        avlTree.add(new MagicString("second", 386));
        avlTree.add(new MagicString("third", 856));
        avlTree.add(new MagicString("fourth", 526));
        avlTree.add(new MagicString("fifth", 477));
        avlTree.remove(new MagicString("fourth", 526));

        assertEquals(4, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("first", 646), root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getBalanceFactor());
        assertEquals(new MagicString("fifth", 477), root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(1, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("third", 856), root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
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
