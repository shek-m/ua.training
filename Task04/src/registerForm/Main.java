package registerForm;

import registerForm.controller.Controller;
import registerForm.model.Model;
import registerForm.view.View;

/**
 * Created by MShekera on 04.07.2021
 */
public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new View(), new Model());
        controller.processNote();
    }
}