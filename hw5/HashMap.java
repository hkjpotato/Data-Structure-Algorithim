import java.util.*;

public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries.
     */
    public HashMap() {
        // Initialize your hashtable here.
        table =  (MapEntry<K, V> [])new MapEntry[STARTING_SIZE];
    }

    @Override
    public V add(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("The input key is null.");
        }

        if (value == null) {
            throw new IllegalArgumentException("The input value is null.");
        }

        //TODO regrow the table
        if ((size + 1) > table.length * MAX_LOAD_FACTOR) {
            table = regrow(table);
        }

//        return addHelper(table, key, value);

        return addHelper1(key, value);
    }

    private V addHelper(MapEntry<K, V>[] arr, K key, V value) {
//        int hashCode = Math.abs(key.hashCode());
//        int index = hashCode % arr.length;
//        int initIndex = index;
//        boolean foundRemovedIndex = false;
//        int removedIndex = -1;




//        //The correct version1
//        for (int i = 1; i <= arr.length; i++) {
//            if (arr[index] == null) {
//                if(foundRemovedIndex) {
//                    arr[removedIndex] = new MapEntry<>(key, value);
//                    arr[removedIndex].setRemoved(false);
//                    size++;
//                    return null;
//                } else {
//                    arr[index] = new MapEntry<>(key, value);
//                    arr[index].setRemoved(false);
//                    size++;
//                    return null;
//                }
//            } else {
//                if (arr[index].getKey().equals(key)) {
//                    if (arr[index].isRemoved()) {
//                        arr[index] = new MapEntry<>(key, value);
//                        arr[index].setRemoved(false);
//                        size++;
//                        return null;
//                    } else {
//                        V ret = arr[index].getValue();
//                        arr[index].setValue(value);
//                        // cannot increase size in this case
//                        return ret;
//                    }
//                } else {
//                    if (arr[index].isRemoved() && !foundRemovedIndex) {
//                        removedIndex = index;
//                        foundRemovedIndex = true;
//                    }
//
//                    index = (initIndex + i * i) % arr.length;
//                }
//            }
//        }
//
//        MapEntry<K, V>[] newTable = regrow(arr);
//        return addHelper(newTable, key, value);


        int hashCode = Math.abs(key.hashCode());
        int index = hashCode % arr.length;
        int initIndex = index;
        boolean foundRemovedIndex = false;
        int removedIndex = -1;
        int increment = 1;

        while (arr[index] != null && increment <= arr.length) {
            if (arr[index].getKey().equals(key)) {
                if (arr[index].isRemoved()) {
                    arr[index] = new MapEntry<>(key, value);
                    arr[index].setRemoved(false);
                    size++;
                    return null;
                } else {
                    V ret = arr[index].getValue();
                    arr[index].setValue(value);
                    return ret;
                }
            } else if (arr[index].isRemoved() && !foundRemovedIndex) {
                removedIndex = index;
                foundRemovedIndex = true;
            }
            index = (initIndex + increment * increment) % arr.length;
            increment++;
        }


        if(foundRemovedIndex) {
            arr[removedIndex] = new MapEntry<>(key, value);
            arr[removedIndex].setRemoved(false);
            size++;
            return null;
        } else if (increment > arr.length) {
            arr = regrow(arr);
            return addHelper(arr, key, value);
        } else {
            arr[index] = new MapEntry<>(key, value);
            arr[index].setRemoved(false);
            size++;
            return null;
        }



    }


    private MapEntry<K, V>[] regrow(MapEntry<K, V>[] arr) {
        MapEntry<K, V>[] temTable = arr;
        arr = (MapEntry<K, V>[]) new MapEntry[2 * arr.length + 1];
        size = 0;

        for (MapEntry<K, V> entry : temTable) {
            if (entry != null && !entry.isRemoved()) {
                addHelper(arr, entry.getKey(), entry.getValue());
            }
        }
        return arr;
    }

    private V addHelper1(K key, V value) {
        int hashCode = Math.abs(key.hashCode());
        int index = hashCode % table.length;
        int initIndex = index;
        boolean foundRemovedIndex = false;
        int removedIndex = -1;
        int increment = 1;

        while (table[index] != null && increment <= table.length) {
            if (table[index].getKey().equals(key)) {
                if (table[index].isRemoved()) {
                    table[index] = new MapEntry<>(key, value);
                    table[index].setRemoved(false);
                    size++;
                    return null;
                } else {
                    V ret = table[index].getValue();
                    table[index].setValue(value);
                    return ret;
                }
            } else if (table[index].isRemoved() && !foundRemovedIndex) {
                removedIndex = index;
                foundRemovedIndex = true;
            }
            index = (initIndex + increment * increment) % table.length;
            increment++;
        }


        if(foundRemovedIndex) {
            table[removedIndex] = new MapEntry<>(key, value);
            table[removedIndex].setRemoved(false);
            size++;
            return null;
        } else if (increment > table.length) {
            regrowBacking();
            return addHelper1(key, value);
        } else {
            table[index] = new MapEntry<>(key, value);
            table[index].setRemoved(false);
            size++;
            return null;
        }



    }

    private void regrowBacking() {
        MapEntry<K, V>[] temTable = table;
        table = (MapEntry<K, V>[]) new MapEntry[2 * table.length + 1];
        size = 0;

        for (MapEntry<K, V> entry : temTable) {
            if (entry != null && !entry.isRemoved()) {
                addHelper1(entry.getKey(), entry.getValue());
            }
        }
    }


    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The input key is null.");
        }

        int hashCode = Math.abs(key.hashCode());
        int index = hashCode % table.length;
        int initIndex = index;

        int increment = 1;
        while (table[index] != null && increment <= table.length) {
            if (table[index].getKey().equals(key)) {
                if (table[index].isRemoved()) {
                    throw new NoSuchElementException("No such element is not in the map.");
                } else {
                    table[index].setRemoved(true);
                    size--;
                    return table[index].getValue();
                }
            }
            index = (initIndex + increment * increment) % table.length;
            increment++;
        }

        throw new NoSuchElementException("The key is not in the map.");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The input key is null.");
        }

        int hashCode = Math.abs(key.hashCode());
        int index = hashCode % table.length;
        int initIndex = index;

        int increment = 1;
        while (table[index]!= null && increment <= table.length) {
            if (table[index].getKey().equals(key)) {
                if (!table[index].isRemoved()) {
                    return table[index].getValue();
                }
                else {
                    throw new NoSuchElementException("The key is not in the map.");
                }
            }

            index = (initIndex + increment * increment) % table.length;
            increment++;
        }

        throw new NoSuchElementException("The key is not in the map.");

    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The input key is null.");
        }

        int hashCode = Math.abs(key.hashCode());
        int index = hashCode % table.length;
        int initIndex = index;

        int increment = 1;
        while (table[index] != null && increment <= table.length) {
            if (table[index].getKey().equals(key)) {
                if ( !table[index].isRemoved()) {
                    return true;
                }
                else {
                    return false;
                }
            }
            index = (initIndex + increment * increment) % table.length;
            increment++;
        }
        return false;
    }

    @Override
    public void clear() {
        table =  (MapEntry<K, V> [])new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public MapEntry<K, V>[] toArray() {
        return table;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (MapEntry<K, V> entry : table) {
            if (entry != null && !entry.isRemoved()) {
                set.add(entry.getKey());
            }
        }
        return set;
    }

    @Override
    public List<V> values() {
        List list = new ArrayList<>();
        for (MapEntry<K, V> entry : table) {
            if (entry != null && !entry.isRemoved()) {
                list.add(entry.getValue());
            }
        }
        return list;
    }

}
