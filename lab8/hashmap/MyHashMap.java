package hashmap;

import afu.org.checkerframework.checker.oigj.qual.O;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Ingcheon
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private Set<K> keySet;
    private int NE; // The number of elements.
    private int MB; // The number of buckets.
    private double loadFactor;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        keySet = new HashSet<>();
        loadFactor = 0.75;
        buckets = createTable(16);
        NE = 0;
        MB = 16;

    }

    public MyHashMap(int initialSize) {
        keySet = new HashSet<>();
        loadFactor = 0.75;
        buckets = createTable(initialSize);
        NE = 0;
        MB = 16;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        keySet = new HashSet<>();
        loadFactor = maxLoad;
        buckets = createTable(initialSize);
        NE = 0;
        MB = initialSize;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] pussy = new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            pussy[i] = createBucket();
        }
        return pussy;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!


    @Override
    public void clear() {
        keySet = new HashSet<>();
        buckets = createTable(16);
        NE = 0;
        MB = 16;

    }

    @Override
    public boolean containsKey(K key) {
        int index = Math.abs(key.hashCode()) % MB;
        if (buckets[index].isEmpty()) {
            return false;
        }
        for (Node x : buckets[index]) {
            if (x.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int index = Math.abs(key.hashCode()) % MB;
        for (Node x : buckets[index]) {
            if (x.key.equals(key)) {
                return x.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return NE;
    }

    @Override
    public void put(K key, V value) {
        if (((NE * 1.0 + 1) / MB) >= loadFactor) {
            resize();
        }
        keySet.add(key);
        int index = Math.abs(key.hashCode()) % MB;
        if (containsKey(key)) {
            for (Node x : buckets[index]) {
                if (x.key.equals(key)) {
                    x.value = value;
                }
            }
        } else {
            buckets[index].add(createNode(key, value));
            NE++;
        }


    }

    private void resize() {
        Set<Node> nodeSet = new HashSet<>();
        for (int i = 0; i < MB; i++) {
            for (Node tmp : buckets[i]) {
                nodeSet.add(tmp);
            }
        }
        this.MB *= 2;
        this.NE = 0;
        buckets = createTable(this.MB);
        for (Node tmp : nodeSet) {
            put(tmp.key, tmp.value);
        }

    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        int index = Math.abs(key.hashCode()) % MB;
        for (Node x : buckets[index]) {
            if (x.key.equals(key)) {
                V ans = x.value;
                buckets[index].remove(x);
                return ans;
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        int index = Math.abs(key.hashCode()) % MB;
        for (Node x : buckets[index]) {
            if (x.key.equals(key) && x.value.equals(value)) {
                V ans = x.value;
                buckets[index].remove(x);
                return ans;
            }
        }
        return null;
    }


    private class keyIterator implements Iterator<K> {
        Iterator<K> iterator;
        private keyIterator(){
            iterator = keySet.iterator();
        }
        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public K next() {
            if (iterator.hasNext()) {
                return iterator.next();
            }
            return null;
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new keyIterator();
    }



}
