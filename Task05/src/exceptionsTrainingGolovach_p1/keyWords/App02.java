package exceptionsTrainingGolovach_p1.keyWords;

public class App02 {
    public static void main(String[] args) {
        //throw and new: two separate operations
        Error ref = new Error(); // создаем экземпляр
        throw ref;               // "бросаем" его
    }
}