package exceptionsTrainingGolovach_p1.systemErr;

public class App {
    public static void main(String[] args) {
        System.err.println("sout");        //System.err is NOT buffered
        throw new Error();
    }
}

class App01 {
    public static void main(String[] args) {
        System.out.println("sout");         //System.out is buffered
        throw new Error();
    }
}