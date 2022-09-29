package deque;

import java.util.Iterator;


public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] arr;
    private int size;
    private int FirstPoint;
    private int LastPoint;
    

    private void deduce() {
        if (isEmpty()) {
            resize(8);
        } else if (size >= 4 && size < arr.length / 4) {
            resize(2 * size);
        }
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        int a = (capacity - size) / 2;
        System.arraycopy(arr, FirstPoint + 1, temp, a, size);
        arr = temp;
        FirstPoint = a - 1;
        LastPoint = FirstPoint + size + 1;

    }



    public ArrayDeque() {
        arr = (T[]) new Object[8];
        size = 0;
        FirstPoint = 4;
        LastPoint = 5;
    }

    @Override
    public void addFirst(T item) {
        arr[FirstPoint] = item;
        FirstPoint--;
        size++;
        if (FirstPoint < 0) {
            resize(size * 2);
        }

    }

    @Override
    public void addLast(T item) {
        arr[LastPoint] = item;
        LastPoint++;
        size++;
        if (LastPoint == arr.length) {
            resize(size * 2);
        }

    }



    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < size - 1; i++) {
            str.append(this.get(i));
            str.append(" ");
        }
        str.append(this.get(size - 1));
        System.out.println(str.toString());
    }

    @Override
    public T removeFirst() {
        if (isEmpty()){
            return null;
        }
        FirstPoint++;
        size--;
        T ans = arr[FirstPoint];
        arr[FirstPoint] = null;
        deduce();
        return ans;

    }

    @Override
    public T removeLast() {
        if (isEmpty()){
            return null;
        }
        LastPoint--;
        size--;
        T ans = arr[LastPoint];
        arr[LastPoint] = null;
        deduce();

        return ans;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0){
            return null;
        }

        return arr[FirstPoint + index + 1];
    }


    private class ADIterator implements Iterator<T> {
        private int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < size();
        }

        @Override
        public T next() {
            T ans = get(pos);
            pos++;
            return ans;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ADIterator();
    }



    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }

        if (! (o instanceof ArrayDeque)) {
            return false;
        }


        if (o instanceof ArrayDeque) {
            ArrayDeque<T> dick = (ArrayDeque<T>) o;
            if (dick.size() != this.size()) {
                return false;
            }
            for (int i = 0; i < size(); i++) {
                if (!this.get(i).equals(dick.get(i))) {
                    return false;
                }
            }
        }
       return true;
    }
}
