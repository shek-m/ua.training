package homework03.task01.test;

import homework03.task01.moreOrLessGameUpdated.Controller;
import homework03.task01.moreOrLessGameUpdated.Model;
import homework03.task01.moreOrLessGameUpdated.View;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestController {
    private static View v;
    private static Controller c;

    @BeforeClass
    public static void init() {
        v = new View();
        c = new Controller(new Model(), v);
    }


}
