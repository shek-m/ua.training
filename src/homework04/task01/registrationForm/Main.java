package homework04.task01.registrationForm;

import homework04.task01.registrationForm.controller.Controller;
import homework04.task01.registrationForm.view.View;

/**
 * Created by MShekera on 04.07.2021
 */
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new View());
        controller.processNote();
    }
}