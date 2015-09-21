/**
 * Created by kaijiehuang on 2/16/15.
 */

import org.junit.Before;
        import org.junit.Test;

        import java.util.*;

        import static org.junit.Assert.assertArrayEquals;
        import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.assertFalse;
        import static org.junit.Assert.assertTrue;

public class AngTest {

    private HeapInterface<Integer> maxHeap;
    private PriorityQueueInterface<Integer> maxPriorityQueue;

    @Before
    public void setUp() {
        maxHeap = new MaxHeap<Integer>();

        maxPriorityQueue = new MaxPriorityQueue<>();
    }

    private void makeHeap() {
        maxHeap.add(5);
        maxHeap.add(8);
        maxHeap.add(1);
        maxHeap.add(9);
        maxHeap.add(10);
        maxHeap.add(7);
        maxHeap.add(2);
    }

    @Test (timeout = 200)
    public void testHeapAddNormal() {
        // setup
        makeHeap();

        // check size
        assertEquals((Object) 7, maxHeap.size());

        // check structure
        Integer[] expected = {null, 10, 9, 7, 5, 8, 1, 2, null, null};
        assertArrayEquals(expected, maxHeap.getBackingArray());
    }

    @Test (timeout = 200, expected = IllegalArgumentException.class)
    public void testHeapAddNull() {
        // setup
        makeHeap();
        maxHeap.add(null);
    }

    @Test (timeout = 200, expected = IllegalArgumentException.class)
    public void testHeapAddNull2() {
        // setup
        maxHeap.add(null);
    }

    @Test (timeout = 200)
    public void testHeapAddRepeats() {
        // setup
        makeHeap();
        maxHeap.add(11);
        maxHeap.add(11);

        // check size
        assertEquals((Object) 9, maxHeap.size());

        // check structure
        Integer[] expected = {null, 11, 11, 7, 10, 8, 1, 2, 5, 9};
        assertArrayEquals(expected, maxHeap.getBackingArray());
    }

    @Test (timeout = 200)
    public void testHeapResize() {
        // setup
        makeHeap();
        maxHeap.add(11);
        maxHeap.add(11);
        maxHeap.add(14);
        maxHeap.add(1);
        maxHeap.add(-1);
        maxHeap.add(0);
        maxHeap.add(3);
        maxHeap.add(4);

        // check size
        assertEquals((Object) 15, maxHeap.size());

        // check structure
        Integer[] expected = {null, 14, 11, 7, 10, 11, 1, 4, 5, 9, 8, 1, -1, 0, 2, 3, null, null, null, null};
        assertArrayEquals(expected, maxHeap.getBackingArray());
    }

    @Test (timeout = 200)
    public void testHeapRemoveNormal() {
        // setup
        makeHeap();

        // check size
        assertEquals((Object) 7, maxHeap.size());

        // remove
        assertEquals((Object) 10, maxHeap.remove());
        assertEquals((Object) 6, maxHeap.size());

        assertEquals((Object) 9, maxHeap.remove());
        assertEquals((Object) 5, maxHeap.size());

        assertEquals((Object) 8, maxHeap.remove());
        assertEquals((Object) 4, maxHeap.size());

        // check structure
        assertFalse(maxHeap.isEmpty());
        Integer[] expected = {null, 7, 5, 2, 1, null, null, null, null, null};
        assertArrayEquals(expected, maxHeap.getBackingArray());
    }

    @Test (timeout = 200, expected = NoSuchElementException.class)
    public void testHeapRemoveEmpty() {
        // check size
        assertEquals((Object) 0, maxHeap.size());

        // remove
        maxHeap.remove();
    }

    @Test (timeout = 200)
    public void testHeapRemoveAll() {
        // setup
        makeHeap();

        // check size
        assertEquals((Object) 7, maxHeap.size());

        // remove
        assertEquals((Object) 10, maxHeap.remove());
        assertEquals((Object) 6, maxHeap.size());

        assertEquals((Object) 9, maxHeap.remove());
        assertEquals((Object) 5, maxHeap.size());

        assertEquals((Object) 8, maxHeap.remove());
        assertEquals((Object) 4, maxHeap.size());

        assertEquals((Object) 7, maxHeap.remove());
        assertEquals((Object) 3, maxHeap.size());

        assertEquals((Object) 5, maxHeap.remove());
        assertEquals((Object) 2, maxHeap.size());

        assertEquals((Object) 2, maxHeap.remove());
        assertEquals((Object) 1, maxHeap.size());

        assertEquals((Object) 1, maxHeap.remove());
        assertEquals((Object) 0, maxHeap.size());

    }

    // PROBLEM WITH THIS ONE.
    @Test (timeout = 200)
    public void testHeapRemoveDuplicate() {
        // Note: When you have multiple duplicate values, if you use the remove method,
        // then the duplicate value on the right becomes the root. This test does this
        // check using the memory addresses of the objects passed into the heap with the
        // help of the == operator.

        // setup
        MaxHeap<String> maxHeap = new MaxHeap<String>();
        String str1 = new String("Foo");
        String str2 = new String("Foo");
        String str3 = new String("Foo");
        maxHeap.add(str1);
        maxHeap.add(str2);
        maxHeap.add(str3);

        // check size
        assertEquals((Object) 3, maxHeap.size());

        // remove and check structure
        assertTrue(str1 == maxHeap.remove());
        assertEquals((Object) 2, maxHeap.size());

        // remove and check structure
        assertTrue(str3 == maxHeap.remove());
        assertEquals((Object) 1, maxHeap.size());

        // remove and check structure
        assertTrue(str2 == maxHeap.remove());
        assertEquals((Object) 0, maxHeap.size());

        assertFalse(str1 == str2);
    }

