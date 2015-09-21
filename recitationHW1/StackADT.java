/**
 * Interface for a Stack ADT
 *
 * DO NOT MODIFY THIS FILE
 *
 * @author CS 1332 TAs
 */
public interface StackADT<T> {

    /**
     * Push a new node onto the stack with the given data.
     *
     * Must be O(1) (amortized for array-backed)
     *
     * @param data
     *            The data to push.
     * @throws IllegalArgumentException
     *             if data is null.
     */
    void push(T data);

    /**
     * Pop from the stack.
     *
     * Must be O(1)
     *
     * @return Data from node
     * @throws java.util.NoSuchElementException
     *             if the stack is empty.
     */
    T pop();

    /**
     * Return the size of the stack.
     *
     * Must be O(1)
     *
     * @return number of items in the stack
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