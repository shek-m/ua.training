package homework04.task01.registrationForm.controller;

import homework04.task01.registrationForm.view.View;

import java.util.Scanner;

public class UtilityController {
    private Scanner scan;
    private View view;

    public UtilityController(Scanner scan, View view) {
        this.scan = scan;
        this.view = view;
    }

    public String getInputAndMatch(String message, String regex) {
        String str;
        view.printInputText(message);
        while (!(scan.hasNext() && ((str = scan.next()).matches(regex)))) {
            view.printWrongText(message);
        }
        return str;
    }
}
