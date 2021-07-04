package homework04.task01.registrationForm;

/**
 * Created by MShekera on 04.07.2021
 */
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new View());
        controller.processNote();
    }
}