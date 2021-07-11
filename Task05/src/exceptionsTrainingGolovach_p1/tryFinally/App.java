package exceptionsTrainingGolovach_p1.tryFinally;

public class App {
    /*
     * finally-секция получает управление, если try-блок завершился успешно
     */
    public static void main(String[] args) {
        try {
            System.err.println("try");
        } finally {
            System.err.println("finally");
        }
    }
}

class App01 {
    /*
     * finally-секция получает управление, даже если try-блок завершился исключением
     */
    public static void main(String[] args) {
        try {
            throw new RuntimeException();
        } finally {
            System.err.println("finally");
        }
    }
}

class App02 {
    /*
     * finally-секция получает управление, даже если try-блок завершился директивой выхода из метода
     */
    public static void main(String[] args) {
        try {
            return;
        } finally {
            System.err.println("finally");
        }
    }
}

class App03 {
    /*
     * finally-секция НЕ вызывается только если мы «прибили» JVM
     */
    public static void main(String[] args) {
        try {
            System.exit(42);
        } finally {
            System.err.println("finally");
        }
    }
}

class App04 {
    /*
     * System.exit(42) и Runtime.getRuntime().exit(42) — это синонимы
     */
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().exit(42);
        } finally {
            System.err.println("finally");
        }
    }
}

class App05 {
    /*
     * И при Runtime.getRuntime().halt(42) — тоже не успевает зайти в finally
     */
    public static void main(String[] args) {
        try {
            Runtime.getRuntime().halt(42);
        } finally {
            System.err.println("finally");
        }
    }
}

class App06 {
    /*
     * Однако finally-секция не может «починить» try-блок завершившийся исключение
     *  (заметьте, «more» — не выводится в консоль)
     */
    public static void main(String[] args) {
        try {
            System.err.println("try");
            if (true) {throw new RuntimeException();}
        } finally {
            System.err.println("finally");
        }
        System.err.println("more");
    }
}

class App07 {
    /*
     * И finally-секция не может «предотвратить» выход из метода, если try-блок
     * вызвал return («more» — не выводится в консоль)
     */
    public static void main(String[] args) {
        try {
            System.err.println("try");
            if (true) {return;}
        } finally {
            System.err.println("finally");
        }
        System.err.println("more");
    }
}

class App08 {
    /*
     * Однако finally-секция может «перебить» throw/return при помощи другого throw/return
     */
    public static void main(String[] args) {
        System.err.println(f());
    }
    public static int f() {
        try {
            return 0;
        } finally {
            return 1;
        }
    }
}

class App09 {
    public static void main(String[] args) {
        System.err.println(f());
    }
    public static int f() {
        try {
            throw new RuntimeException();
        } finally {
            return 1;
        }
    }
}

class App10 {
    //исключение в finally перебивает RETURN в try
    public static void main(String[] args) {
        System.err.println(f());
    }
    public static int f() {
        try {
            return 0;
        } finally {
            throw new RuntimeException();
        }
    }
}

class App11 {
    //исключение в finally перебивает исключение в try
    public static void main(String[] args) {
        System.err.println(f());
    }
    public static int f() {
        try {
            throw new Error();
        } finally {
            throw new RuntimeException();
        }
    }
}