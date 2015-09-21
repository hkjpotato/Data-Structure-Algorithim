import java.util.Set;

/**
 * Interface for a HashMap with external chaining.
 *
 * Created by CS1332TAs on 2/11/15.
 */
public interface HashMapInterface<K,V> {
    int STARTING_SIZE = 10;
    double MAX_LOAD_FACTOR = 0.5;

    /**
     * size = 10;
     * 4 val1, 14 val2, 4 val3
     *
     *
     *
     * Adds the given key-value pair to the HashMap.
     * If the key is already in the HashMap, then append this entry to the
     * end of the chain for the particular key.
     *
     * Check to see if the backing array needs to be regrown BEFORE
     * adding. For example, If my HashMap has a backing array of size 5, and 3
     * elements in it, I should regrow at the start of the next add operation.
     * Regrow your backing array to 2*original_length + 1
     *
     * Should run in O(1) with a good hash function, O(n) otherwise.
     * @param key
     * @param value
     * @throws IllegalArgumentException if key or value is null
     */
    void add(K key, V value);

    /**
     * Returns a Set view of the entries contained in this map.
     * Use @code{java.util.HashSet}.
     * Should be O(n)
     *
     * @return the set of all entries
     */
    Set<MapEntry<K,V>> getEntrySet();

    /**
     * Returns the backing array where entries are stored in this map. DO NOT
     * MAKE A COPY.
     * Should be O(1)
     *
     * @return the backing array of entries
     */
    MapEntry<K,V>[] getBackingArray();


    /**
     * Returns the number of elements in the map.
     * Should be O(1)
     *
     * @return number of elements in the HashMap
     */
    int size();

}
