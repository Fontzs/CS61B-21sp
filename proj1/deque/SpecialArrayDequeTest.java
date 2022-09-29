package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SpecialArrayDequeTest {
    @Test
    public void testThreeAddThreeRemove() {
        LinkedListDeque<Integer> correct = new LinkedListDeque<>();
        ArrayDeque<Integer> broken = new ArrayDeque<>();

        correct.addLast(5);
        correct.addLast(10);
        correct.addLast(15);

        broken.addLast(5);
        broken.addLast(10);
        broken.addLast(15);

        assertEquals(correct.size(), broken.size());

        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
    }

    @Test
    public void randomizedTest() {
        LinkedListDeque<Integer> L = new LinkedListDeque<>();
        ArrayDeque<Integer> R = new ArrayDeque<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 5);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                R.addLast(randVal);
                //System.out.println("addLast(" + randVal + ")");

            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = R.size();
                //System.out.println("size: " + size);
                assertEquals(size, size2);
                assertEquals(true, L.equals(R));

            } else if (L.size() <= 0) {
                continue;
            } else if (operationNumber == 2) {
                // get random one
                int k = StdRandom.uniform(0, L.size());
                int last = L.get(k);
                int last2 = R.get(k);
                //System.out.println("getLast(" + last + ")");
                assertEquals(last, last2);
            } else if (operationNumber == 3) {
                // removeLast
                int removed = L.removeLast();
                int removed2 = R.removeLast();
                //System.out.println("removeLast(" + removed + ")");
                assertEquals(removed, removed2);

            } else if (operationNumber == 4) {
                // addFirst
                int randVal = StdRandom.uniform(0, 100);
                L.addFirst(randVal);
                R.addFirst(randVal);
                //System.out.println("addLast(" + randVal + ")");
            }
        }
    }

    @Test
    public void equalTest(){
        Deque<Integer> a = new ArrayDeque<>();
        Deque<Integer> b = new LinkedListDeque<>();
        for (int i = 0; i < 500; i++) {
            a.addLast(i);
            b.addLast(i);
        }
        for (int i = 0; i < 400; i++) {
            a.removeFirst();
            b.removeFirst();
        }
        //a.printDeque();
        //b.printDeque();

        assertEquals(a.size(), b.size());
        for (int i = 0; i < a.size(); i++) {
            assertEquals(a.get(i), b.get(i));
        }
        assertEquals(true, a.equals(b));


        for (int i = 0; i < 1000; i++) {
            a.addFirst(i);
            b.addFirst(i);
        }
        for (int i = 0; i < 298; i++) {
            a.removeLast();
            b.removeLast();
        }

        assertEquals(true, a.equals(b));
        assertEquals(a.size(), b.size());
    }
}
