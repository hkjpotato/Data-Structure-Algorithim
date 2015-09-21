import java.util.NoSuchElementException;

/**
 * A doubly LinkedList implementation.
 *
 * @author CS 1332 TAs
 */
public class LinkedList<T> {

    private Node<T> head, tail;
    private int size;

    public void addToFront(final T data) {
        if (null == data) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            head = new Node<T>(data);
            tail = head;
        } else {
            final Node<T> newHead = new Node<T>(data);
            newHead.setNext(head);
            head.setPrevious(newHead);
            head = newHead;
        }
        size++;
    }

    public void addToBack(final T data) {
        if (null == data) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            head = new Node<T>(data);
            tail = head;
        } else {
            final Node<T> newTail = new Node<T>(data);
            newTail.setPrevious(tail);
            tail.setNext(newTail);
            tail = newTail;
        }
        size++;
    }

    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final T data = head.getData();
        if (size == 1) {
            clear();
        } else {
            head = head.getNext();
            head.setPrevious(null);
            size--;
        }
        return data;
    }

    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        final T data = tail.getData();
        if (size == 1) {
            clear();
        } else {
            tail = tail.getPrevious();
            tail.setNext(null);
            size--;
        }
        return data;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public Object[] toArray() {
        if (null == head) {
            return null;
        }
        final Object[] array = new Object[size];
        Node<T> current = head;
        for (int i = 0; i < size(); i++) {
            array[i] = current.getData();
            current = current.getNext();
        }
        return array;

    }
}
