import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MaxHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] arr;
    private int size;
    // Do not add any more instance variables

    /**
     * Creates a MaxHeap.
     */
    public MaxHeap() {
        arr = (T[]) new Comparable[STARTING_SIZE];
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("The input item is null.");
        }



        if (size == arr.length - 1) {
            // double the size & copy to the new array
            T [] temp = (T[]) new Comparable[2 * arr.length];
            for (int i = 1; i <= size; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }

        int curr = size + 1;
        arr[curr] = item;
        size++;

        while (curr > 1 && arr[curr].compareTo(arr[curr / 2]) > 0) {
            swap(arr, curr, curr / 2);
            curr = curr / 2;
        }
    }

    /**
     * Swap the values of two elements stored in a given array.
     * The indexes of the elements are given
     *
     * @param arr the given array
     * @param index1 the index of the one of the element to be swapped
     * @param index2 the index of the other element to be swapped
     */
    private void swap(T[] arr, int index1, int index2) {
        T temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }


    @Override
    public T remove() {
        //replace the root key with the key of the last node w
        // remove w
        //restore the head-order property (heapify)

        if (size == 0) {
            throw new NoSuchElementException("The heap is empty.");
        }

        T max = arr[1];

        if (size == 1) {
            arr[1] = null;
            size--;
            return max;
        }

        T last = arr[size];
        arr[size] = null;
        size--;
        arr[1] = last;

        if (size > 1) {
            heapify();
        }
        return max;

    }

    /**
     * Heapify the back array arr: to ensure that arr fulfils the
     * requirements of being a max heap
     *
     */
    private void heapify() {
        int maxChild;
        boolean doHeapify = true;
        int curr = 1;
        while (2 * curr <= size && doHeapify) {
            if (arr[2 * curr + 1] != null) {
                int comp = arr[2 * curr].compareTo(arr[2 * curr + 1]);
                if (comp > 0) {
                    maxChild = 2 * curr;
                } else {
                    maxChild = 2 * curr + 1;
                }
            } else {
                maxChild = 2 * curr;
            }

            int comp = arr[curr].compareTo(arr[maxChild]);
            if (comp >= 0) {
                doHeapify = false;
            } else {
                swap(arr, curr, maxChild);
            }
            curr = maxChild;
        }
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        arr = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        return arr;
    }
}
