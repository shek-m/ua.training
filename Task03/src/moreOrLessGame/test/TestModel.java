package moreOrLessGame.test;

import moreOrLessGame.main.GlobalConstants;
import moreOrLessGame.main.Model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestModel {
    private static Model m;

    @BeforeClass
    public static void init() {
        m = new Model();
    }

    @Before
    public void setPrimaryBarrier() {
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

    @Test
    public void testIsMatchingTheRange() {
        for (int i = -10000; i <= GlobalConstants.PRIMARY_LOWER_BARRIER; i++) {
            Assert.assertFalse(m.isMatchingTheRange(i));
        }

        for (int i = GlobalConstants.PRIMARY_HIGHER_BARRIER; i < 10000; i++) {
            Assert.assertFalse(m.isMatchingTheRange(i));
        }

        for (int i = GlobalConstants.PRIMARY_LOWER_BARRIER + 1; i < GlobalConstants.PRIMARY_HIGHER_BARRIER; i++) {
            Assert.assertTrue(m.isMatchingTheRange(i));
        }
    }
}