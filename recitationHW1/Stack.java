/**
 * Recitation #2 1/21/15.
 * 
 * @author Your Name Here
 */
public class Stack<T> implements StackADT<T> {

    // Do not add any instance variables
    private LinkedList<T> list;

    public Stack() {
        this.list = new LinkedList<T>();
    }

    @Override
    public void push(T data) {
        // implement this method
    }

    @Override
    public T pop() {
        // implement this method
        return null;
    }

    @Override
    public int size() {
        // implement this method
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // implement this method
        return false;
    }

    /**
     * Do not touch - These are for grading purposes
     */
    public Object[] toArray() {
        return list.toArray();
    }

    /**
     * Do not touch - These are for grading purposes
     */
    public void setList(final LinkedList<T> list) {
        this.list = list;
    }
}
