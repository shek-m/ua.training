package homework03.task01.test;

import homework03.task01.moreOrLessGameUpdated.GlobalConstants;
import homework03.task01.moreOrLessGameUpdated.Model;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestModel {
    private static Model m;

    @BeforeClass
    public static void init() {
        m = new Model();
        m.setPrimaryBarrier(GlobalConstants.PRIMARY_LOWER_BARRIER, GlobalConstants.PRIMARY_HIGHER_BARRIER);
    }

    @Test
    public void testPickRandomNumber() {
        boolean[] arr = new boolean[101];
        for (int i = 0; i < 1000000; i++) {
            arr[m.pickRandomNumber()] = true;
        }

        if (!arr[1] || !arr[99] || arr[0] || arr[100]) {
            Assert.fail("incorrect random range");
        }
    }
}