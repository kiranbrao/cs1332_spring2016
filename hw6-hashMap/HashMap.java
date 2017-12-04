import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Your implementation of HashMap.
 * 
 * @author Kiran Rao
 * @version 1.3
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code STARTING_SIZE}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(STARTING_SIZE);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = (MapEntry<K, V>[]) new MapEntry[initialCapacity];
    }

    @Override
    public V add(K key, V value) {
        if ((key == null) || (value == null)) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        double loadFactor = ((double) (size + 1) / (double) table.length);
        if (loadFactor > MAX_LOAD_FACTOR) {
            resizeBackingTable((2 * table.length) + 1);
        }
        V returnVal = add(key, value, table);
        if (returnVal == null) {
            size++;
        }
        return returnVal;
    }

    /**
     * Adds give key-value pair to the MapEntry array passed into the method
     * This method is called in the add method and the resize backing table
     * method
     *
     * @param key key to add into the HashMap
     * @param value value to add into the HashMap
     * @param currentTable the table to which the key and value are being added
     * @return null if the key was not already in the map.  If it was in the
     * map, return the old value associated with it
     */
    private V add(K key, V value, MapEntry<K, V>[] currentTable) {
        int tableIndex = (Math.abs(key.hashCode()) % (currentTable.length));
        Integer[] removedIndices = new Integer[table.length];
        int index = 0;
        while (currentTable[tableIndex] != null) {
            if (currentTable[tableIndex].getKey().equals(key)) {
                V oldValue = currentTable[tableIndex].getValue();
                currentTable[tableIndex].setValue(value);
                if (currentTable[tableIndex].isRemoved()) {
                    currentTable[tableIndex].setRemoved(false);
                    return null;
                }
                currentTable[tableIndex].setRemoved(false);
                return oldValue;
            }
            if (currentTable[tableIndex].isRemoved()) {
                removedIndices[index] = tableIndex;
                index++;
            }
            tableIndex = ((tableIndex + 1) % currentTable.length);
        }
        if (removedIndices[0] != null) {
            currentTable[removedIndices[0]] = new MapEntry<K, V>(key, value);
            return null;
        }
        currentTable[tableIndex] = new MapEntry<K, V>(key, value);
        return null;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int tableIndex = (Math.abs(key.hashCode()) % (table.length));
        int counter = 0;
        while ((table[tableIndex] != null) && (counter <= size)) {
            if (table[tableIndex].getKey().equals(key)) {
                if (!(table[tableIndex].isRemoved())) {
                    table[tableIndex].setRemoved(true);
                    size--;
                    return table[tableIndex].getValue();
                }
            }
            tableIndex = ((tableIndex + 1) % table.length);
            counter++;
        }
        throw new NoSuchElementException("This key is not in the map");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int tableIndex = (Math.abs(key.hashCode()) % (table.length));
        int counter = 0;
        while ((table[tableIndex] != null) && (counter <= size)) {
            if (table[tableIndex].getKey().equals(key)) {
                if (!(table[tableIndex].isRemoved())) {
                    return table[tableIndex].getValue();
                }
            }
            tableIndex = ((tableIndex + 1) % table.length);
            counter++;
        }
        throw new NoSuchElementException("This key is not in the map");
    }


    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        int tableIndex = (Math.abs(key.hashCode()) % (table.length));
        int counter = 0;
        while ((table[tableIndex] != null) && (counter <= size)) {
            if (table[tableIndex].getKey().equals(key)) {
                return true;
            }
            tableIndex = ((tableIndex + 1) % table.length);
            counter++;
        }
        return false;
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> returnSet = new java.util.HashSet<>();
        for (MapEntry<K, V> current : table) {
            if (current != null) {
                if (!current.isRemoved()) {
                    returnSet.add(current.getKey());
                }
            }
        }
        return returnSet;
    }

    @Override
    public List<V> values() {
        List<V> returnList = new java.util.ArrayList<>();
        for (MapEntry<K, V> current : table) {
            if (current != null) {
                if (!current.isRemoved()) {
                    returnList.add(current.getValue());
                }
            }
        }
        return returnList;
    }

    @Override
    public void resizeBackingTable(int length) {
        if ((length < size) || (length == 0)) {
            throw new IllegalArgumentException("Length of array must"
                    + " exceed table size");
        }
        MapEntry<K, V>[] newTable = (MapEntry<K, V>[]) new MapEntry[length];
        for (MapEntry<K, V> current : table) {
            if (current != null) {
                if (!current.isRemoved()) {
                    add(current.getKey(), current.getValue(), newTable);
                }
            }
        }
        table = newTable;
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
