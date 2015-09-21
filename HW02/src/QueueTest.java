/**
 * Created by kaijiehuang on 1/23/15.
 */

import junit.framework.TestCase;
        import org.junit.Before;
        import org.junit.Test;

        import java.util.NoSuchElementException;
        import static junit.framework.TestCase.*;

public class QueueTest {

    public ArrayQueue<Integer> queue;

    @Before
    public void setup() {
        queue = new ArrayQueue<Integer>(1);
    }

    @Test(timeout = 200)
    public void testConstructors() {
        queue = new ArrayQueue<Integer>();
        assertEquals("Default constructor not using default backing array length.", 10, queue.getBackingArray().length);

        queue = new ArrayQueue<Integer>(2);
        TestCase.assertEquals("Constructor did not set capacity of backing array correctly.", 2, queue.getBackingArray().length);
    }

    @Test(timeout = 300)
    public void testResizing() {
        /*
         * Size starts at one and doubles, so the backing array length will always be a power of 2.
         * We will check to see if it resizes only at these thresholds.
         *
         * We will also be adding 2 to the 19th numbers to the data structure, so if you have any
         * n-squared operations related to adding or resizing, you'll probably timeout.
         */

        int expectedSize = 1;

        //so that the loop always starts with a resize//
        queue.enqueue(0);

        //We will check through 19 shifts//
        for (int i = 1; i < 20; i++) {

            //difference between last resize next resize//
            int numberOfElementsToAdd = (1 << i) - (1 << (i - 1));

            //bitshift to multiply expected size by two//
            expectedSize <<= 1;

            for (int j = 0; j < numberOfElementsToAdd; j++) {
                queue.enqueue(0);

                TestCase.assertEquals("Resized at wrong time (size " + queue.getBackingArray().length + ")",
                        expectedSize, queue.getBackingArray().length);
            }
        }
    }

    @Test(timeout = 200)
    public void testResizeWithWraparound() {
        //[0, 1, ... 1023]//
        for (int i = 0; i < 1024; i++) {
            queue.enqueue(i);
        }

        //dequeue three//
        //[null, null, null, 3, 4, ..., 1023]//
        for (int i = 0; i < 3; i++) {
            queue.dequeue();
        }

        //enqueue 4 elements to cause a resize//
        //when third element added: [1024, 1025, 1026, 3, 4, ..., 1023]//
        //after fourth element added: [3, 4, ..., 1027, null, ..., null]//
        for (int i = 1024; i < 1028; i++) {
            queue.enqueue(i);
        }

        for (int i = 3; i < 1028; i++) {
            assertEquals("Error resizing after wraparound", i,
                    (int) queue.dequeue());
        }
    }

    @Test(timeout = 200)
    public void testEnqueueWithoutWrapAround() {

        //[1, 2, ... 999]//
        for (int i = 0; i < 1000; i++) {
            queue.enqueue(i);
        }

        Object[] ints = queue.getBackingArray();

        for (int i = 0; i < 1000; i++) {
            assertEquals("Incorrect element at index " + i, i, (int) ints[i]);
        }
    }

    @Test(timeout = 200)
    public void testDequeueWithoutWrapAround() {
        //[0, 1, ... 999]//
        for (int i = 0; i < 1000; i++) {
            queue.enqueue(i);
        }

        Object[] ints =  queue.getBackingArray();

        for (int i = 0; i < 1000; i++) {
            assertEquals("Incorrect element at index " + i, i, (int) queue.dequeue());

            //should be nulling out dequeued elements//
            assertNull("Unused array index not null at index " + i, ints[i]);
        }
    }

    @Test(timeout = 200)
    public void testBackWraparound() {

        //[1, 2, ..., 1023]//
        for (int i = 0; i < 1024; i++) {
            queue.enqueue(i);
        }

        //[null, null, ..., null, 500, 501, ..., 1023]//
        for (int i = 0; i < 500; i++) {
            queue.dequeue();
        }

        //[0, 1, ..., 1023]//
        for (int i = 0; i < 500; i++) {
            queue.enqueue(i);
        }

        Object[] ints = queue.getBackingArray();

        for (int i = 0; i < 1024; i++) {
            assertEquals("Did not wrap around correctly. Error at index " + i, i, ints[i]);
        }
    }

    @Test(timeout = 200)
    public void testFrontWraparound() {

        //[1, 2, ..., 1023]//
        for (int i = 0; i < 1024; i++) {
            queue.enqueue(i);
        }

        //[null, null, ..., null, 512, 513, ..., 1023]//
        for (int i = 0; i < 512; i++) {
            queue.dequeue();
        }

        //[0, 1, ..., 1023]//
        for (int i = 0; i < 512; i++) {
            queue.enqueue(i);
        }

        //back and front should now both be at 512//

        //[null, 1, 2, ..., 511, null, ..., null]//
        for (int i = 0; i < 513; i++) {
            queue.dequeue();
        }


        Object[] ints = queue.getBackingArray();

        assertNull("first index should be null", ints[0]);

        for (int i = 1; i < 512; i++) {
            assertEquals("error in front wrap-around at index " + i, i, ints[i]);
        }
    }

    @Test(timeout = 200)
    public void testSize() {

        //[1, 2, ..., 1023]//
        for (int i = 1; i < 1024; i++) {
            queue.enqueue(i);
            assertEquals("Size incorrect while enqueueing at index " + i, i, queue.size());
        }

        for (int i = 1022; i >= 0; i--) {
            queue.dequeue();
            assertEquals("Size incorrect while dequeueing at index " + i, i, queue.size());
        }

        //if size is affected by wrap-around, other things will break//
    }

    @Test(timeout = 200)
    public void testIsEmpty() {
        assertTrue("should be empty before anything has been added", queue.isEmpty());

        queue.enqueue(0);

        assertFalse("queue shouldn't be empty!", queue.isEmpty());

        queue.dequeue();


        //one-element wrap-around//

        queue.enqueue(0);

        assertFalse("queue shouldn't be empty!", queue.isEmpty());
    }


    /*
     * TESTING EXCEPTIONS
     */

    @Test(expected = IllegalArgumentException.class)
    public void testEnqueueNullException() {
        queue.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueWhenEmptyException() {
        queue.dequeue();
    }
}