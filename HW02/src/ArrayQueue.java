import java.util.NoSuchElementException;

/**
 * ArrayQueue
 * Implementation of a queue using
 * an array as the backing structure
 *
 * @author Your Kaijie Huang
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueADT<T> {

    // Do not add instance variables
    private T[] backing;
    private int size;
    private int front;
    private int back;

    /**
     * Construct an ArrayQueue with an
     * initial capacity of INITIAL_CAPACITY
     *
     * Use Constructor Chaining
     */
    public ArrayQueue() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Construct an ArrayQueue with the specified
     * initial capacity of initialCapacity
     * @param initialCapacity Initial capacity of the backing array.
     */
    public ArrayQueue(int initialCapacity) {
        backing = (T[]) new Object[initialCapacity];
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        } else if (size == backing.length) {
            //Regrow the backing array.
            T[] temArr = (T[]) new Object[backing.length * 2];


            // what is this trying to do
            // we need to rearrange x x 1 2 3 into 1 2 3
            // backing length is still 5
            //front is 2
            // tempArr[0] = (front + 0)% backing.length

            // for 4 5 x x 1 2 3 not full
            // for 4 5 1 2 3
            // want to rearrange into 1 2 3 4 5
            // 0 -> front
            // 1 -> front + 1
            // for wrap one
            // if want to approach front + i
            // need to use (front + i) % backing.length
            // for example, 4 is actually at 2(1) + 3 = 5
            // but here in the wrap, it's at 5 % 5 = 0

            for (int i = 0; i < size; i++) {
                temArr[i] = backing[(front + i) % backing.length];
            }
            front = 0;
            back = front + size;
            backing = temArr;

            enqueue(data);

        } else {
            backing[back] = data;
            back = (back + 1) % backing.length;
            size++;
        }
    }

    @Override
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("The queue is empty.");
        } else {
            T ret = backing[front];
            backing[front] = null;
            front = (front + 1) % backing.length;
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
