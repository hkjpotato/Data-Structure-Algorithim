/**
 * Interface for a Queue ADT
 *
 * DO NOT MODIFY THIS FILE
 * 
 * @author CS 1332 TAs
 */
public interface QueueADT<T> {

    /**
     * Add a node with the given data to the back of your queue.
     *
     * Must be O(1) (amortized for array-backed)
     *
     * @param data
     *            The data to add.
     * @throws IllegalArgumentException
     *             if data is null
     */
    void enqueue(T data);

    /**
     * Dequeue from the front of your queue.
     *
     * Must be O(1)
     *
     * @return The data from the front node.
     * @throws java.util.NoSuchElementException
     *             if the queue is empty
     */
    T dequeue();

    /**
     * Return the size of the queue.
     * 
     * Must be O(1)
     *
     * @return number of items in the queue
     */
    int size();

    /**
     * Return true if empty. False otherwise.
     * 
     * Must be O(1)
     *
     * @return boolean representing whether the list is empty
     */
    boolean isEmpty();
}