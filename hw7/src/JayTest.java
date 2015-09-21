import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Jay's Tests!
 *
 * @author Jay Kamat
 * @version 0.9.1
 *
 * Jay's Tests are distributed in the hope that they will be useful,
 * but WITHOUT ANY GUARANTEES; without even the implied guarantee of
 * BEING ON PIAZZA UNDER THE JUNIT NOTE.  See the
 * GNU General Public License (v3) for more details.
 *
 * These tests are a derivative work of the TA's tests.
 *
 * MOTD: This homework is too hard. Every time.
 *
 * These tests test restructures on additions and deletions. You probably
 * want to make sure you're checking for nulls everywhere and all, but I'm
 * wayyyy to lazy to put that into my tests.
 *
 * THESE ARE NOT A SUBSTITUTE FOR NORMAL BST TESTS. THESE ARE ONLY AVL TESTS.
 * You probably want to be sure your tree does normal BST tree things as well,
 * like adding properly and stuff.
 *
 * IF YOU FIND AN ISSUE WITH THIS TEST PLEASE SUBMIT A FIX IN THE FORM OF
 * A .PATCH FILE ON PIAZZA. THANK YOU.
 *
 */
public class JayTest {
    private AVL<MagicString> avlTree;

    // do you even debug? I do, and therefore, timeouts are annoying
    public static final int TIMEOUT = Integer.MAX_VALUE;

    private Collection<Integer> testList;

    @Before
    public void setup() {
        // the copy pasta is strong with these tests.
        avlTree = new AVL<>();
        testList = Arrays.asList(10, 0, 8, 4, 20, 13, 10, 1, 9, 13, 16, 12, 3, 6, 12, 11, 18, 18, 12);
    }

    /**
     * This method is private!
     * and it does what the name says!
     */
    private AVL<Integer> createAVLForBasicTests() {
        AVL<Integer> toReturn = new AVL<>();

        // I pulled these numbers from the internet and they are random
        // 10 0 8 4 20 13 10 1 9 13 16 12 3 6 12 11 18 18 12 1
        // I create a master list in the constructor above.

        testList.stream().forEach(toReturn::add);

        return toReturn;
    }
 
    /*
   --------------------------------------
   THE FOLLOWING 4 TESTS TEST THE 4 BASIC
   CASES FOR AVL TREES, LEFT LEFT, LEFT RIGHT,
   RIGHT LEFT, AND RIGHT RIGHT.
   --------------------------------------
     */

