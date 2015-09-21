import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class for implementing your HashMap with external chaining
 *
 * Created by CS1332TAs on 2/11/15.
 */
public class HashMap<K,V> implements HashMapInterface<K,V> {
    private MapEntry<K, V>[] backing;
    private int size;

    public HashMap() {
        this.backing = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public void add(K key, V value) {

        if (key == null) {
            throw new IllegalArgumentException("The input key is null.");
        }

        if (value == null) {
            throw new IllegalArgumentException("The input value is null.");
        }

        if ((size + 1) >= MAX_LOAD_FACTOR * backing.length) {
            //creat a reference temp to refer to the current backing array
            MapEntry<K, V>[] temp = backing;
            //regrow the backing array
            backing = (MapEntry<K, V>[]) new MapEntry[backing.length * 2 + 1];
            //resize new array to 0;
            size = 0;

            //rehash all entry into the new backing array
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] != null) {
                    addHelper(backing, temp[i].getKey(), temp[i].getValue());
                    MapEntry<K, V> curr = temp[i];
                    while (curr.getNext() != null) {
                        addHelper(backing, curr.getNext().getKey(), curr.getNext().getValue());
                        curr = curr.getNext();
                    }
                }
            }
        }

        addHelper(backing, key, value);

    }

    /**
     * Helper method for add method. Add entry to the given arr
     *
     * @param arr input the entry into this given array
     * @param key the key of the entry
     * @param value the value of the entry
     */
    private void addHelper(MapEntry<K, V>[] arr, K key, V value) {
        int index = Math.abs(key.hashCode()) % arr.length;
        MapEntry<K, V> curr;
        curr = arr[index];

        if (curr == null) {
            arr[index] = new MapEntry<>(key, value);
        } else {
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            curr.setNext(new MapEntry<>(key, value));
        }
        size++;
    }

    @Override
    public Set<MapEntry<K, V>> getEntrySet() {
       Set<MapEntry<K, V>> set = new HashSet<>();
       for (MapEntry<K, V> entry : backing) {
           if (entry != null) {
               set.add(entry);
               MapEntry<K, V> curr = entry;
               while (curr.getNext() != null) {
                   set.add(curr.getNext());
                   curr = curr.getNext();
               }
           }
       }
       return set;
    }

    @Override
    public MapEntry<K, V>[] getBackingArray() {
        return backing;
    }

    @Override
    public int size() {
        return size;
    }
}
