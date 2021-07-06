package moreOrLessGame.test;

import moreOrLessGame.main.Controller;
import moreOrLessGame.main.GlobalConstants;
import moreOrLessGame.main.Model;
import moreOrLessGame.main.View;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class TestController {
    private static Model m;
    private static View v;
    private static Controller c;

    @BeforeClass
    public static void init() {
        m = new Model();
        v = new View();
        c = new Controller(m, v);
    }

    @Before
    public void setPrimaryBarrier() {
        m.setPrimaryBarrier(GlobalConstants.PRIMARY_LOWER_BARRIER, GlobalConstants.PRIMARY_HIGHER_BARRIER);
    }

    @Test
    public void testGetInputIntFromUser() {
        String s = "zoe\ncheck\n\n20000000000\n-20564\nSTRING\n~!()><\n \nпроверка\n222xu\n25";
        ByteArrayInputStream bis = new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(new InputStreamReader(bis));

        int temp = c.getInputIntFromUser(reader);

        Assert.assertFalse(temp >= GlobalConstants.PRIMARY_HIGHER_BARRIER
                || temp <= GlobalConstants.PRIMARY_LOWER_BARRIER);
    }
}