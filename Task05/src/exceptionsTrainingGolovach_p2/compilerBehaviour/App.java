package exceptionsTrainingGolovach_p2.compilerBehaviour;

public class App {
    // пугаем Exception
    public static void main(String[] args) throws Exception {
        Throwable t = new Exception(); // и лететь будет Exception
        //throw t; // но тут ошибка компиляции
    }
}

class App01 {
    //Полная аналогия
    public static void main(String[] args) throws Exception {
        Object ref = "Hello!";  // ref указывает на строку
        //char c = ref.charAt(0); // но тут ошибка компиляции
    }
}

class App02 {
    // пугаем Exception
    public static void main(String[] args) throws Exception {
        try {
            Throwable t = new Exception(); // и лететь будет Exception
            //throw t; // но тут ошибка компиляции
        } catch (Exception e) {
            System.out.println("Перехвачено!");
        }
    }
}

class App03 {
    // ТЕПЕРЬ пугаем Throwable
    public static void main(String[] args) throws Throwable {
        try {
            Throwable t = new Exception(); // а лететь будет Exception
            throw t;
        } catch (Exception e) { // и мы перехватим Exception
            System.out.println("Перехвачено!");
        }
    }
}