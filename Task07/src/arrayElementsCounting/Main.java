package arrayElementsCounting;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(4,5,-6,4,5,3,4,2,4,5,7));

        System.out.println(ArrayElementsCounter.countElements(list));
    }
}
