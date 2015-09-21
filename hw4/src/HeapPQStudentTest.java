import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HeapPQStudentTest {

    private HeapInterface<Integer> maxHeap;
    private PriorityQueueInterface<Integer> maxPriorityQueue;

    @Before
    public void setUp() {
        maxHeap = new MaxHeap<>();
        maxPriorityQueue = new MaxPriorityQueue<>();
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
