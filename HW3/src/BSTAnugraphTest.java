/**
 * Created by kaijiehuang on 2/9/15.
 */
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertNull;

// Version 1.2 - more tests + timeouts included
public class BSTAnugraphTest {
    private BST<Integer> bst;

    @Before
    public void setup() {
        bst = new BST<Integer>();
    }

    private void makeTree() {
        // 9 elements
        bst.add(8);
        bst.add(3);
        bst.add(10);
        bst.add(1);
        bst.add(6);
        bst.add(14);
        bst.add(4);
        bst.add(7);
        bst.add(13);
    }

    @Test (timeout = 200)
    public void testAddNormal() {
        // setup
        makeTree();

        // check size
        assertEquals(9, bst.size());

        // check structure
        assertEquals((Object) 8, bst.getRoot().getData());
        assertEquals((Object) 3, bst.getRoot().getLeft().getData());
        assertEquals((Object) 10, bst.getRoot().getRight().getData());

        assertEquals((Object) 6, bst.getRoot().getLeft().getRight().getData());
        assertEquals((Object) 1, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Object) 14, bst.getRoot().getRight().getRight().getData());

        assertEquals((Object) 13, bst.getRoot().getRight().getRight().getLeft().getData());
        assertEquals((Object) 4, bst.getRoot().getLeft().getRight().getLeft().getData());
        assertEquals((Object) 7, bst.getRoot().getLeft().getRight().getRight().getData());
    }

    @Test(timeout = 200)
    public void testAddDuplicate() {
        // setup
        makeTree();

        // add duplicates
        bst.add(bst.getRoot().getData());
        bst.add(bst.getRoot().getLeft().getData());
        bst.add(bst.getRoot().getRight().getData());

        // check size
        assertEquals(9, bst.size());

        // check structure
        assertEquals((Object) 8, bst.getRoot().getData());
        assertEquals((Object) 3, bst.getRoot().getLeft().getData());
        assertEquals((Object) 10, bst.getRoot().getRight().getData());

        assertEquals((Object) 6, bst.getRoot().getLeft().getRight().getData());
        assertEquals((Object) 1, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Object) 14, bst.getRoot().getRight().getRight().getData());

        assertEquals((Object) 13, bst.getRoot().getRight().getRight().getLeft().getData());
        assertEquals((Object) 4, bst.getRoot().getLeft().getRight().getLeft().getData());
        assertEquals((Object) 7, bst.getRoot().getLeft().getRight().getRight().getData());

    }

    @Test (timeout = 200, expected = IllegalArgumentException.class)
    public void testAddNullData() {
        // setup
        makeTree();

        // Add Null
        bst.add(null);

        // check size
        assertEquals(9, bst.size());

        // check structure
        assertEquals((Object) 8, bst.getRoot().getData());
        assertEquals((Object) 3, bst.getRoot().getLeft().getData());
        assertEquals((Object) 10, bst.getRoot().getRight().getData());

        assertEquals((Object) 6, bst.getRoot().getLeft().getRight().getData());
        assertEquals((Object) 1, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Object) 14, bst.getRoot().getRight().getRight().getData());

        assertEquals((Object) 13, bst.getRoot().getRight().getRight().getLeft().getData());
        assertEquals((Object) 4, bst.getRoot().getLeft().getRight().getLeft().getData());
        assertEquals((Object) 7, bst.getRoot().getLeft().getRight().getRight().getData());

    }

    @Test (timeout = 200)
    public void testRemoveNoChild() {
        // setup
        makeTree();
        assertEquals(9, bst.size());

        // remove
        assertEquals((Object) 1, bst.remove(1));
        assertEquals((Object) 4, bst.remove(4));
        assertEquals((Object) 7, bst.remove(7));
        assertEquals((Object) 13, bst.remove(13));

        // check size
        assertEquals(5, bst.size());

        // check structure
        assertEquals((Object) 8, bst.getRoot().getData());
        assertEquals((Object) 3, bst.getRoot().getLeft().getData());
        assertEquals((Object) 10, bst.getRoot().getRight().getData());

        assertEquals((Object) 6, bst.getRoot().getLeft().getRight().getData());
        assertEquals((Object) 14, bst.getRoot().getRight().getRight().getData());

        assertNull("Location of node 1 should be null after removal", bst.getRoot().getLeft().getLeft());
        assertNull("Location of node 13 should be null after removal",bst.getRoot().getRight().getRight().getLeft());
        assertNull("Location of node 4 should be null after removal",bst.getRoot().getLeft().getRight().getLeft());
        assertNull("Location of node 7 should be null after removal",bst.getRoot().getLeft().getRight().getRight());
    }

    @Test (timeout = 200)
    public void testRemoveOneChild() {
        // setup
        makeTree();
        assertEquals(9, bst.size());

        // removing node with only left child
        bst.remove(14);

        // check size
        assertEquals(8, bst.size());

        // check structure
        assertEquals((Object) 8, bst.getRoot().getData());
        assertEquals((Object) 3, bst.getRoot().getLeft().getData());
        assertEquals((Object) 10, bst.getRoot().getRight().getData());

        assertEquals((Object) 6, bst.getRoot().getLeft().getRight().getData());
        assertEquals((Object) 1, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Object) 13, bst.getRoot().getRight().getRight().getData());

        assertNull("Previous location of node 13 should be null after node 14 is removed", bst.getRoot().getRight().getRight().getLeft());
        assertEquals((Object) 4, bst.getRoot().getLeft().getRight().getLeft().getData());
        assertEquals((Object) 7, bst.getRoot().getLeft().getRight().getRight().getData());

        // removing node with only right child
        bst.remove(10);

        // check size
        assertEquals(7, bst.size());

        // check structure
        assertEquals((Object) 8, bst.getRoot().getData());
        assertEquals((Object) 3, bst.getRoot().getLeft().getData());
        assertEquals((Object) 13, bst.getRoot().getRight().getData());

        assertEquals((Object) 6, bst.getRoot().getLeft().getRight().getData());
        assertEquals((Object) 1, bst.getRoot().getLeft().getLeft().getData());
        assertNull("Previous location of node 13 should be null after node 10 is removed", bst.getRoot().getRight().getRight());

        assertEquals((Object) 4, bst.getRoot().getLeft().getRight().getLeft().getData());
        assertEquals((Object) 7, bst.getRoot().getLeft().getRight().getRight().getData());
    }

    @Test (timeout = 200)
    public void testRemoveTwoChildren() {
        // setup
        makeTree();
        assertEquals(9, bst.size());

        // remove
        bst.remove(3);

        // check size
        assertEquals(8, bst.size());

        // check structure
        assertEquals((Object) 8, bst.getRoot().getData());
        assertEquals((Object) 4, bst.getRoot().getLeft().getData());
        assertEquals((Object) 10, bst.getRoot().getRight().getData());

        assertEquals((Object) 6, bst.getRoot().getLeft().getRight().getData());
        assertEquals((Object) 1, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Object) 14, bst.getRoot().getRight().getRight().getData());

        assertNull("Previous location of node 14 should be null", bst.getRoot().getLeft().getRight().getLeft());
        assertEquals((Object) 7, bst.getRoot().getLeft().getRight().getRight().getData());
        assertEquals((Object) 13, bst.getRoot().getRight().getRight().getLeft().getData());
    }

    @Test (timeout = 200, expected = IllegalArgumentException.class)
    public void testRemoveNullData() {
        // removing with input null
        makeTree();
        bst.remove(null);

    }

    @Test (timeout = 200, expected = NoSuchElementException.class)
    public void testRemoveNoSuchElement() {
        // removing element which is not in BST
        makeTree();
        bst.remove(100);

    }

    @Test (timeout = 200, expected = NoSuchElementException.class)
    public void testRemoveEmpty() {
        // removing from empty BST
        bst.remove(5);
    }

    @Test (timeout = 200)
    public void testRemoveRootNoChildren() {
        // setup
        bst.add(1);

        // check size
        assertEquals((Object) 1, bst.size());

        // removing root when root has no children
        bst.remove(1);
        assertEquals((Object) 0, bst.size());
        assertNull("Root should be null", bst.getRoot());
    }

    @Test (timeout = 200)
    public void testRemoveRootOneChild() {
        // setup
        bst.add(2);
        bst.add(4);
        bst.add(3);

        // check size
        assertEquals((Object) 3, bst.size());

        // removing root when root has one child
        bst.remove(2);
        assertEquals((Object) 2, bst.size());
        assertEquals((Object) 4, bst.getRoot().getData());
        assertEquals((Object) 3, bst.getRoot().getLeft().getData());
        assertNull("The right child of node 14 should be null", bst.getRoot().getRight());


        // setup
        bst = new BST<Integer>();
        bst.add(2);
        bst.add(4);
        bst.add(5);

        // check size
        assertEquals((Object) 3, bst.size());

        // removing root when root has one child
        bst.remove(2);
        assertEquals((Object) 2, bst.size());
        assertEquals((Object) 4, bst.getRoot().getData());
        assertEquals((Object) 5, bst.getRoot().getRight().getData());
        assertNull("The left child of node 14 should be null", bst.getRoot().getLeft());
    }

    @Test (timeout = 200)
    public void testRemoveRootTwoChildren() {
        // setup
        makeTree();

        // check size
        assertEquals(9, bst.size());

        // removing root when root has two children
        bst.remove(8);
        assertEquals((Object) 8, bst.size());
        assertEquals((Object) 10, bst.getRoot().getData());
        assertEquals((Object) 14, bst.getRoot().getRight().getData());
    }

    @Test (timeout = 200)
    public void testRemoveHard() {
        // this is the case when you have to remove a node with 2 children
        // so you go to the next highest node using the successor method
        // but that node may also have a right child.

        // setup
        makeTree();
        bst.add(5);
        assertEquals(10, bst.size());

        // remove
        bst.remove(3);

        // check size
        assertEquals(9, bst.size());

        // check structure
        assertEquals((Object) 8, bst.getRoot().getData());
        assertEquals((Object) 4, bst.getRoot().getLeft().getData());
        assertEquals((Object) 10, bst.getRoot().getRight().getData());

        assertEquals((Object) 6, bst.getRoot().getLeft().getRight().getData());
        assertEquals((Object) 1, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Object) 14, bst.getRoot().getRight().getRight().getData());

        assertEquals((Object) 5, bst.getRoot().getLeft().getRight().getLeft().getData());
        assertEquals((Object) 7, bst.getRoot().getLeft().getRight().getRight().getData());
        assertEquals((Object) 13, bst.getRoot().getRight().getRight().getLeft().getData());

    }

    @Test (timeout = 200)
    public void testAddRemove() {
        Queue<Integer> queue = new LinkedList<Integer>();
        Random rand = new Random();
        int counter = 0;
        int current;

        // adding random elements
        while (counter < 5) {
            current = rand.nextInt(101);
            if (!queue.contains(current)) {
                queue.add(current);
                bst.add(current);
                counter++;
            }
        }

        counter = bst.size();

        // checking removing the 100 elements
        while (queue.size() != 0) {
            int remove = queue.poll();
            assertTrue(bst.contains(remove));
            bst.remove(remove);
            counter--;
            assertFalse(bst.contains(remove));
            assertEquals(counter, bst.size());
        }
    }

    @Test(timeout = 200, expected = NoSuchElementException.class)
    public void testGetEmpty() {
        // getting data from empty BST
        bst.get(20);
    }

    @Test(timeout = 200, expected = IllegalArgumentException.class)
    public void testGetNullData() {
        // getting null data
        bst.get(null);
    }
    @Test(timeout = 200, expected = NoSuchElementException.class)
    public void testGetNoSuchElement() {
        // getting data not present in BST
        makeTree();
        bst.get(-1);
    }

    @Test (timeout = 200)
    public void testGet() {
        // setup
        makeTree();

        // check size
        assertEquals(9, bst.size());

        // checking get of every item in BST
        assertTrue(bst.contains(8));
        assertEquals((Object) 8, bst.get(8));
        assertTrue(bst.contains(3));
        assertEquals((Object) 3, bst.get(3));
        assertTrue(bst.contains(10));
        assertEquals((Object) 10, bst.get(10));
        assertTrue(bst.contains(1));
        assertEquals((Object) 1, bst.get(1));
        assertTrue(bst.contains(6));
        assertEquals((Object) 6, bst.get(6));
        assertTrue(bst.contains(14));
        assertEquals((Object) 14, bst.get(14));
        assertTrue(bst.contains(4));
        assertEquals((Object) 4, bst.get(4));
        assertTrue(bst.contains(7));
        assertEquals((Object) 7, bst.get(7));
        assertTrue(bst.contains(13));
        assertEquals((Object) 13, bst.get(13));
    }

    @Test (timeout = 200)
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

    // Test contains regular - value in tree and value not in tree
    // Test contains null
    // Test contains empty BST

    @Test (timeout = 200)
    public void testContains() {
        // setup
        makeTree();

        // check if all elements present return true
        assertTrue(bst.contains(8));
        assertTrue(bst.contains(3));
        assertTrue(bst.contains(10));
        assertTrue(bst.contains(14));
        assertTrue(bst.contains(13));
        assertTrue(bst.contains(7));
        assertTrue(bst.contains(6));
        assertTrue(bst.contains(4));

        // elements not present should return false
        assertFalse(bst.contains(100));
        assertFalse(bst.contains(-5));

        // Now add them
        bst.add(100);
        bst.add(-5);

        // now check contains
        assertTrue(bst.contains(100));
        assertTrue(bst.contains(-5));
    }

    @Test (timeout = 200, expected = IllegalArgumentException.class)
    public void testContainsNullData() {
        makeTree();
        assertTrue(bst.contains(null));
    }

    @Test (timeout = 200)
    public void testContainsEmpty() {
        assertFalse(bst.contains(5));
    }

    @Test (timeout = 200)
    public void testPreOrder() {
        // setup
        makeTree();

        // actual order
        List<Integer> preorder = new ArrayList<Integer>();
        preorder.add(8);
        preorder.add(3);
        preorder.add(1);
        preorder.add(6);
        preorder.add(4);
        preorder.add(7);
        preorder.add(10);
        preorder.add(14);
        preorder.add(13);

        // Testing
        assertEquals(preorder, bst.preorder());
    }

    @Test (timeout = 200)
    public void testPostOrder() {
        // setup
        makeTree();

        // actual order
        List<Integer> postOrder = new ArrayList<Integer>();
        postOrder.add(1);
        postOrder.add(4);
        postOrder.add(7);
        postOrder.add(6);
        postOrder.add(3);
        postOrder.add(13);
        postOrder.add(14);
        postOrder.add(10);
        postOrder.add(8);

        // Testing
        assertEquals(postOrder, bst.postorder());
    }

    @Test (timeout = 200)
    public void testInOrder() {
        // setup
        makeTree();

        // actual order
        List<Integer> inOrder = new ArrayList<Integer>();
        inOrder.add(1);
        inOrder.add(3);
        inOrder.add(4);
        inOrder.add(6);
        inOrder.add(7);
        inOrder.add(8);
        inOrder.add(10);
        inOrder.add(13);
        inOrder.add(14);

        // Testing
        assertEquals(inOrder, bst.inorder());
    }

    @Test (timeout = 200)
    public void testLevelOrder() {
        // setup
        makeTree();

        // actual order
        List<Integer> levelOrder = new ArrayList<Integer>();
        levelOrder.add(8);
        levelOrder.add(3);
        levelOrder.add(10);
        levelOrder.add(1);
        levelOrder.add(6);
        levelOrder.add(14);
        levelOrder.add(4);
        levelOrder.add(7);
        levelOrder.add(13);

        // Testing
        assertEquals(levelOrder, bst.levelorder());
    }

    @Test (timeout = 200)
    public void testTraversalEmpty() {
        // setup
        bst = new BST<Integer>();

        // testing
        List<Integer> preOrder = new ArrayList<Integer>();
        assertEquals(preOrder, bst.preorder());
        assertEquals(0,preOrder.size());

        // testing
        List<Integer> postOrder = new ArrayList<Integer>();
        assertEquals(postOrder, bst.postorder());
        assertEquals(0,postOrder.size());

        // testing
        List<Integer> inOrder = new ArrayList<Integer>();
        assertEquals(inOrder, bst.inorder());
        assertEquals(0,inOrder.size());

        // testing
        List<Integer> levelOrder = new ArrayList<Integer>();
        assertEquals(levelOrder, bst.levelorder());
        assertEquals(0,levelOrder.size());
    }

    @Test (timeout = 200)
    public void testClear() {
        // setup
        makeTree();

        // testing
        bst.clear();
        // check size
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());

        // re-using BST
        makeTree();

        // check size
        assertEquals(9, bst.size());

        // check structure
        assertEquals((Object) 8, bst.getRoot().getData());
        assertEquals((Object) 3, bst.getRoot().getLeft().getData());
        assertEquals((Object) 10, bst.getRoot().getRight().getData());

        assertEquals((Object) 6, bst.getRoot().getLeft().getRight().getData());
        assertEquals((Object) 1, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Object) 14, bst.getRoot().getRight().getRight().getData());

        assertEquals((Object) 13, bst.getRoot().getRight().getRight().getLeft().getData());
        assertEquals((Object) 4, bst.getRoot().getLeft().getRight().getLeft().getData());
        assertEquals((Object) 7, bst.getRoot().getLeft().getRight().getRight().getData());
    }

    @Test (timeout = 200)
    public void testHeight() {
        // setup
        makeTree();

        // checking usual height
        assertEquals((Object) 3, bst.height());

        // removing all bottom level leafs
        bst.remove(4);
        bst.remove(7);
        bst.remove(13);

        // height should be one less
        assertEquals((Object) 2, bst.height());

        // height of empty
        bst.clear();
        assertEquals((Object)(-1) , bst.height());

    }

    @Test (timeout = 200)
    public void testDepth() {
        // setup
        makeTree();

        // testing
        assertEquals((Object) 1, bst.depth(8));
        assertEquals((Object) 2, bst.depth(3));
        assertEquals((Object) 2, bst.depth(10));
        assertEquals((Object) 3, bst.depth(1));
        assertEquals((Object) 3, bst.depth(6));
        assertEquals((Object) 3, bst.depth(14));
        assertEquals((Object) 4, bst.depth(4));
        assertEquals((Object) 4, bst.depth(7));
        assertEquals((Object) 4, bst.depth(13));

        // depth of element not in BST
        assertEquals((Object) (-1), bst.depth(100));

        // depth of empty tree
        bst.clear();
        assertEquals((Object) (-1), bst.depth(100));
    }

    @Test(timeout = 200, expected = IllegalArgumentException.class)
    public void testDepthOfNull() {
        bst.depth(null);
    }

    @Test (timeout = 200)
    public void testConstructorCollection() {
        // setup
        Collection<Integer> numbers = new LinkedList<Integer>();
        numbers.add(8);
        numbers.add(3);
        numbers.add(10);
        numbers.add(1);
        numbers.add(6);
        numbers.add(14);
        numbers.add(4);
        numbers.add(7);
        numbers.add(13);

        bst = new BST<Integer>(numbers);

        // check size
        assertEquals(9, bst.size());

        // check structure
        assertEquals((Object) 8, bst.getRoot().getData());
        assertEquals((Object) 3, bst.getRoot().getLeft().getData());
        assertEquals((Object) 10, bst.getRoot().getRight().getData());

        assertEquals((Object) 6, bst.getRoot().getLeft().getRight().getData());
        assertEquals((Object) 1, bst.getRoot().getLeft().getLeft().getData());
        assertEquals((Object) 14, bst.getRoot().getRight().getRight().getData());

        assertEquals((Object) 13, bst.getRoot().getRight().getRight().getLeft().getData());
        assertEquals((Object) 4, bst.getRoot().getLeft().getRight().getLeft().getData());
        assertEquals((Object) 7, bst.getRoot().getLeft().getRight().getRight().getData());
    }

    @Test(timeout = 200, expected = IllegalArgumentException.class)
    public void testConstructorNull1() {
        bst = new BST<Integer>(null);
    }

    @Test (timeout = 200, expected = IllegalArgumentException.class)
    public void testConstructorCollectionNull2() {
        // setup
        Collection<Integer> numbers = new LinkedList<Integer>();
        numbers.add(8);
        numbers.add(null);
        numbers.add(10);
        numbers.add(1);
        numbers.add(null);
        numbers.add(14);
        numbers.add(4);
        numbers.add(null);
        numbers.add(13);

        bst = new BST<Integer>(numbers);
    }

}