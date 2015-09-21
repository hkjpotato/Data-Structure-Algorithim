
import junit.framework.TestCase;
        import org.junit.Before;
        import org.junit.Test;

        import java.util.NoSuchElementException;

        import static junit.framework.Assert.assertEquals;
        import static junit.framework.TestCase.*;

/**
 * Created by Brian on 1/20/2015.
 */
public class StackTest {

    public ArrayStack<Integer> stack;

    @Before
    public void setUp() {
        stack = new ArrayStack<>(1);
    }

    @Test(timeout = 200)
    public void testConstructor() {
        stack = new ArrayStack<>();

        assertEquals("Stack is not using default backing array size with default constructor.", 10, stack.getBackingArray().length);
    }

    @Test(timeout = 200)
    public void testPush() {
        //backing array: [0, 1, ..., 1023]//
        for (int i = 0; i < 1024; i++) {
            stack.push(i);
        }

        Object[] ints = stack.getBackingArray();

        for (int i = 0; i < 1024; i++) {
            assertEquals("Value added incorrectly at index" + i, i, (int) ints[i]);
        }
    }

    @Test(timeout = 200)
    public void testPop() {
        //backing array: [0, 1, ..., 1023]//
        for (int i = 0; i < 1024; i++) {
            stack.push(i);
        }

        for (int i = 1023; i >= 0; i--) {
            assertEquals("Incorrectly popped value " + i, i, (int) stack.pop());
        }

        stack.push(0);
        TestCase.assertEquals("Issue adding after pop", 0, (int) stack.pop());
    }

    @Test(timeout = 200)
    public void testSize() {
        for (int i = 0; i < 1024; i++) {
            assertEquals("Size incorrect after " + i + "added", i, stack.size());

            stack.push(0);
        }

        for (int i = 1024; i > 0; i--) {
            assertEquals("Size incorrect after VALUE " + i + "removed", i, stack.size());

            stack.pop();
        }

        assertEquals("Size not reset to zero.", 0, stack.size());
    }

    @Test(timeout = 200)
    public void testIsEmpty() {
        assertTrue("Stack should be empty before anything added.", stack.isEmpty());

        stack.push(0);

        assertFalse("Stack should be empty before anything added.", stack.isEmpty());

        stack.pop();

        assertTrue("Stack not empty after all elements removed.", stack.isEmpty());
    }

    /*
     * Testing Exceptions
     */

    @Test(timeout = 200, expected = IllegalArgumentException.class)
    public void testPushNullException() {
        stack.push(null);
    }

    @Test(timeout = 200, expected = NoSuchElementException.class)
    public void testPopWhileEmptyException() {
        stack.pop();
    }
}