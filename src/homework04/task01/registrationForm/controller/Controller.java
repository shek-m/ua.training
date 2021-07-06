/*
 * Controller.java
 *
 * version 1.0
 *
 * 04.07.2021
 *
 */

package homework04.task01.registrationForm.controller;

import homework04.task01.registrationForm.view.TextConstants;
import homework04.task01.registrationForm.view.View;
import homework04.task01.registrationForm.model.DataBaseEmulation;
import homework04.task01.registrationForm.model.Note;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    /** db variable refers to instance of storage for finished notes */
    private DataBaseEmulation db = DataBaseEmulation.getInstance();

    /** activeRegex variable refers to the list of regex patterns, that
     * are used to match input strings in accordance to active fields of
     * the note
     */
    private ArrayList<String> activeRegex;

    /**
     * Creates new instance of Controller class
     * @param view instance for initialization of view variable
     */
    public Controller(View view) {
        this.view = view;
    }

    /**
     * This method is the main working method of the class.
     * The method adds the fully filled note to the storage (db).
     */
    public void processNote() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        initActiveRegex();
        view.printMessage(View.INPUT_STRING);
        db.add(validateSingleNote(reader));
    }

    public Locale askUsersLocale(Scanner scan) {
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

    /**
     * This method reads string from console using BufferedReader.
     * In case of IOException, it prints the error message and recursively
     * calls itself from catch block
     * @param reader instance of BufferedReader, set to reading from console
     * @return string, read from console
     */
    private String readString(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException ignored) {
            view.printMessage(View.INPUT_ERROR);
            return readString(reader);
        }
    }

    /**
     * Method checks, if the string matches regular expression for particular field
     * @param str input string to check
     * @param regex appropriate regular expression for string check
     * @return true if str matches regular expression or false if not
     */
    private boolean isStringCorrect(String str, String regex) {
        return str.matches(regex);
    }

    /** Initialization of the activeRegex arraylist */
    private void initActiveRegex() {
        this.activeRegex = new ArrayList<>();
        this.activeRegex.add(RegexPatterns.REGEX_SURNAME);
        this.activeRegex.add(RegexPatterns.REGEX_NICKNAME);
    }

    /**
     * Proceed the procedure of filling of one single note.
     * If the mismatch occurs, parameter i in the "else" block is
     * decremented to repeat input/check procedure for same field.
     * @param reader for reading strings from console
     * @return note with all filled fields
     */
    private Note validateSingleNote(BufferedReader reader) {
        Note note = new Note();
        for (int i = 0; i < activeRegex.size(); i++) {
            String inputStr = readString(reader);
            if (isStringCorrect(inputStr, activeRegex.get(i))) {
                switch (i) {
                    case 0: note.setSurname(inputStr);
                            break;
                    case 1: note.setNickname(inputStr);
                            break;
                }
                view.printMessage();
            } else {
                view.printMessage(View.MISMATCH);
                i--;
            }
        }
        return note;
    }
}