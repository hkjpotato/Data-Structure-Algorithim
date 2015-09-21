public class MaxPriorityQueue<T extends Comparable<? super T>>
    implements PriorityQueueInterface<T> {

    private MaxHeap<T> heap;
    // Do not add any more instance variables

    /**
     * Creates a MaxPriorityQueue
     */
    public MaxPriorityQueue() {
        heap = new MaxHeap<>();
    }

    @Override
    public void enqueue(T item) {
        heap.add(item);
    }

    @Override
    public T dequeue() {
        return heap.remove();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public int size() {
        return heap.size();
    }


    @Override
    public void clear() {
        heap.clear();
    }

    @Override
    public MaxHeap<T> getBackingHeap() {
        return heap;
    }
}
