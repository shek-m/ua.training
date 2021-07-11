package exceptionsTrainingGolovach_p1.tryAndMulticatch;

public class App {
    /*
     *  есть такое правило — нельзя ставить потомка после предка! (RuntimeException после Exception)
     */
    public static void main(String[] args) {
        try {
        } catch (Exception e) {
            //    } catch (RuntimeException e) {
            //    }
        }
    }
}

class App01 {
    // cтавить брата после брата — можно (RuntimeException после Error)
    public static void main(String[] args) {
        try {
        } catch (Error e) {
        } catch (RuntimeException e) {
        }
    }
}

class App02 {
    /*
     * Как происходит выбор «правильного» catch?
     * Да очень просто — JVM идет сверху-вниз до тех пор,
     * пока не найдет такой catch что в нем указано ваше исключение
     * или его предок — туда и заходит. Ниже — не идет.
     */
    public static void main(String[] args) {
        try {
            throw new Exception();
        } catch (RuntimeException e) {
            System.err.println("catch RuntimeException");
        } catch (Exception e) {
            System.err.println("catch Exception");
        } catch (Throwable e) {
            System.err.println("catch Throwable");
        }
        System.err.println("next statement");
    }
}

class App03 {
    /*
     * Выбор catch осуществляется в runtime (а не в compile-time),
     * значит учитывается не тип ССЫЛКИ (Throwable), а тип ССЫЛАЕМОГО (Exception)
     */
    public static void main(String[] args) {
        try {
            Throwable t = new Exception(); // ссылка типа Throwable указывает на объект типа Exception
            throw t;
        } catch (RuntimeException e) {
            System.err.println("catch RuntimeException");
        } catch (Exception e) {
            System.err.println("catch Exception");
        } catch (Throwable e) {
            System.err.println("catch Throwable");
        }
        System.err.println("next statement");
    }
}