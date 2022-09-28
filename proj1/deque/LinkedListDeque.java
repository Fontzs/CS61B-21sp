package deque;


import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T>{

    private class Node{
        Node prev;
        T item;
        Node next;

        private Node(Node a,T i, Node b){
            prev = a;
            item = i;
            next = b;
        }

    }

    private Node sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    private T getRecursiveHelper(Node p, int index){
        if (index == 0){
            return p.next.item;
        }else{
            return getRecursiveHelper(p.next, index - 1);
        }
    }

    public T getRecursive(int index){
        if (index >= size){
            return null;
        }
        return getRecursiveHelper(sentinel, index);
    }

    @Override
    public void addFirst(T item){
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;

    }

    @Override
    public void addLast(T item){
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }



    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        StringBuffer str = new StringBuffer();

        for (int i = 0; i < size - 1; i++) {
            str.append(get(i));
            str.append(" ");
        }
        str.append(get(size - 1));
        System.out.println(str.toString());
    }

    @Override
    public T removeFirst(){
        if(size == 0){
            return null;
        }
        T ans = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return ans;
    }

    @Override
    public T removeLast(){
        if(size == 0){
            return null;
        }
        T ans = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return ans;
    }

    @Override
    public T get(int index){
        if (index >= size || index < 0){
            return null;
        }
        Node temp = sentinel;
        while(index >= 0){
            temp = temp.next;
            index--;
        }
        return temp.item;
    }


    private class LLDIterator implements Iterator<T>{
        private int pos;

        public LLDIterator(){
            pos = 0;
        }

        @Override
        public boolean hasNext(){
            return pos < size;
        }
        @Override
        public T next(){
            T item = get(pos);
            pos++;
            return item;
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new LLDIterator();
    }

    @Override
    public boolean equals(Object o){
        if(o == null){
            return false;
        }
        if (o == this){
            return true;
        }

        if(o instanceof Deque){
            Deque<T> dick = (Deque<T>) o;
            if(dick.size() != this.size){
                return false;
            }
            for (int i = 0; i < this.size ; i++) {
                if(!get(i).equals(dick.get(i))){
                    return false;
                }
            }

        }

        return true;
    }




}
