package exceptionsTrainingGolovach_p1.returnsAndExceptions;

public class AppCalcRectangleArea {
    public static int area(int width, int height) {
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("Negative sizes: w = " + width + ", h = " + height);
            // System.exit(0);  - не корректное завершение работы
            // return -1;       - не факт что кто-то прочитает, а не использует
        }
        return width * height;
    }
    //Благодаря исключениям, можно ничего не возвращать, а бросить его,
    //и корректно остановить программу, предоставив возможность всё исправить и пойти дальше.
}
