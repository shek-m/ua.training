import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Streams {
    public static void main(String[] args) {
        int[] array = {1,6,4,8,12,3,11,2};

        //среднее значение элементов в массиве
        OptionalDouble avg = Arrays.stream(array).average();
        System.out.println(avg.getAsDouble());

        //индекс минимального элемента и значение
        int minIndex = IntStream.range(0,array.length).reduce((i, j)-> array[i]>array[j]? j : i).getAsInt();
        System.out.println("Index of min el: " + minIndex + ", element: " + array[minIndex]);

        //индекс минимального элемента и значение (2)
        int min = Arrays.stream(array).min().getAsInt();
        System.out.println(min + " index: " +   Arrays.stream(array).boxed().collect(Collectors.toList()).indexOf(min));

        //колличество элементов раыных нулю
        long zeroCount = Arrays.stream(array).filter(x -> x == 0).count();
        System.out.println("zero count: " + zeroCount);

        //колличество элементов больше нуля
        long moreThenZero = Arrays.stream(array).filter(x->x>0).count();
        System.out.println("more then zero count: " + moreThenZero);

        //умножаем каждый эллемент массива на 10
        int[] newArr = Arrays.stream(array).map(x->x*10).toArray();
        System.out.println(Arrays.toString(newArr));
    }
}