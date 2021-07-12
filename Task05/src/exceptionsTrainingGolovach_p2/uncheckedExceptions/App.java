package exceptionsTrainingGolovach_p2.uncheckedExceptions;

public class App {
    //Вызов метода, который «пугает» unchecked исключением не накладывает на нас никаких обязанностей.
    //Эта конструкция служит цели «указать» программисту-читателю кода на то,
    // что ваш метод может выбросить некоторое непроверяемое (unchecked) исключение.
    public static void main(String[] args) {
        f();
    }
    public static void f() throws RuntimeException {
    }
}