    @Test (timeout = 200)
    public void testHeapRemoveAllReuse() {
        // setup
        maxHeap.add(5);
        maxHeap.add(6);
        maxHeap.add(7);

        // check size
        assertEquals((Object) 3, maxHeap.size());

        // remove
        assertEquals((Object) 7, maxHeap.remove());
        assertEquals((Object) 2, maxHeap.size());

        assertEquals((Object) 6, maxHeap.remove());
        assertEquals((Object) 1, maxHeap.size());

        assertEquals((Object) 5, maxHeap.remove());
        assertEquals((Object) 0, maxHeap.size());

        // reuse
        makeHeap();

        // check size
        assertEquals((Object) 7, maxHeap.size());

        // check structure
        Integer[] expected = {null, 10, 9, 7, 5, 8, 1, 2, null, null};
        assertArrayEquals(expected, maxHeap.getBackingArray());
    }

    @Test
    public void testHeapResizeRemove() {
        // setup
        makeHeap();
        maxHeap.add(11);
        maxHeap.add(11);
        maxHeap.add(14);
        maxHeap.add(1);

        // check size
        assertEquals((Object) 11, maxHeap.size());

        // check structure
        Integer[] expected = {null, 14, 11, 7, 10, 11, 1, 2, 5, 9, 8, 1, null, null, null, null, null, null, null, null};
        assertArrayEquals(expected, maxHeap.getBackingArray());

        // remove
        assertEquals((Object) 14, maxHeap.remove());
        assertEquals((Object) 10, maxHeap.size());

        assertEquals((Object) 11, maxHeap.remove());
        assertEquals((Object) 9, maxHeap.size());

        assertEquals((Object) 11, maxHeap.remove());
        assertEquals((Object) 8, maxHeap.size());

        // check structure
        Integer[] expected2 = {null, 10, 9, 7, 5, 8, 1, 2, 1, null, null, null, null, null, null, null, null, null, null, null};
        assertArrayEquals(expected2, maxHeap.getBackingArray());
    }

    @Test (timeout = 200)
    public void testHeapAddRemove() {
        // Adds 500 random numbers to the heap, checks size,
        // then removes all elements from the heap.

        int counter = 0;
        int size = 500;
        ArrayList<Integer> added = new ArrayList<Integer>(size);
        Random rand = new Random();

        while (counter < size) {
            int number = rand.nextInt(1000);
            added.add(number);
            maxHeap.add(number);
            counter++;
        }

        // check size
        assertEquals((Object) size, maxHeap.size());

        for (int i = 0; i < counter; i++) {
            Integer removed = maxHeap.remove();
            assertTrue(added.remove(removed));
        }

        // check size
        assertEquals((Object) 0, maxHeap.size());
    }

    @Test (timeout = 200)
    public void testHeapClear() {
        // setup
        makeHeap();

        // check size
        assertEquals((Object) 7, maxHeap.size());

        // check structure
        Integer[] expected = {null, 10, 9, 7, 5, 8, 1, 2, null, null};
        assertArrayEquals(expected, maxHeap.getBackingArray());

        // clear
        maxHeap.clear();
        assertEquals((Object) 0, maxHeap.size());
        Integer[] expected2 = {null, null, null, null, null, null, null, null, null, null};
        assertArrayEquals(expected2, maxHeap.getBackingArray());

        // setup
        makeHeap();

        // check size
        assertEquals((Object) 7, maxHeap.size());

        // check structure
        assertArrayEquals(expected, maxHeap.getBackingArray());
    }

    @Test
    public void testHeap() {
        maxHeap.add(5);
        maxHeap.add(8);
        maxHeap.add(1);
        maxHeap.add(9);
        maxHeap.add(10);


        Integer[] expected = {null, 10, 9, 1, 5, 8, null, null, null, null};

        assertArrayEquals(expected, maxHeap.getBackingArray());

        assertEquals(new Integer(10), maxHeap.remove());
        assertEquals(new Integer(9), maxHeap.remove());
        assertEquals(3, maxHeap.size());
        assertFalse(maxHeap.isEmpty());
        assertEquals(new Integer(8), maxHeap.remove());
        assertEquals(new Integer(5), maxHeap.remove());
        assertEquals(new Integer(1), maxHeap.remove());
        assertTrue(maxHeap.isEmpty());
    }

    @Test
    public void testPriorityQueue() {
        maxPriorityQueue.enqueue(5);
        maxPriorityQueue.enqueue(8);
        maxPriorityQueue.enqueue(1);
        maxPriorityQueue.enqueue(9);
        maxPriorityQueue.enqueue(10);

        assertEquals(new Integer(10), maxPriorityQueue.dequeue());
        assertEquals(new Integer(9), maxPriorityQueue.dequeue());
        assertEquals(3, maxPriorityQueue.size());
        assertFalse(maxPriorityQueue.isEmpty());
        assertEquals(new Integer(8), maxPriorityQueue.dequeue());
        assertEquals(new Integer(5), maxPriorityQueue.dequeue());
        assertEquals(new Integer(1), maxPriorityQueue.dequeue());
        assertTrue(maxPriorityQueue.isEmpty());
    }
}