package homework02.task02.moduleTestingExample.test;

import homework02.task02.moduleTestingExample.calculation.Arithmetics;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class TestArithmetics {
    private static Arithmetics a;

    @Rule
    public final ExpectedException exp = ExpectedException.none();

    @Rule
    public Timeout time = new Timeout(1000);

    @BeforeClass
    public static void runT() {
        a = new Arithmetics();
    }

    @Test
    public void testAdd() {
        double res = a.add(3, 7);
        Assert.assertEquals(res, 10.0, 0.00001d);
    }

    @Ignore
    @Test
    public void testMult() {
        double res = a.mult(3, 7);
        Assert.assertEquals(res, 21.0, 0.00001d);
    }

    @Test
    public void testDeduct() {
        double res = a.deduct(3, 7);
        Assert.assertEquals(res, -4.0, 0.00001d);
    }

    @Test
    public void testDiv() {
        double res = a.div(10, 5);
        Assert.assertEquals(res, 2.0, 0.00001d);
    }

   // @Test(expected = ArithmeticException.class)
    @Test
    public void testDivException() {
        exp.expect(ArithmeticException.class);
        a.div(10.0, 0.0);
    }

   // @Test(timeout = 1000)
    @Test
    public void testN() {
        while (true);
    }


}