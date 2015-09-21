import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/**
 * Created by kaijiehuang on 15-3-28.
 */
public class KJSortingTest {

    /**
     * Integer comparator for some tests.......yeah.
     * @return  Comparator that can do basic subtraction. Woah.
     */
    private Comparator<Integer> getIntegerComparator() {
        return new Comparator<Integer>() {
            @Override
            public int compare(Integer integer1, Integer integer2) {
                return integer1 - integer2;
            }
        };
    }

    private Integer[] integers;
    private Integer[] result;
    @Before
    public void setup() {
        integers = new Integer[] {
//                2, 4, 2
                5, 2, 4, 3, 6

        };

        result = new Integer[] {
//                2, 2, 4
                2, 3, 4, 5, 6
        };
    }

    @Test
    public void testQuickSort() {
        SortingOriginal.quicksort(integers, getIntegerComparator(), new Random(0x600dc0de));
        for (int i = 0; i < integers.length; i++) {
            System.out.print(integers[i] + " ");
            assertTrue(integers[i].equals(result[i]));
        }

    }





}
