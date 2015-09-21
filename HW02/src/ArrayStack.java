import java.util.NoSuchElementException;

/**
 * ArrayStack
 * Implementation of a stack using
 * an array as a backing structure
 *
 * @author Your Kaijie Huang
 * @version 1.0
 */
public class ArrayStack<T> implements StackADT<T> {

    // Do not add instance variables
    private T[] backing;
    private int size;

    /**
     * Construct an ArrayStack with
     * an initial capacity of INITIAL_CAPACITY.
     *
     * Use constructor chaining.
     */
    public ArrayStack() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Construct an ArrayStack with the specified
     * initial capacity of initialCapacity
     * @param initialCapacity Initial capacity of the backing array.
     */
    public ArrayStack(int initialCapacity) {
        backing = (T[]) new Object[initialCapacity];
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null");
        } else if (size == backing.length) {
            //Regrow the backing array.
            T[] temArr = (T[]) new Object[backing.length * 2];
            for (int i = 0; i < size; i++) {
                temArr[i] = backing[i];
            }
            backing = temArr;

            push(data);
        } else {
            backing[size] = data;
            size++;
        }
    }

    @Override
    public T pop() {
        //FIXME
        if (size == 0) {
            throw new NoSuchElementException("The stack is empty.");
        } else {
            T ret = backing[size - 1];
            backing[size - 1] = null;
            size--;
            return ret;
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the backing array for your queue.
     * This is for grading purposes only. DO NOT EDIT THIS METHOD.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        return backing;
    }
}
