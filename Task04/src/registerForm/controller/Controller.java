/*
 * Controller.java
 *
 * version 1.0
 *
 * 04.07.2021
 *
 */

package registerForm.controller;

import registerForm.model.Model;
import registerForm.view.TextConstants;
import registerForm.view.View;
import registerForm.model.entity.DataBaseEmulation;

import java.util.Locale;
import java.util.Scanner;

/**
 * This class provides functionality, that allows to add the note entity to the storage
 * by subsequently reading strings from console and matching them with appropriate regular
 * expression. The note is being added to the storage, when all the required fields of note
 * have been read from console and each of them match it's regular expression. Otherwise,
 * the input/check process is repeated.
 *
 * @version 1.0
 *
 * @author Mykhailo Shekera
 */
public class Controller {
    /** view variable is needed to print messages to console */
    private View view;

    /** model variable refers to the storage for finished notes */
      private Model model;

    /**
     * Creates new instance of Controller class
     * @param view instance for initialization of view variable
     */
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    /**
     * This method is the moreOrLessGame.main working method of the class.
     * The method adds the fully filled note to the storage.
     */
    public void processNote() {
        Scanner scanner = new Scanner(System.in);
        InputTheNote itn = new InputTheNote(view, scanner);
        view.setDesiredLocale(askUsersLocale(scanner));
        model.addNote(itn.validateSingleNote());
    }

    private Locale askUsersLocale(Scanner scan) {
        int temp = 0;
        Locale locale = Locale.US;
        view.printMessage(View.bundle.getString(TextConstants.INPUT_ASK_FOR_LOCALE));

        while (!(scan.hasNextInt() && ((temp = scan.nextInt()) == 1 || temp == 2))) {
            view.printMessage(View.bundle.getString(TextConstants.INPUT_ERROR));
        }
        switch (temp) {
            case 1: break;
            case 2: locale = new Locale("ua", "UA");
                    break;
        }
        return locale;
    }
}