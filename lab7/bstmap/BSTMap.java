package bstmap;

import java.nio.file.Path;
import java.security.Key;
import java.util.*;

import static java.nio.file.Files.delete;

public class BSTMap<K extends Comparable, V> implements Map61B {

    BSTNode root;
    int size;
    Queue<BSTNode> queue;
    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;
        private BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }
        /*
        private int getSize() {
            if (this.key == null) {
                return 0;
            }
            return 1 + this.left.getSize() + this.right.getSize();
        }
         */

    }

    public BSTMap() {
        this.size = 0;
        queue = new LinkedList<>();
    }

    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }


    private BSTNode find(BSTNode cur, K key) {
        if (cur == null) {
            return null;
        } else if (cur.key.equals(key)) {
            return cur;
        }

        BSTNode left_key = find(cur.left, key);
        BSTNode right_key = find(cur.right, key);
        return left_key == null ? right_key : left_key ;

    }
    @Override
    public boolean containsKey(Object key) {
        BSTNode cur = root;
        if (root == null || root.key == null) {
            return false;
        }
        if (root.key == key) {
            return true;
        }
        while (cur != null) {
            if (cur.key.compareTo(key) > 0) {
                cur = cur.left;
            } else if (cur.key.compareTo(key) < 0) {
                cur = cur.right;
            } else {
                return true;
            }
        }
        return false;

    }

    @Override
    public Object get(Object key) {

        return find(root, (K) key) == null ? null : find(root, (K) key).value;

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void put(Object key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        root = put(root, (K) key, (V) value);
    }

    private BSTNode put(BSTNode x, K k, V v) {
        if (x == null) {
            this.size++;
            return new BSTNode(k, v);

        }
        int cmp = k.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, k, v);
        } else if (cmp > 0) {
            x.right = put(x.right, k ,v);
        } else {
            x.value = v;
        }
        return x;
    }


    private void dfs(BSTNode cur) {
        if (cur == null) {
            return;
        }
        queue.add(cur);
        dfs(cur.left);
        dfs(cur.right);
    }
    @Override
    public Set keySet() {

        queue = new LinkedList<>();
        dfs(root);
        Set<BSTNode> set = new HashSet<>();
        while (!queue.isEmpty()){
            set.add(queue.poll());
        }
        return set;


        //throw new UnsupportedOperationException("I don't wanna do it.");
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public Object remove(Object key, Object value) {
        throw new UnsupportedOperationException("I don't wanna do it.");
    }


    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("I don't wanna do it.");
    }


    public void printInOrder() {
        queue = new LinkedList<>();
        dfs(root);
        while (!queue.isEmpty()) {
            System.out.print(queue.poll());
            System.out.print(" ");
        }
    }

}
