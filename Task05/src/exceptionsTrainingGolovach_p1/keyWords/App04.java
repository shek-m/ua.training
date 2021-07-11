package exceptionsTrainingGolovach_p1.keyWords;

public class App04 {
    public static void main(String[] args) {
        //StackOverflowError occurs in both cases (recursive call in catch block)
        f(new NullPointerException());
        //f(null);
    }
    public static void f(NullPointerException e) {
        try {
            throw e;
        } catch (NullPointerException npe) {
            f(npe);
        }
    }
}