    /**
     * Copied without shame from the TA's
     */
    @Test(timeout = TIMEOUT)
    public void testLeftLeftRotationRoot() {
        avlTree.add(new MagicString("lima", 5));
        avlTree.add(new MagicString("hotel", 4));
        avlTree.add(new MagicString("echo", 3));

        assertEquals(3, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 4), root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 5), root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testLeftRightRotationRoot() {
        avlTree.add(new MagicString("vim", 5));
        avlTree.add(new MagicString("nano", 3));
        avlTree.add(new MagicString("emacs", 4));

        // vim > emacs >>>>> nano

        assertEquals(3, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 4), root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 5), root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRightRightRotationRoot() {
        avlTree.add(new MagicString("java", 3));
        avlTree.add(new MagicString("viml", 4));
        avlTree.add(new MagicString("bash", 5));

        // I like bash scripting ok...

        assertEquals(3, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 4), root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 5), root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRightLeftRotationRoot() {
        avlTree.add(new MagicString("kingdom", 3));
        avlTree.add(new MagicString("idk the next one", 5));
        avlTree.add(new MagicString("phylum", 4));

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
 
 
    /*
   --------------------------------------
   THE FOLLOWING 4 TESTS TEST THE 4 BASIC
   CASES FOR AVL TREES, LEFT LEFT, LEFT RIGHT,
   RIGHT LEFT, AND RIGHT RIGHT, BUT THIS TIME
   I'M GOING TO PUT THE CHANGE BELOW THE ROOT.
 
   btw, the first two and second two tests
   have the same ouptut tree!
   --------------------------------------
     */


    @Test(timeout = TIMEOUT)
    public void testLeftLeftRotationInside() {
 
        /*
                    5
                   / \
                3     10
               / \    /
              1   4   9
                     /
                    8 <-- adding this should make the 9 go below the 5
         */

        avlTree.add(new MagicString("alpha", 5));
        avlTree.add(new MagicString("beta", 3));
        avlTree.add(new MagicString("gamma", 10));
        avlTree.add(new MagicString("delta", 4));
        avlTree.add(new MagicString("epsilon", 1));

        // finished building the left side

        // the right side is supposed to trip things up!
        avlTree.add(new MagicString("zeta", 9));
        avlTree.add(new MagicString("eta", 8));

        assertEquals(7, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 5), root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 1), root.getLeft().getLeft().getData());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 4), root.getLeft().getRight().getData());
        assertEquals(0, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

        // did you even rebalance?
        assertEquals(new MagicString("N/a", 9), root.getRight().getData());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 8), root.getRight().getLeft().getData());
        assertEquals(0, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 10), root.getRight().getRight().getData());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testLeftRightRotationInside() {
        /*
                    5
                   / \
                3     10
               / \    /
              1   4  8
                       \
                        9 <-- adding this should make the 9 go below the 5
         */

        avlTree.add(new MagicString("alpha", 5));
        avlTree.add(new MagicString("beta", 3));
        avlTree.add(new MagicString("gamma", 10));
        avlTree.add(new MagicString("delta", 4));
        avlTree.add(new MagicString("epsilon", 1));

        // finished building the left side

        // the right side is supposed to trip things up!
        avlTree.add(new MagicString("zeta", 8));
        avlTree.add(new MagicString("eta", 9));

        assertEquals(7, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 5), root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 1), root.getLeft().getLeft().getData());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 4), root.getLeft().getRight().getData());
        assertEquals(0, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

        // did you even rebalance?
        assertEquals(new MagicString("N/a", 9), root.getRight().getData());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 8), root.getRight().getLeft().getData());
        assertEquals(0, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 10), root.getRight().getRight().getData());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRightRightRotationInside() {
        /*
                    5
                   / \
                3     10
               / \      \
              1   4      11
                          \
                           12 <-- adding this should make the 11 go below the 5
         */

        avlTree.add(new MagicString("alpha", 5));
        avlTree.add(new MagicString("beta", 3));
        avlTree.add(new MagicString("gamma", 10));
        avlTree.add(new MagicString("delta", 4));
        avlTree.add(new MagicString("epsilon", 1));

        // finished building the left side

        // the right side is supposed to trip things up!
        avlTree.add(new MagicString("zeta", 11));
        avlTree.add(new MagicString("eta", 12));

        assertEquals(7, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 5), root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 1), root.getLeft().getLeft().getData());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 4), root.getLeft().getRight().getData());
        assertEquals(0, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

        // did you even rebalance?
        assertEquals(new MagicString("N/a", 11), root.getRight().getData());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 10), root.getRight().getLeft().getData());
        assertEquals(0, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 12), root.getRight().getRight().getData());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRightLeftRotationInside() {
        /*
                    5
                   / \
                3     10
               / \      \
              1   4      12
                        /
                       11 <-- adding this should make the 11 go below the 5
         */

        avlTree.add(new MagicString("alpha", 5));
        avlTree.add(new MagicString("beta", 3));
        avlTree.add(new MagicString("gamma", 10));
        avlTree.add(new MagicString("delta", 4));
        avlTree.add(new MagicString("epsilon", 1));

        // finished building the left side

        // the right side is supposed to trip things up!
        avlTree.add(new MagicString("zeta", 12));
        avlTree.add(new MagicString("eta", 11));

        assertEquals(7, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 5), root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 1), root.getLeft().getLeft().getData());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 4), root.getLeft().getRight().getData());
        assertEquals(0, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

        // did you even rebalance?
        assertEquals(new MagicString("N/a", 11), root.getRight().getData());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 10), root.getRight().getLeft().getData());
        assertEquals(0, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 12), root.getRight().getRight().getData());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());
    }
 
    /*
   --------------------------------------
   THE FOLLOWING TESTS TEST REMOVING
   CASES FOR AVL TREES, LEFT LEFT, LEFT RIGHT,
   RIGHT LEFT, AND RIGHT RIGHT
 
   IM NOT SURE IF THESE ARE THE ONLY CASES,
   BUT I'M PRETTY SURE!
 
    Just like last time, let's start at the root :P
   --------------------------------------
     */

    @Test(timeout = TIMEOUT)
    public void testLeftLeftRotationRootRemove() {
        avlTree.add(new MagicString("lima", 5));
        avlTree.add(new MagicString("simpkins", 6));
        avlTree.add(new MagicString("hotel", 4));
        avlTree.add(new MagicString("echo", 3));

        // the restructure should take place on this command.
        avlTree.remove(new MagicString("simpkins", 6));

        assertEquals(3, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 4), root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 5), root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testLeftRightRotationRootRemove() {
        avlTree.add(new MagicString("vim", 5));
        avlTree.add(new MagicString("chris simpkins", 6));
        avlTree.add(new MagicString("nano", 3));
        avlTree.add(new MagicString("emacs", 4));

        // vim > emacs >>>>> nano

        // the restructure should take place on this command.
        avlTree.remove(new MagicString("chris simpkins", 6));

        assertEquals(3, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 4), root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 5), root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRightRightRotationRootRemove() {
        avlTree.add(new MagicString("java", 3));
        avlTree.add(new MagicString("Dr. CS", 2));
        avlTree.add(new MagicString("viml", 4));
        avlTree.add(new MagicString("bash", 5));

        // I like bash scripting ok...

        // the restructure should take place on this command.
        avlTree.remove(new MagicString("Dr. CS", 2));

        assertEquals(3, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 4), root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(0, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 5), root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRightLeftRotationRootRemove() {
        avlTree.add(new MagicString("kingdom", 3));
        avlTree.add(new MagicString("The Doctor. Of CS.", 2));
        avlTree.add(new MagicString("idk the next one", 5));
        avlTree.add(new MagicString("phylum", 4));

        // the restructure should take place on this command.
        avlTree.remove(new MagicString("http://www.docker.io", 2));

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
 
 
    /*
   --------------------------------------
   THE FOLLOWING 4 TESTS TEST THE 4 BASIC
   CASES (i found) FOR AVL TREES, LEFT LEFT, LEFT RIGHT,
   RIGHT LEFT, AND RIGHT RIGHT, BUT THIS TIME
   I'M GOING TO PUT THE CHANGE BELOW THE ROOT.
 
   IM NOT SURE IF THESE ARE THE ONLY REMOVE CASES
   BUT I'M PRETTY SURE?!?!? (not really :/)
 
   btw, the first two and second two tests
   have the same ouptut tree!
   --------------------------------------
     */


    @Test(timeout = TIMEOUT)
    public void testLeftLeftRotationInsideRemove() {
 
        /*
                    5
                   / \
                3     10
               / \    / \
              1   4   9  11 <-- removing this should make the 9 go below the 5
                     /
                    8
         */

        avlTree.add(new MagicString("alpha", 5));
        avlTree.add(new MagicString("beta", 3));
        avlTree.add(new MagicString("gamma", 10));
        avlTree.add(new MagicString("CHRIS SIMPKINS", 11));
        avlTree.add(new MagicString("delta", 4));
        avlTree.add(new MagicString("epsilon", 1));
        avlTree.add(new MagicString("zeta", 9));
        avlTree.add(new MagicString("eta", 8));

        // I really should be checking to see if the tree is properly made
        // here, but its 12 AM so i'm going to pass on that. Check to see
        // if your tree looks like the thing above.
        // If this becomes popular enough maybe I'll add it later

        // lets see if the remove works!?!?!?
        avlTree.remove(new MagicString("DR. SIMPKINS ACCEPT MY PULL REQUESTS PLEASE", 11));

        assertEquals(7, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 5), root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 1), root.getLeft().getLeft().getData());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 4), root.getLeft().getRight().getData());
        assertEquals(0, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

        // did you even rebalance?
        assertEquals(new MagicString("N/a", 9), root.getRight().getData());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 8), root.getRight().getLeft().getData());
        assertEquals(0, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 10), root.getRight().getRight().getData());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testLeftRightRotationInsideRemove() {
        /*
                    5
                   / \
                3     10
               / \    / \
              1   4  8   11 <-- removing this should make the 9 go below the 5
                      \
                       9
         */

        avlTree.add(new MagicString("alpha", 5));
        avlTree.add(new MagicString("beta", 3));
        avlTree.add(new MagicString("gamma", 10));
        avlTree.add(new MagicString("http://www.github.com/csimpkins", 11));
        avlTree.add(new MagicString("delta", 4));
        avlTree.add(new MagicString("epsilon", 1));
        avlTree.add(new MagicString("zeta", 8));
        avlTree.add(new MagicString("eta", 9));

        // I really should be checking to see if the tree is properly made
        // here, but its 12 AM so i'm going to pass on that. Check to see
        // if your tree looks like the thing above.
        // If this becomes popular enough maybe I'll add it later

        // lets see if the remove works!?!?!?
        avlTree.remove(new MagicString("DR. SIMPKINS ACCEPT MY PULL REQUESTS PLEASE", 11));

        assertEquals(7, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 5), root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 1), root.getLeft().getLeft().getData());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 4), root.getLeft().getRight().getData());
        assertEquals(0, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

        // did you even rebalance?
        assertEquals(new MagicString("N/a", 9), root.getRight().getData());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 8), root.getRight().getLeft().getData());
        assertEquals(0, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 10), root.getRight().getRight().getData());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRightRightRotationInsideRemove() {
        /*
                    5
                   / \
                3     10
               / \   /  \
              1   4 8   11
                    ^     \
                    |       12
                    |
                    --- Removing that node up there should make the 11 go up.
         */

        avlTree.add(new MagicString("alpha", 5));
        avlTree.add(new MagicString("beta", 3));
        avlTree.add(new MagicString("gamma", 10));
        avlTree.add(new MagicString("Dr. CS is here", 8));
        avlTree.add(new MagicString("delta", 4));
        avlTree.add(new MagicString("epsilon", 1));
        avlTree.add(new MagicString("zeta", 11));
        avlTree.add(new MagicString("eta", 12));


        // I really should be checking to see if the tree is properly made
        // here, but its 12 AM so i'm going to pass on that. Check to see
        // if your tree looks like the thing above.
        // If this becomes popular enough maybe I'll add it later

        // lets see if the remove works!?!?!?
        avlTree.remove(new MagicString("DR. SIMPKINS ACCEPT MY PULL REQUESTS Pl0X", 8));

        assertEquals(7, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 5), root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 1), root.getLeft().getLeft().getData());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 4), root.getLeft().getRight().getData());
        assertEquals(0, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

        // did you even rebalance?
        assertEquals(new MagicString("N/a", 11), root.getRight().getData());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 10), root.getRight().getLeft().getData());
        assertEquals(0, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 12), root.getRight().getRight().getData());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testRightLeftRotationInsideRemove() {
        /*
                    5
                   / \
                3     10
               / \   |  \
              1   4 8   12
                    ^   /
                    |  11
                    |
                    --- Removing that node up there should make the 11 go up.
         */

        avlTree.add(new MagicString("alpha", 5));
        avlTree.add(new MagicString("beta", 3));
        avlTree.add(new MagicString("gamma", 10));
        avlTree.add(new MagicString("Guess who?", 8));
        avlTree.add(new MagicString("delta", 4));
        avlTree.add(new MagicString("epsilon", 1));
        avlTree.add(new MagicString("zeta", 12));
        avlTree.add(new MagicString("eta", 11));

        // I really should be checking to see if the tree is properly made
        // here, but its 12 AM so i'm going to pass on that. Check to see
        // if your tree looks like the thing above.
        // If this becomes popular enough maybe I'll add it later

        // lets see if the remove works!?!?!?
        avlTree.remove(new MagicString("Also plz accept my TA application", 8));

        assertEquals(7, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 5), root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 1), root.getLeft().getLeft().getData());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 4), root.getLeft().getRight().getData());
        assertEquals(0, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

        // did you even rebalance?
        assertEquals(new MagicString("N/a", 11), root.getRight().getData());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 10), root.getRight().getLeft().getData());
        assertEquals(0, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 12), root.getRight().getRight().getData());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());
    }


    /**
     * ---------------------------------------------------
     * THIS TEST TESTS IF YOU'RE BRINGING ALONG LEAF NODES!
     *
     * Also they are more complicated.
     * That means there could be more bugs
     *
     * If you find errors, please submit a patch.
     * ---------------------------------------------------
     */

    @Test(timeout = TIMEOUT)
    public void testRightRightRotationInsideWithCarriers() {
        /*
                    5
                   / \
                3     10
               / \   |  \
              1   4 8   12
                        / \
                       11  14
                          /
                         13 <--- adding this node should cause a restructure.
                                 The 12 should go below the 5, but the 11 should
                                 be preserved.
 
         */

        avlTree.add(new MagicString("alpha", 5));
        avlTree.add(new MagicString("beta", 3));
        avlTree.add(new MagicString("gamma", 10));
        avlTree.add(new MagicString("Guess who?", 8));
        avlTree.add(new MagicString("delta", 4));
        avlTree.add(new MagicString("epsilon", 1));
        avlTree.add(new MagicString("zeta", 12));
        avlTree.add(new MagicString("eta", 11));
        avlTree.add(new MagicString("theta", 14));
        avlTree.add(new MagicString("iota", 13));


        assertEquals(10, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 5), root.getData());
        assertEquals(3, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 1), root.getLeft().getLeft().getData());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 4), root.getLeft().getRight().getData());
        assertEquals(0, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

        // did you even rebalance?
        assertEquals(new MagicString("N/a", 12), root.getRight().getData());
        assertEquals(2, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 10), root.getRight().getLeft().getData());
        assertEquals(1, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 8), root.getRight().getLeft().getLeft().getData());
        assertEquals(0, root.getRight().getLeft().getLeft().getHeight());
        assertEquals(0, root.getRight().getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 11), root.getRight().getLeft().getRight().getData());
        assertEquals(0, root.getRight().getLeft().getRight().getHeight());
        assertEquals(0, root.getRight().getLeft().getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 14), root.getRight().getRight().getData());
        assertEquals(1, root.getRight().getRight().getHeight());
        assertEquals(1, root.getRight().getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 13), root.getRight().getRight().getLeft().getData());
        assertEquals(0, root.getRight().getRight().getLeft().getHeight());
        assertEquals(0, root.getRight().getRight().getLeft().getBalanceFactor());
    }

    // This test is pretty hard.
    @Test(timeout = TIMEOUT)
    public void testLeftRightRotationInsideWithCarriers() {
        /*
                    5
                   / \
                3     10
               / \   |  \
              1   4 7   12
                   / \
                  6   9
                     /
                    8 <--- adding this node should cause a restructure.
                                 The 9 should go below the 5, but the 8 should
                                 be preserved.
 
         */

        avlTree.add(new MagicString("alpha", 5));
        avlTree.add(new MagicString("beta", 3));
        avlTree.add(new MagicString("gamma", 10));
        avlTree.add(new MagicString("Dr. Simpkins is the best", 7));
        avlTree.add(new MagicString("delta", 4));
        avlTree.add(new MagicString("epsilon", 1));
        avlTree.add(new MagicString("zeta", 12));
        avlTree.add(new MagicString("eta", 6));
        avlTree.add(new MagicString("theta", 9));
        avlTree.add(new MagicString("iota", 8));


        assertEquals(10, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 5), root.getData());
        assertEquals(3, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 1), root.getLeft().getLeft().getData());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 4), root.getLeft().getRight().getData());
        assertEquals(0, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

        // did you even rebalance?
        assertEquals(new MagicString("N/a", 9), root.getRight().getData());
        assertEquals(2, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 7), root.getRight().getLeft().getData());
        assertEquals(1, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 6), root.getRight().getLeft().getLeft().getData());
        assertEquals(0, root.getRight().getLeft().getLeft().getHeight());
        assertEquals(0, root.getRight().getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 8), root.getRight().getLeft().getRight().getData());
        assertEquals(0, root.getRight().getLeft().getRight().getHeight());
        assertEquals(0, root.getRight().getLeft().getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 10), root.getRight().getRight().getData());
        assertEquals(1, root.getRight().getRight().getHeight());
        assertEquals(-1, root.getRight().getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 12), root.getRight().getRight().getRight().getData());
        assertEquals(0, root.getRight().getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getRight().getBalanceFactor());
    }

    @Test(timeout = TIMEOUT)
    public void testLeftLeftRotationInsideWithCarriers() {
        /*
                    5
                   / \
                3     10
               / \   |  \
              1   4 8   12
                   / \
                  6   9
                   \
                    7 <--- adding this node should cause a restructure.
                                 The 8 should go below the 5, but the 9 should
                                 be preserved.
 
         */

        avlTree.add(new MagicString("alpha", 5));
        avlTree.add(new MagicString("beta", 3));
        avlTree.add(new MagicString("gamma", 10));
        avlTree.add(new MagicString("Dr. Simpkins is the best", 8));
        avlTree.add(new MagicString("delta", 4));
        avlTree.add(new MagicString("epsilon", 1));
        avlTree.add(new MagicString("zeta", 12));
        avlTree.add(new MagicString("eta", 6));
        avlTree.add(new MagicString("theta", 9));
        avlTree.add(new MagicString("iota", 7));


        assertEquals(10, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 5), root.getData());
        assertEquals(3, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(0, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 1), root.getLeft().getLeft().getData());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 4), root.getLeft().getRight().getData());
        assertEquals(0, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

        // did you even rebalance?
        assertEquals(new MagicString("N/a", 8), root.getRight().getData());
        assertEquals(2, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 6), root.getRight().getLeft().getData());
        assertEquals(1, root.getRight().getLeft().getHeight());
        assertEquals(-1, root.getRight().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 7), root.getRight().getLeft().getRight().getData());
        assertEquals(0, root.getRight().getLeft().getRight().getHeight());
        assertEquals(0, root.getRight().getLeft().getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 10), root.getRight().getRight().getData());
        assertEquals(1, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 12), root.getRight().getRight().getRight().getData());
        assertEquals(0, root.getRight().getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 9), root.getRight().getRight().getLeft().getData());
        assertEquals(0, root.getRight().getRight().getLeft().getHeight());
        assertEquals(0, root.getRight().getRight().getLeft().getBalanceFactor());
    }

    // This test is also pretty hard, but you should be able to get it
    // if you got the last 'hard one'
    @Test(timeout = TIMEOUT)
    public void testRightLeftRotationInsideWithCarriers() {
        /*
                    5
                   / \
                3     10
               / \   |  \
              1   4 8   15
              \    /    / \
              2   7   12   16
                    /  \    \
                   11  13    19
                       /
                     14 <--- adding this node should cause a restructure.
                                 The 12 should go below the 5, but the 11, 13,
                                 14, and 16 should be preserved.
 
         */

        avlTree.add(new MagicString("alpha", 5));
        avlTree.add(new MagicString("beta", 3));
        avlTree.add(new MagicString("gamma", 10));
        avlTree.add(new MagicString("Dr. CS is the only Dr.", 8));
        avlTree.add(new MagicString("delta", 4));
        avlTree.add(new MagicString("epsilon", 1));
        avlTree.add(new MagicString("zeta", 15));
        avlTree.add(new MagicString("eta", 12));
        avlTree.add(new MagicString("theta", 2));
        avlTree.add(new MagicString("iota", 7));
        avlTree.add(new MagicString("kappa", 16));
        avlTree.add(new MagicString("lambda", 11));
        avlTree.add(new MagicString("mu", 13));
        avlTree.add(new MagicString("nu", 19));

        // punch it scotty!
        avlTree.add(new MagicString("xi", 14));


        assertEquals(15, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 5), root.getData());
        assertEquals(4, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getData());
        assertEquals(2, root.getLeft().getHeight());
        assertEquals(1, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 1), root.getLeft().getLeft().getData());
        assertEquals(1, root.getLeft().getLeft().getHeight());
        assertEquals(-1, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 2), root.getLeft().getLeft().getRight().getData());
        assertEquals(0, root.getLeft().getLeft().getRight().getHeight());
        assertEquals(0, root.getLeft().getLeft().getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 4), root.getLeft().getRight().getData());
        assertEquals(0, root.getLeft().getRight().getHeight());
        assertEquals(0, root.getLeft().getRight().getBalanceFactor());

        // did you even rebalance?
        assertEquals(new MagicString("N/a", 12), root.getRight().getData());
        assertEquals(3, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 10), root.getRight().getLeft().getData());
        assertEquals(2, root.getRight().getLeft().getHeight());
        assertEquals(1, root.getRight().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 8), root.getRight().getLeft().getLeft().getData());
        assertEquals(1, root.getRight().getLeft().getLeft().getHeight());
        assertEquals(1, root.getRight().getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 7), root.getRight().getLeft().getLeft().getLeft().getData());
        assertEquals(0, root.getRight().getLeft().getLeft().getLeft().getHeight());
        assertEquals(0, root.getRight().getLeft().getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 11), root.getRight().getLeft().getRight().getData());
        assertEquals(0, root.getRight().getLeft().getRight().getHeight());
        assertEquals(0, root.getRight().getLeft().getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 15), root.getRight().getRight().getData());
        assertEquals(2, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 16), root.getRight().getRight().getRight().getData());
        assertEquals(1, root.getRight().getRight().getRight().getHeight());
        assertEquals(-1, root.getRight().getRight().getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 13), root.getRight().getRight().getLeft().getData());
        assertEquals(1, root.getRight().getRight().getLeft().getHeight());
        assertEquals(-1, root.getRight().getRight().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 14), root.getRight().getRight().getLeft().getRight().getData());
        assertEquals(0, root.getRight().getRight().getLeft().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getLeft().getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 19), root.getRight().getRight().getRight().getRight().getData());
        assertEquals(0, root.getRight().getRight().getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getRight().getRight().getBalanceFactor());
    }

    // The interface wants you to use predecessor instead of the
    // successor so here we go...
    @Test(timeout = TIMEOUT)
    public void testPredecessorOnRemove() {
        avlTree.add(new MagicString("root", 5));
        avlTree.add(new MagicString("left", 4));
        avlTree.add(new MagicString("right", 8));
        avlTree.add(new MagicString("feeder", 6));
        avlTree.add(new MagicString("feeder", 3));
        avlTree.add(new MagicString("feeder", 9));
        avlTree.add(new MagicString("feeder", 7));

        // removing the 8 node should cause the 7 node to take it's place!
        avlTree.remove(new MagicString("right", 8));

        assertEquals(6, avlTree.size());

        AVLNode<MagicString> root = avlTree.getRoot();
        assertEquals(new MagicString("N/a", 5), root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        assertEquals(new MagicString("N/a", 4), root.getLeft().getData());
        assertEquals(1, root.getLeft().getHeight());
        assertEquals(1, root.getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 3), root.getLeft().getLeft().getData());
        assertEquals(0, root.getLeft().getLeft().getHeight());
        assertEquals(0, root.getLeft().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 7), root.getRight().getData());
        assertEquals(1, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertEquals(new MagicString("N/a", 6), root.getRight().getLeft().getData());
        assertEquals(0, root.getRight().getLeft().getHeight());
        assertEquals(0, root.getRight().getLeft().getBalanceFactor());
        assertEquals(new MagicString("N/a", 9), root.getRight().getRight().getData());
        assertEquals(0, root.getRight().getRight().getHeight());
        assertEquals(0, root.getRight().getRight().getBalanceFactor());
    }
 
    /*
    -----------------------------------------
    DANGER DANGER DANGER DANGER DANGER DANGER
 
    THESE TESTS ARE THE OUTPUT OF MY PERSONAL
    AVL TREE. THERE IS AN EXTREMELY HIGH
    CHANCE THESE TESTS ARE PARTIALLY OR
    COMPLETELY WRONG. PLEASE REPORT ERRORS
    ASAP ON PIAZZA, PREFERABLY INCLUDING
    A PATCH.
 
    DANGER DANGER DANGER DANGER DANGER DANGER
    -----------------------------------------
     */


    @Test(timeout = TIMEOUT)
    public void testInorder() {
        // The list is created up in the top in createAVLForBasicTests.
        // These numbers were generated randomly, so they must be correct!
        assertEquals(Arrays.asList(
                        0, 1, 3, 4, 6, 8, 9, 10, 11, 12, 13, 16, 18, 20),
                createAVLForBasicTests().inorder());
    }


    @Test(timeout = TIMEOUT)
    public void testPostorder() {
        // The list is created up in the top in createAVLForBasicTests.
        // These numbers were generated randomly, so they must be correct!
        assertEquals(Arrays.asList(
                        0, 3, 6, 4, 1, 9, 11, 12, 10, 16, 20, 18, 13, 8),
                createAVLForBasicTests().postorder());
    }


    @Test(timeout = TIMEOUT)
    public void testPreorder() {
        // The list is created up in the top in createAVLForBasicTests.
        // These numbers were generated randomly, so they must be correct!
        assertEquals(Arrays.asList(
                        8, 1, 0, 4, 3, 6, 13, 10, 9, 12, 11, 18, 16, 20),
                createAVLForBasicTests().preorder());
    }
 
    /*
    -----------------------
    THESE TESTS SHOULD BE
    SIMPLER AND HAVE LESS
    ERRORS!
    -----------------------
     */

    @Test(expected = IllegalArgumentException.class)
    public void testContainsKJ() {
        AVL<Integer> testAVL = new AVL<>();
        testAVL.add(new Integer(2));
        assertEquals(new Integer(2),testAVL.get(new Integer(2)));
        assertTrue(testAVL.contains(new Integer(2)));
        testAVL.contains(null);

    }


    @Test(timeout = TIMEOUT)
    public void testContains() {
        AVL<Integer> testAVL = new AVL<>();

        testList.stream().distinct().forEach((toAdd) -> {
            assertFalse(testAVL.contains(toAdd));
            testAVL.add(toAdd);
            assertTrue(testAVL.contains(toAdd));
        });

        testList.stream().forEach((toCheck) ->
                assertTrue(testAVL.contains(toCheck)));

        // removes elements backwards! Why not?
        testList.stream().sorted((a, b) -> -a.compareTo(b)).distinct().forEach((toRemove) -> {
            testAVL.remove(toRemove);
            assertFalse(testAVL.contains(toRemove));
        });
    }

    /**
     * Copied without shame from the TA's
     */
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
            if (other == null ) {
                throw new IllegalArgumentException("The thing being " +
                        "compared was null!");
            }
            return number - other.number;
        }

        @Override
        public String toString() {
            return "MagicString: " + magicString + ", " + number;
        }
    }
}
 
/*
Press gg to go back up!... why are you still h- Oh wait your not using vim...
Looks like you just wasted 2 seconds of your life scrolling back up!
Looks like I wasted a minute typing this out though...
*/