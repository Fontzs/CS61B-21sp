package flik;

import org.junit.Test;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** An Integer tester created by Flik Enterprises.
 * @author Josh Hug
 * */
public class Flik {
    /** @param a Value 1
     *  @param b Value 2
     *  @return Whether a and b are the same */
    public static boolean isSameNumber(Integer a, Integer b) {
        return a.equals(b);
    }

    @Test
    public void test(){
        Integer a = 128, b = 128, c = 3;
        assertTrue(isSameNumber(a, b));

        assertFalse(isSameNumber(a, c));
    }
}
