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
        int numb;
        for (int i = 0; i < 10000000; i++) {
            numb = m.pickRandomNumber();
            if (numb <= GlobalConstants.PRIMARY_LOWER_BARRIER || numb >= GlobalConstants.PRIMARY_HIGHER_BARRIER) {
                Assert.fail("incorrect value");
            }
        }
    }

    @Test
    public void testCheckValue() {
        m.setPickedNumber(m.pickRandomNumber());
        int secretVal = m.getPickedNumber();

        for (int i = -100; i < 100; i++) {
            if (!m.checkValue(secretVal + i) && i == 0) {
                Assert.fail();
            } else if (m.checkValue(secretVal + i) && i != 0) {
                Assert.fail();
            }
        }
    }
}