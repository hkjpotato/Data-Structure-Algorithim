/**
 * Created by kaijiehuang on 15-3-26.
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class sortingstable {
    private Item[] list;
    private Item[] stableSortedList;
    private static final int TIMEOUT = 200;

    /**
     * A fine example of multiply and surrender. Never ever ever use this
     * anywhere, it is absolutly horrible.
     *
     * Fun paper:
     * Pessimal Algorithms and Simplexity Analysis.
     *
     * @param arr The array of items to sort stably.
     */
    private Item[] slowsort(Item[] array) {

        // Base case.
        if (array.length < 2) {
            Item[] copy = new Item[array.length];
            System.arraycopy(
                    array,
                    0,
                    copy,
                    0,
                    array.length
            );

            return copy;
        }

        // Create our two sub-arrays.
        Item[] left = new Item[array.length / 2];
        Item[] right = new Item[array.length - array.length / 2];

        // Copy into the sub-arrays.
        System.arraycopy(
                array,
                0,
                left,
                0,
                left.length
        );
        System.arraycopy(
                array,
                array.length / 2,
                right,
                0,
                right.length
        );

        // Sort each half.
        left = slowsort(left);
        right = slowsort(right);

        // Extract the head.
        Item head = null;
        if (left[0].value <= right[0].value) {
            head = left[0];
            Item[] newLeft = new Item[left.length - 1];
            System.arraycopy(left, 1, newLeft, 0, left.length - 1);
            left = newLeft;
        } else {
            head = right[0];
            Item[] newRight = new Item[right.length - 1];
            System.arraycopy(right, 1, newRight, 0, right.length - 1);
            right = newRight;
        }

        // Return sorted array.
        Item[] sorted = new Item[array.length];
        sorted[0] = head;

        // Return head + sort(left + right)
        Item[] toSort = new Item[array.length - 1];
        System.arraycopy(left, 0, toSort, 0, left.length);
        System.arraycopy(right, 0, toSort, left.length, right.length);

        System.arraycopy(slowsort(toSort), 0, sorted, 1, toSort.length);

        return sorted;
    }

    @Before
    public void setup() {
        Random rand = new Random();

        ArrayList<Item> arraylist = new ArrayList<Item>();

        // The list is a list of 10 elements repeated 10 times.
        // insert them randomly into the list.
        // Used to cimplify following loop.
        arraylist.add(new Item(0));
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                arraylist.add(rand.nextInt(arraylist.size()), new Item(i));
            }
        }

        list = (Item[])arraylist.toArray(new Item[0]);

        stableSortedList = slowsort(list);
    }

    @Test(timeout = TIMEOUT)
    public void testBubbleSort() {
        Sorting.bubblesort(list, Item.comparator());
        assertArrayEquals(stableSortedList, list);
    }

    @Test(timeout = TIMEOUT)
    public void testInsertionSort() {
        Sorting.insertionsort(list, Item.comparator());
        assertArrayEquals(stableSortedList, list);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort() {
        Sorting.mergesort(list, Item.comparator());
        assertArrayEquals(list, stableSortedList);
    }

    private static class Item {
        public int value;
        private int special;
        private static Random specialGen;

        public Item(int value) {
            if (specialGen == null) {
                specialGen = new Random();
            }

            this.value = value;
            this.special = specialGen.nextInt();
        }

        public static Comparator<Item> comparator() {
            return (first, second)-> {
                return first.value - second.value;
            };
        }

        public String toString() {
            return value + ": " + special;
        }

        public boolean equals(Object other) {
            return other instanceof Item &&
                    ((Item) other).value == this.value &&
                    ((Item) other).special == this.special;
        }
    }
}