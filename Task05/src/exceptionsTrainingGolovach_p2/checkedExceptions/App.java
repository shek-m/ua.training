package exceptionsTrainingGolovach_p2.checkedExceptions;

import java.io.IOException;

public class App {
    //о брошенном исключении нельзя не предупредить
    public static void main(String[] args) {
     //   throw new Exception(); // тут ошибка компиляции
    }
}

class App01 {
    //Мы не можем бросать, но предупредить о «меньшем»
    public static void main(String[] args) throws IOException {
     //   throw new Exception(); // тут ошибка компиляции
    }
}

class App02 {
    //Мы можем предупредить точно о том, что бросаем
    public static void main(String[] args) throws Exception { // предупреждаем о Exception
        throw new Exception(); // и кидаем Exception
    }
}

class App03 {
    //Мы можем предупредить о большем, чем мы бросаем
    public static void main(String[] args) throws Throwable { // предупреждаем "целом" Throwable
        throw new Exception(); // а кидаем только Exception
    }
}

class App04 {
    //Можем даже предупредить о том, чего вообще нет
    public static void main(String[] args) throws Exception { // пугаем
        // но ничего не бросаем
    }
}

class App05 {
    //Даже если предупреждаем о том, чего нет — все обязаны бояться
    public static void main(String[] args) {
     //   f(); // тут ошибка компиляции
    }

    public static void f() throws Exception {
    }
}

class App06 {
    //Хотя они (испугавшиеся) могут перепугать остальных еще больше
    // они пугают целым Throwable
    public static void main(String[] args) throws Throwable {
        f();
    }
    // хотя мы пугали всего-лишь Exception
    public static void f() throws Exception {
    }
}