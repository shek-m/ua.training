package exceptionsTrainingGolovach_p2.catchOrThrows;

import java.io.EOFException;
import java.io.FileNotFoundException;

public class App {
    //не надо пугать тем, что уже перехватили
    public static void main(String[] args) {
        try {
            throw new Exception();
        } catch (Exception e) {
            // ...
        }
    }
}

class App01 {
    //ставим catch по предку и точно перехватываем
    public static void main(String[] args) {
        try {
            throw new Exception();
        } catch (Throwable e) {
            // ...
        }
    }
}

class App02 {
    //Но если перехватили только потомка, то не выйдет (ошибка компиляции)
    public static void main(String[] args) {
        try {
            //throw new Throwable();
        } catch (Exception e) {
            // ...
        }
    }
}

class App03 {
    //Не годится, естественно, и перехватывание «брата»
    public static void main(String[] args) {
        try {
            //throw new Exception();
        } catch (Error e) {
            // ...
        }
    }
}

class App04 {
    // EOFException перехватили catch-ом, им не пугаем
    public static void main(String[] args) throws FileNotFoundException {
        try {
            if (System.currentTimeMillis() % 2 == 0) {
                throw new EOFException();
            } else {
                throw new FileNotFoundException();
            }
        } catch (EOFException e) {
            // ...
        }
    }
}