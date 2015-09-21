import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class QueueStackTest {
    private Queue queue;
    private Stack stack;
    private final Integer[] testSet0 = new Integer[0],
                            testSet1 = new Integer[] {1},
                            testSetMany = new Integer[] {1, 2, 3};

    @Before
    public void setUp() {
        queue = new Queue();
        stack = new Stack();
    }

//    Queue Tests

    @Test(expected = IllegalArgumentException.class)
    public void testEnqueueNull() {
        queue.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueEmpty() {
        queue.dequeue();
    }
    
    @Test(timeout = 200)
    public void testEnqueue0() {
        Integer[] testSet = testSet0;
        for (int i = 0; i < testSet.length; i++) {
            queue.enqueue(testSet[i]);
            assertEquals(i + 1, queue.size());
        }

        Object[] queueOut = queue.toArray();
        if (testSet.length > 0 && queueOut[0] == testSet[testSet.length - 1]) {
            for (int i = 0; i < testSet.length; i++) {
                assertEquals(testSet[testSet.length - 1 - i], queueOut[i]);
            }
        } else {
            for (int i = 0; i < testSet.length; i++) {
                assertEquals(testSet[i], queueOut[i]);
            }
        }
    }

    @Test(timeout = 200)
    public void testDequeue0() {
        Integer[] testSet = testSet0;
        for (Integer i: testSet) {
            queue.enqueue(i);
        }
        for (int i = 0; i < testSet.length; i++) {
            assertEquals(testSet[i], queue.dequeue());
        }
    }

    @Test(timeout = 200)
    public void testQueueSize0() {
        Integer[] testSet = testSet0;
        for (Integer i: testSet) {
            queue.enqueue(i);
        }
        assertEquals(testSet.length, queue.size());
    }

    @Test(timeout = 200)
    public void testQueueIsEmpty0() {
        Integer[] testSet = testSet0;
        for (Integer i: testSet) {
            queue.enqueue(i);
        }
        assertTrue(queue.isEmpty());
    }

    
    @Test(timeout = 200)
    public void testEnqueue1() {
        Integer[] testSet = testSet1;
        for (Integer i: testSet) {
            queue.enqueue(i);
        }
        Object[] queueOut = queue.toArray();
        if (testSet.length > 0 && queueOut[0] == testSet[testSet.length - 1]) {
            for (int i = 0; i < testSet.length; i++) {
                assertEquals(testSet[testSet.length - 1 - i], queueOut[i]);
            }
        } else {
            for (int i = 0; i < testSet.length; i++) {
                assertEquals(testSet[i], queueOut[i]);
            }
        }
    }

    @Test(timeout = 200)
    public void testEnqueueDequeueEmpty0() {
        Integer[] testSet = testSet0;
        for (int i = 0; i < testSet.length; i++) {
            queue.enqueue(testSet[i]);
            assertEquals(i + 1, queue.size());
        }
        for (int i = 0; i < testSet.length; i++) {
            assertEquals(testSet[i], queue.dequeue());
            assertEquals(testSet.length - 1 - i, queue.size());
        }
        assertTrue(queue.isEmpty());
    }

    @Test(timeout = 200)
    public void testDequeue1() {
        Integer[] testSet = testSet1;
        for (Integer i: testSet) {
            queue.enqueue(i);
        }
        for (int i = 0; i < testSet.length; i++) {
            assertEquals(testSet[i], queue.dequeue());
        }
    }

    @Test(timeout = 200)
    public void testQueueSize1() {
        Integer[] testSet = testSet1;
        for (Integer i: testSet) {
            queue.enqueue(i);
        }
        assertEquals(testSet.length, queue.size());
    }

    @Test(timeout = 200)
    public void testQueueIsEmpty1() {
        Integer[] testSet = testSet1;
        for (Integer i: testSet) {
            queue.enqueue(i);
        }
        assertFalse(queue.isEmpty());
    }

    @Test(timeout = 200)
    public void testEnqueueDequeueEmpty1() {
        Integer[] testSet = testSet1;
        for (int i = 0; i < testSet.length; i++) {
            queue.enqueue(testSet[i]);
            assertEquals(i + 1, queue.size());
        }
        for (int i = 0; i < testSet.length; i++) {
            assertEquals(testSet[i], queue.dequeue());
            assertEquals(testSet.length - 1 - i, queue.size());
        }
        assertTrue(queue.isEmpty());
    }

    @Test(timeout = 200)
    public void testEnqueueMany() {
        Integer[] testSet = testSetMany;
        for (int i = 0; i < testSet.length; i++) {
            queue.enqueue(testSet[i]);
            assertEquals(i + 1, queue.size());
        }
        Object[] queueOut = queue.toArray();
        if (testSet.length > 0 && queueOut[0] == testSet[testSet.length - 1]) {
            for (int i = 0; i < testSet.length; i++) {
                assertEquals(testSet[testSet.length - 1 - i], queueOut[i]);
            }
        } else {
            for (int i = 0; i < testSet.length; i++) {
                assertEquals(testSet[i], queueOut[i]);
            }
        }
    }

    @Test(timeout = 200)
    public void testDequeueMany() {
        Integer[] testSet = testSetMany;
        for (Integer i: testSet) {
            queue.enqueue(i);
        }
        for (int i = 0; i < testSet.length; i++) {
            assertEquals(testSet[i], queue.dequeue());
        }
    }

    @Test(timeout = 200)
    public void testQueueSizeMany() {
        Integer[] testSet = testSetMany;
        for (Integer i: testSet) {
            queue.enqueue(i);
        }
        assertEquals(testSet.length, queue.size());
    }

    @Test(timeout = 200)
    public void testQueueIsEmptyMany() {
        Integer[] testSet = testSetMany;
        for (Integer i: testSet) {
            queue.enqueue(i);
        }
        assertFalse(queue.isEmpty());
    }

    @Test(timeout = 200)
    public void testEnqueueDequeueEmptyMany() {
        Integer[] testSet = testSetMany;
        for (int i = 0; i < testSet.length; i++) {
            queue.enqueue(testSet[i]);
            assertEquals(i + 1, queue.size());
        }
        for (int i = 0; i < testSet.length; i++) {
            assertEquals(testSet[i], queue.dequeue());
            assertEquals(testSet.length - 1 - i, queue.size());
        }
        assertTrue(queue.isEmpty());
    }

    //    Stack Tests
    @Test(expected = IllegalArgumentException.class)
    public void testPushNull() {
        stack.push(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testPopEmpty() {
        stack.pop();
    }

    @Test(timeout = 200)
    public void testPush0() {
        Integer[] testSet = testSet0;
        for (int i = 0; i < testSet.length; i++) {
            stack.push(testSet[i]);
            assertEquals(i + 1, stack.size());
        }
        Object[] stackOut = stack.toArray();
        if (testSet.length > 0 && stackOut[0] == testSet[testSet.length - 1]) {
            for (int i = 0; i < testSet.length; i++) {
                assertEquals(testSet[testSet.length - 1 - i], stackOut[i]);
            }
        } else {
            for (int i = 0; i < testSet.length; i++) {
                assertEquals(testSet[i], stackOut[i]);
            }
        }
    }

    @Test(timeout = 200)
    public void testPop0() {
        Integer[] testSet = testSet0;
        for (Integer i: testSet) {
            stack.push(i);
        }
        for (int i = testSet.length - 1; i > -1; i--) {
            assertEquals(testSet[i], stack.pop());
            assertEquals(i, stack.size());
        }
    }

    @Test(timeout = 200)
    public void testStackSize0() {
        Integer[] testSet = testSet0;
        for (Integer i: testSet) {
            stack.push(i);
        }
        assertEquals(testSet.length, stack.size());
    }

    @Test(timeout = 200)
    public void testStackIsEmpty0() {
        Integer[] testSet = testSet0;
        for (Integer i: testSet) {
            stack.push(i);
        }
        assertTrue(stack.isEmpty());
    }

    @Test(timeout = 200)
    public void testPushPopEmpty0() {
        Integer[] testSet = testSet0;
        for (int i = 0; i < testSet.length; i++) {
            stack.push(testSet[i]);
            assertEquals(i + 1, stack.size());
        }
        for (int i = testSet.length - 1; i > -1; i--) {
            assertEquals(testSet[i], stack.pop());
            assertEquals(i, stack.size());
        }
        assertTrue(queue.isEmpty());
    }

    @Test(timeout = 200)
     public void testPush1() {
        Integer[] testSet = testSet1;
        for (Integer i: testSet) {
            stack.push(i);
        }
        Object[] stackOut = stack.toArray();
        if (testSet.length > 0 && stackOut[0] == testSet[testSet.length - 1]) {
            for (int i = 0; i < testSet.length; i++) {
                assertEquals(testSet[testSet.length - 1 - i], stackOut[i]);
            }
        } else {
            for (int i = 0; i < testSet.length; i++) {
                assertEquals(testSet[i], stackOut[i]);
            }
        }
    }

    @Test(timeout = 200)
    public void testPop1() {
        Integer[] testSet = testSet1;
        for (Integer i: testSet) {
            stack.push(i);
        }
        for (int i = testSet.length - 1; i > -1; i--) {
            assertEquals(testSet[i], stack.pop());
            assertEquals(i, stack.size());
        }
    }

    @Test(timeout = 200)
    public void testStackSize1() {
        Integer[] testSet = testSet1;
        for (Integer i: testSet) {
            stack.push(i);
        }
        assertEquals(testSet.length, stack.size());
    }

    @Test(timeout = 200)
    public void testStackIsEmpty1() {
        Integer[] testSet = testSet1;
        for (Integer i: testSet) {
            stack.push(i);
        }
        assertFalse(stack.isEmpty());
    }

    @Test(timeout = 200)
    public void testPushPopEmpty1() {
        Integer[] testSet = testSet1;
        for (int i = 0; i < testSet.length; i++) {
            stack.push(testSet[i]);
            assertEquals(i + 1, stack.size());
        }
        for (int i = testSet.length - 1; i > -1; i--) {
            assertEquals(testSet[i], stack.pop());
            assertEquals(i, stack.size());
        }
        assertTrue(queue.isEmpty());
    }

    @Test(timeout = 200)
    public void testPushMany() {
        Integer[] testSet = testSetMany;
        for (Integer i: testSet) {
            stack.push(i);
        }
        Object[] stackOut = stack.toArray();
        if (testSet.length > 0 && stackOut[0] == testSet[testSet.length - 1]) {
            for (int i = 0; i < testSet.length; i++) {
                assertEquals(testSet[testSet.length - 1 - i], stackOut[i]);
            }
        } else {
            for (int i = 0; i < testSet.length; i++) {
                assertEquals(testSet[i], stackOut[i]);
            }
        }
    }

    @Test(timeout = 200)
    public void testPopMany() {
        Integer[] testSet = testSetMany;
        for (Integer i: testSet) {
            stack.push(i);
        }
        for (int i = testSet.length - 1; i > -1; i--) {
            assertEquals(testSet[i], stack.pop());
            assertEquals(i, stack.size());
        }
    }

    @Test(timeout = 200)
    public void testStackSizeMany() {
        Integer[] testSet = testSetMany;
        for (Integer i: testSet) {
            stack.push(i);
        }
        assertEquals(testSet.length, stack.size());
    }

    @Test(timeout = 200)
    public void testStackIsEmptyMany() {
        Integer[] testSet = testSetMany;
        for (Integer i: testSet) {
            stack.push(i);
        }
        assertFalse(stack.isEmpty());
    }

    @Test(timeout = 200)
    public void testPushPopEmptyMany() {
        Integer[] testSet = testSetMany;
        for (int i = 0; i < testSet.length; i++) {
            stack.push(testSet[i]);
            assertEquals(i + 1, stack.size());
        }
        for (int i = testSet.length - 1; i > -1; i--) {
            assertEquals(testSet[i], stack.pop());
            assertEquals(i, stack.size());
        }
        assertTrue(queue.isEmpty());
    }
}