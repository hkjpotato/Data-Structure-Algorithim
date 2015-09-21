import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StackQueueTestStudent {
    private ArrayQueue<Integer> queue;
    private ArrayStack<Integer> stack;

    @Before
    public void setUp() throws Exception {
        queue = new ArrayQueue<>();
        stack = new ArrayStack<>();
    }

    @Test
    public void testQueueSimple() {
        queue.enqueue(5);
        queue.enqueue(7);
        queue.enqueue(6);

        Integer[] arr = {5, 7, 6, null, null, null, null, null, null, null};

        assertArrayEquals(arr, queue.getBackingArray());
    }

    @Test
    public void testQueueHarder() {
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }
        assertEquals(new Integer(0), queue.dequeue());
        queue.enqueue(10);

        assertEquals(new Integer(10), queue.getBackingArray()[0]);
        queue.enqueue(11);

        Object[] arr = queue.getBackingArray();
        for (int i = 0; i < 20; i++) {
            if (i < 11) {
                assertEquals(new Integer(i + 1), arr[i]);
            } else {
                assertNull(arr[i]);
            }
        }

    }

    @Test
    public void testStack() {
        Integer[] arr = new Integer[10];
        Integer[] arr2 = new Integer[20];
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            arr[i] = i;
            arr2[i] = i;
        }
        assertArrayEquals(arr, stack.getBackingArray());

        arr2[10] = 10;
        stack.push(10);
        assertArrayEquals(arr2, stack.getBackingArray());
    }
}
