import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

// Version 1.1 - more tests
public class BSTStudentTest {
    private BST<Integer> bst;

    @Before
    public void setup() {
        bst = new BST<Integer>();
    }

    @Test
    public void testAdd() {
        assertEquals(0, bst.size());

        bst.add(2);
        bst.add(1);
        bst.add(3);

        assertEquals(3, bst.size());
        assertEquals((Object) 2, bst.getRoot().getData());
        assertEquals((Object) 1, bst.getRoot().getLeft().getData());
        assertEquals((Object) 3, bst.getRoot().getRight().getData());
    }

    @Test
    public void testRemove() {
        assertEquals(0, bst.size());

        bst.add(2);
        bst.add(1);
        bst.add(3);
        bst.add(4);
        bst.add(5);

        assertEquals((Object) 3, bst.remove(3));
        assertEquals(4, bst.size());
        assertEquals((Object) 2, bst.getRoot().getData());
        assertEquals((Object) 1, bst.getRoot().getLeft().getData());
        assertEquals((Object) 4, bst.getRoot().getRight().getData());
        assertEquals((Object) 5, bst.getRoot().getRight().getRight().getData());
    }

    @Test
    public void testGet() {
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);

        assertTrue(bst.contains(58));
        assertEquals((Object) 58, bst.get(58));
        assertTrue(bst.contains(12));
        assertEquals((Object) 12, bst.get(12));
        assertTrue(bst.contains(94));
        assertEquals((Object) 94, bst.get(94));
        assertTrue(bst.contains(24));
        assertEquals((Object) 24, bst.get(24));
    }

    @Test
    public void testGetObject() {
        Integer testInt = new Integer(12);
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(testInt);
        bst.add(94);

        assertTrue("Object from tree is not being returned",
                testInt == bst.get(new Integer(12)));
    }

    @Test
    public void testPreorder() {
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> preorder = new ArrayList<>();
        preorder.add(24);
        preorder.add(1);
        preorder.add(7);
        preorder.add(12);
        preorder.add(94);
        preorder.add(58);
        preorder.add(73);
        preorder.add(68);
        preorder.add(77);

        assertEquals(preorder, bst.preorder());
    }

    @Test
    public void testPostorder() {
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> postorder = new ArrayList<>();
        postorder.add(12);
        postorder.add(7);
        postorder.add(1);
        postorder.add(68);
        postorder.add(77);
        postorder.add(73);
        postorder.add(58);
        postorder.add(94);
        postorder.add(24);

        assertEquals(postorder, bst.postorder());
    }

    @Test
    public void testLevelorder() {
        bst.add(24);
        bst.add(1);
        bst.add(7);
        bst.add(12);
        bst.add(94);
        bst.add(58);
        bst.add(73);
        bst.add(77);
        bst.add(68);

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(24);
        levelorder.add(1);
        levelorder.add(94);
        levelorder.add(7);
        levelorder.add(58);
        levelorder.add(12);
        levelorder.add(73);
        levelorder.add(68);
        levelorder.add(77);

        assertEquals(levelorder, bst.levelorder());
    }

    @Test(timeout = 200, expected = IllegalArgumentException.class)
    public void addExpectException() {
        bst.add(null);
    }

    @Test(timeout = 200, expected = IllegalArgumentException.class)
    public void removeExpectException() {
        bst.remove(null);
    }

    @Test(timeout = 200, expected = NoSuchElementException.class)
    public void removeNoSuchElement() {
        bst.remove(5);
    }

    @Test(timeout = 200, expected = NoSuchElementException.class)
    public void getEmpty() {
        bst.get(20);
    }

    @Test(timeout = 200, expected = IllegalArgumentException.class)
    public void getExpectException() {
        bst.get(null);
    }
    @Test(timeout = 200, expected = NoSuchElementException.class)
    public void getNoSuchElement() {
        bst.get(-1);
    }

    @Test(timeout = 200, expected = IllegalArgumentException.class)
    public void depthOfNull() {
        bst.depth(null);
    }

    @Test(timeout = 200, expected = IllegalArgumentException.class)
    public void constructorException() {
        bst = new BST<Integer>(null);
    }

    @Test(timeout = 200, expected = IllegalArgumentException.class)
    public void containsExpectException() {
        bst.contains(null);
    }


}
