
public interface HeapInterface<T extends Comparable<? super T>> {

    public static final int STARTING_SIZE = 10;

    /**
     * Adds an item to the heap. If the backing array is full, double its size.
     *
     * @throws IllegalArgumentException if the item is null
     * @param item the item to be added to the heap
     */
    public void add(T item);

    /**
     * Removes and returns the first item of the heap.
     *
     * @throws java.util.NoSuchElementException if the heap is empty
     * @return the item removed
     */
    public T remove();

    /**
     * Finds if the heap is empty
     * @return a boolean representing if the heap is empty
     */
    public boolean isEmpty();

    /**
     * Finds the size of the heap
     * @return the size of the heap
     */
    public int size();

    /**
     * Clears the list and returns array to starting size.
     */
    public void clear();

    /**
     * Used for grading purposes only. Do not use or edit.
     * @return the backing array
     */
    public Comparable[] getBackingArray();
}
