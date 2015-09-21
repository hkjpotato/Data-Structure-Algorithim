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
public class MaxHeapTest extends TestCase {
    MaxHeap<Integer> heap;

    @Before
    public void setUp() throws Exception {
        super.setUp();

        this.heap = new MaxHeap<>();
    }

    @Test
    public void testNew() {
        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
        assertEquals(MaxHeap.STARTING_SIZE, heap.getBackingArray().length);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromEmpty() {
        heap.remove();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {
        heap.add(null);
    }

    @Test
    public void testExpand() {
        heap.add(3);
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(MaxHeap.STARTING_SIZE, heap.getBackingArray().length);

        for (int i = 0; i < 8; i++) {
            heap.add(i);
        }
        assertEquals(9, heap.size());
        assertEquals(MaxHeap.STARTING_SIZE, heap.getBackingArray().length);

        heap.add(3);
        assertEquals(10, heap.size());
        assertEquals(MaxHeap.STARTING_SIZE * 2, heap.getBackingArray().length);
    }

    @Test
    public void testAdd() {
        heap.add(9);
        heap.add(6);
        heap.add(3);
        Integer []ex = {null, 9, 6, 3, null, null, null, null, null, null};
        assertArrayEquals(ex, heap.getBackingArray());

        heap.add(7);
        ex[2] = 7;
        ex[4] = 6;
        assertArrayEquals(ex, heap.getBackingArray());

        heap.add(8);
        ex[2] = 8;
        ex[5] = 7;
        assertArrayEquals(ex, heap.getBackingArray());

        heap.add(10);
        ex[1] = 10;
        ex[3] = 9;
        ex[6] = 3;
        assertArrayEquals(ex, heap.getBackingArray());
    }

    @Test
    public void testRemove() {
        heap.add(3);
        heap.add(6);
        heap.add(9);
        heap.add(8);
        heap.add(7);
        heap.add(1);
        heap.add(2);
        Integer []ex = {null, 9, 8, 6, 3, 7, 1, 2, null, null};
        assertArrayEquals(ex, heap.getBackingArray());

        assertEquals(9, (int)heap.remove());
        ex[1] = 8;
        ex[2] = 7;
        ex[5] = 2;
        ex[7] = null;
        assertArrayEquals(ex, heap.getBackingArray());

        assertEquals(8, (int)heap.remove());
        assertEquals(7, (int)heap.remove());
        assertEquals(6, (int)heap.remove());
        assertEquals(3, (int)heap.remove());
        assertEquals(2, (int)heap.remove());
        assertEquals(1, (int)heap.remove());

        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
        assertEquals(MaxHeap.STARTING_SIZE, heap.getBackingArray().length);
    }

    @Test
    public void testClear() {
        for (int i = 0; i < 5; i++) {
            heap.add(i);
        }
        assertEquals(5, heap.size());
        assertEquals(MaxHeap.STARTING_SIZE, heap.getBackingArray().length);

        heap.clear();
        assertEquals(0, heap.size());
        assertEquals(MaxHeap.STARTING_SIZE, heap.getBackingArray().length);
    }

    @Test
    public void testAddEqualChildren() {
        heap.add(5);
        heap.add(3);
        heap.add(3);
        heap.add(1);
        Integer []ex = {null, 5, 3, 3, 1, null, null, null, null, null};
        assertArrayEquals(ex, heap.getBackingArray());

        assertEquals(5, (int)heap.remove());
        ex[1] = 3;
        ex[3] = 1;
        ex[4] = null;
        assertArrayEquals(ex, heap.getBackingArray());
    }
}