import java.util.Map;

/**
 * Class for storing entries into your HashMap.
 *
 * Created by CS1332TAs on 2/11/15.
 */
public class MapEntry<K, V> {
    private K key;
    private V value;
    private MapEntry<K, V> next;

    public MapEntry(K k, V v) {
        key = k;
        value = v;
        next = null;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public MapEntry<K,V> getNext() {
        return next;
    }

    public void setNext(MapEntry<K,V> entry) {
        next = entry;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash += 11 * key.hashCode();
        hash += 13 * value.hashCode();
        if (next != null) {
            hash += 7 * next.hashCode();
        }
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        MapEntry<K, V> other = (MapEntry<K,V>) o;
        return key.equals(other.getKey()) && value.equals(other.getValue())
                && ((next != null && next.equals(other.getNext()))
                || (next == null && other.getNext() == null));
    }
}
