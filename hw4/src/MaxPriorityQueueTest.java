/**
 * Created by kaijiehuang on 2/12/15.
 */
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;

@RunWith(value = BlockJUnit4ClassRunner.class)
public class MaxPriorityQueueTest extends TestCase {
    MaxPriorityQueue<Integer> queue;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        this.queue = new MaxPriorityQueue<>();
    }

    @Test
    public void testNew() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        assertEquals(MaxHeap.STARTING_SIZE, queue.getBackingHeap().getBackingArray().length);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueFromEmpty() {
        queue.dequeue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEnqueueNull() {
        queue.enqueue(null);
    }

    @Test
    public void testExpand() {
        queue.enqueue(3);
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals(MaxHeap.STARTING_SIZE, queue.getBackingHeap().getBackingArray().length);

        for (int i = 0; i < 8; i++) {
            queue.enqueue(i);
        }
        assertEquals(9, queue.size());
        assertEquals(MaxHeap.STARTING_SIZE, queue.getBackingHeap().getBackingArray().length);

        queue.enqueue(3);
        assertEquals(10, queue.size());
        assertEquals(MaxHeap.STARTING_SIZE * 2, queue.getBackingHeap().getBackingArray().length);
    }

    @Test
    public void testEnqueue() {
        queue.enqueue(9);
        queue.enqueue(6);
        queue.enqueue(3);
        Integer []ex = {null, 9, 6, 3, null, null, null, null, null, null};
        assertArrayEquals(ex, queue.getBackingHeap().getBackingArray());

        queue.enqueue(7);
        ex[2] = 7;
        ex[4] = 6;
        assertArrayEquals(ex, queue.getBackingHeap().getBackingArray());

        queue.enqueue(8);
        ex[2] = 8;
        ex[5] = 7;
        assertArrayEquals(ex, queue.getBackingHeap().getBackingArray());

        queue.enqueue(10);
        ex[1] = 10;
        ex[3] = 9;
        ex[6] = 3;
        assertArrayEquals(ex, queue.getBackingHeap().getBackingArray());
    }

    @Test
    public void testDequeue() {
        queue.enqueue(3);
        queue.enqueue(6);
        queue.enqueue(9);
        queue.enqueue(8);
        queue.enqueue(7);
        queue.enqueue(1);
        queue.enqueue(2);
        Integer []ex = {null, 9, 8, 6, 3, 7, 1, 2, null, null};
        assertArrayEquals(ex, queue.getBackingHeap().getBackingArray());

        assertEquals(9, (int)queue.dequeue());
        ex[1] = 8;
        ex[2] = 7;
        ex[5] = 2;
        ex[7] = null;
        assertArrayEquals(ex, queue.getBackingHeap().getBackingArray());

        assertEquals(8, (int)queue.dequeue());
        assertEquals(7, (int)queue.dequeue());
        assertEquals(6, (int)queue.dequeue());
        assertEquals(3, (int)queue.dequeue());
        assertEquals(2, (int)queue.dequeue());
        assertEquals(1, (int)queue.dequeue());

        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        assertEquals(MaxHeap.STARTING_SIZE, queue.getBackingHeap().getBackingArray().length);
    }

    @Test
    public void testClear() {
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
        assertEquals(5, queue.size());
        assertEquals(MaxHeap.STARTING_SIZE, queue.getBackingHeap().getBackingArray().length);

        queue.clear();
        assertEquals(0, queue.size());
        assertEquals(MaxHeap.STARTING_SIZE, queue.getBackingHeap().getBackingArray().length);
    }

    @Test
    public void testEnqueueEqualChildren() {
        queue.enqueue(5);
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(1);
        Integer []ex = {null, 5, 3, 3, 1, null, null, null, null, null};
        assertArrayEquals(ex, queue.getBackingHeap().getBackingArray());

        assertEquals(5, (int)queue.dequeue());
        ex[1] = 3;
        ex[3] = 1;
        ex[4] = null;
        assertArrayEquals(ex, queue.getBackingHeap().getBackingArray());
    }
}
