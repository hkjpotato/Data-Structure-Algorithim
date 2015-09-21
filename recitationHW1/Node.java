/**
 * The node class.
 *
 * @author CS 1332 TAs
 */
public class Node<T> {
    private T data;
    private Node<T> next;
    private Node<T> previous;

    public Node(final T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(final Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(final Node<T> previous) {
        this.previous = previous;
    }
}
