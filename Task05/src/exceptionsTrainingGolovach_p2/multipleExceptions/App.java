package exceptionsTrainingGolovach_p2.multipleExceptions;



import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * Рассмотрим ситуацию с кодом, который может бросать проверяемые исключения разных типов.
 * Далее учитывайте, что EOFException и FileNotFoundException — потомки IOException.
 */
public class App {
    //Мы можем точно указать, что выбрасываем
    // пугаем ОБОИМИ исключениями
    public static void main(String[] args) throws EOFException, FileNotFoundException {
        if (System.currentTimeMillis() % 2 == 0) {
            throw new EOFException();
        } else {
            throw new FileNotFoundException();
        }
    }
}

class App01 {
    // пугаем ОБОИМИ исключениями
    public static void main(String[] args) throws EOFException, FileNotFoundException {
        f0();
        f1();
    }
    public static void f0() throws EOFException {}
    public static void f1() throws FileNotFoundException {}
}

class App02 {
    // пугаем ПРЕДКОМ исключений
    public static void main(String[] args) throws IOException {
        if (System.currentTimeMillis() % 2 == 0) {
            throw new EOFException();
        } else {
            throw new FileNotFoundException();
        }
    }
}

class App03 {
    public static void main(String[] args) throws IOException {
        f0();
        f1();
    }
    public static void f0() throws EOFException {}
    public static void f1() throws FileNotFoundException {}
}

class App04 {
    /*
     * EOFException и FileNotFoundException «обобщаем до» IOException,
     * а InterruptedException «пропускаем нетронутым» (InterruptedException — НЕ потомок IOException)
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        f0();
        f1();
        f2();
    }
    public static void f0() throws EOFException {}
    public static void f1() throws FileNotFoundException {}
    public static void f2() throws InterruptedException {}
}