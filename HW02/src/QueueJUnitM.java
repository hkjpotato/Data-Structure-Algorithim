

import org.junit.Before;
        import org.junit.Test;

        import java.lang.reflect.Field;
        import java.util.Arrays;
        import java.util.LinkedList;
        import java.util.Queue;

/**
 * Created by mde on 1/23/15.
 */
public class QueueJUnitM {

    public ArrayQueue<Integer> okQueue;
    public Queue<Integer>      goodQueue;
    public Field back, front;

    private static final int ROUGHNESS = 128;

    @Before
    public void setup() {
        okQueue   = new ArrayQueue<>();
        goodQueue = new LinkedList<>();

        try {
            back = okQueue.getClass().getDeclaredField("back");
            back.setAccessible(true);

            front = okQueue.getClass().getDeclaredField("front");
            front.setAccessible(true);
        }
        catch (NoSuchFieldException err) {
            System.out.println("Make sure you're ArrayQueue class has "
                    + "front and back instance variables.");
        }
    }

    @Test
    public void nullTest() {
        try {
            okQueue.enqueue(null);
        }
        catch (IllegalArgumentException err) {
            System.out.println("Null check is OK");
        }
        catch (Exception err) {
            System.out.println("Did not handle null correctly...");
            throw err;
        }
    }

    @Test
    public void hardTest() {
        for (int i = 0; i < ROUGHNESS; ++i) {
            for (int forward = 0; forward < i; ++forward) {
                enqueue(~forward);
                System.out.print('+');
            }

            if (okQueue.size() > ArrayQueue.INITIAL_CAPACITY) {
                assert okQueue.getBackingArray().length <= okQueue.size() * 2;
            }

            for (int backward = 0; backward < i; ++backward) {
                dequeque();
                System.out.print('-');
            }

            try {
                assert getFront().equals(getBack());
            }
            catch (AssertionError err) {
                System.out.println("\nFront and back do not go back to 0 when queue empty");
                dumpInfo();
                throw err;
            }
            System.out.println();
        }

    }

    private void checkQueues() {
        assert okQueue.size() <= okQueue.getBackingArray().length;

        Object[] normalized = new Object[okQueue.size()];

        int i, j;
        int front = getFront();
        for (i = front, j = 0; j < okQueue.size(); i = (i + 1) % okQueue.getBackingArray().length, ++j) {
            normalized[j] = okQueue.getBackingArray()[i];
        }

        for (; i != front; i = (i + 1) % okQueue.getBackingArray().length) {
            try {
                assert okQueue.getBackingArray()[i] == null;
            }
            catch (AssertionError err) {
                System.out.println("\nDid not null-out deleted elements!");
                dumpInfo();
                throw err;
            }
        }

        String badQueue = Arrays.toString(normalized);
        String btrQueue = Arrays.toString(goodQueue.toArray());

        try {
            assert badQueue.equals(btrQueue);
        }
        catch (AssertionError err) {
            System.out.println("\nYour Queue (ordered): " + badQueue);
            dumpInfo();
            throw err;
        }
    }

    private Integer getBack() {
        try {
            return (Integer) back.get(okQueue);
        }
        catch (IllegalAccessException err) {
            return null;
        }
    }

    private Integer getFront() {
        try {
            return (Integer) front.get(okQueue);
        }
        catch (IllegalAccessException err) {
            return null;
        }
    }

    private void enqueue(int data) {
        okQueue.enqueue(data);
        goodQueue.add(data);
        checkQueues();
    }

    private void dequeque() {
        okQueue.dequeue();
        goodQueue.remove();
        checkQueues();
    }

    private void dumpInfo() {
        String btrQueue = Arrays.toString(goodQueue.toArray());
        String backing = Arrays.toString(okQueue.getBackingArray());
        System.out.println("Your Queue:   " + backing);
        System.out.println("Java's Queue: " + btrQueue);
        System.out.println("Front Index:  " + getFront());
        System.out.println("Back Index:   " + getBack());
    }
}