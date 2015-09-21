import org.junit.Before;
import org.junit.Test;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;


public class IteratorTests {
    Iterator fibIterator0;
    Iterator fibIterator1;
    Iterator fibIterator6;
    Iterator fibIterator10;

    SquareMatrix<Integer> emptyMatrix;
    Iterator emptyIterator;

    SquareMatrix<Integer> size1Matrix;
    Iterator size1Iterator;

    SquareMatrix<Integer> size4Matrix;
    Iterator size4Iterator;

    SquareMatrix<Integer> size9Matrix;
    Iterator size9Iterator;



    @Before
    public void setUp() {
        fibIterator0 = new FibonacciIterator(0);
        fibIterator1 = new FibonacciIterator(1);
        fibIterator6 = new FibonacciIterator(6);
        fibIterator10 = new FibonacciIterator(10);

        emptyMatrix = new SquareMatrix<>(0);
        emptyIterator = emptyMatrix.iterator();

        size1Matrix = new SquareMatrix<>(1);
        size1Matrix.set(0, 0, 0);
        size1Iterator = size1Matrix.iterator();

        size4Matrix = new SquareMatrix<>(2);
        fillMatrix(size4Matrix, 2);
        size4Iterator = size4Matrix.iterator();

        size9Matrix = new SquareMatrix<>(3);
        fillMatrix(size9Matrix, 3);
        size9Iterator = size9Matrix.iterator();
    }

    private void fillMatrix(SquareMatrix<Integer> matrix, int size) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix.set(i, j, count++);
            }
        }
    }

    /**
     * FIBONACCI TESTS
     */

    @Test (expected = NoSuchElementException.class)
    public void testFib0() {
        assertFalse(fibIterator0.hasNext());
        fibIterator0.next();
    }

    @Test
    public void testFib1HasNext() {
        assertTrue(fibIterator1.hasNext());
        fibIterator1.next();
        assertFalse(fibIterator1.hasNext());
    }

    @Test
    public void testFib1Next() {
        assertEquals(new Integer(0), fibIterator1.next());
    }

    @Test
    public void testFib6HasNext() {
        for (int i = 0; i < 6; i++) {
            assertTrue(fibIterator6.hasNext());
            fibIterator6.next();
        }
        assertFalse(fibIterator6.hasNext());
    }

    @Test
    public void testFib6Next() {
        int[] fibSeq = {0, 1, 1, 2, 3, 5};
        for (int i = 0; i < 6; i++) {
            assertEquals(fibSeq[i], fibIterator6.next());
        }
    }

    @Test
    public void testFibComprehensive() {
        int[] fibSeq = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};
        for (int i = 0; i < fibSeq.length; i++) {
            assertTrue(fibIterator10.hasNext());
            assertEquals(fibSeq[i], fibIterator10.next());
        }
    }

    /**
     * SQUARE MATRIX TESTS
     */

    @Test
    public void testSquareHasNextEmpty() {
        assertFalse(emptyIterator.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void testSquareEmptyNextException() {
        emptyIterator.next();
    }

    @Test
    public void testSquare1HasNext() {
        assertTrue(size1Iterator.hasNext());
        size1Iterator.next();
        assertFalse(size1Iterator.hasNext());
    }

    @Test
    public void testSquare1Next() {
        assertEquals(0, size1Iterator.next());
    }

    @Test
    public void testSquare4HasNext() {
        for (int i = 0; i < 4; i++) {
            assertTrue(size4Iterator.hasNext());
            size4Iterator.next();
        }
        assertFalse(size4Iterator.hasNext());
    }

    @Test
    public void testSquare4Next() {
        int[] expected = {0, 2, 1, 3};
        for (int i = 0; i < 4; i++) {
            assertEquals(expected[i], size4Iterator.next());
        }
    }

    @Test
    public void testSquare4NextException() {
        for (int i = 0; i < 9; i++){
            size9Iterator.next();
        }
        try {
            size9Iterator.next();
            fail();
        }
        catch (NoSuchElementException e) {

        }
    }

    @Test
    public void testSquareComprehensive() {
        int[] expected = {0, 3, 6, 1, 4, 7, 2, 5, 8};
        for (int i = 0; i < 9; i++) {
            assertTrue(size9Iterator.hasNext());
            assertEquals(expected[i], size9Iterator.next());
        }
        assertFalse(size9Iterator.hasNext());
    }
}
