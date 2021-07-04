package homework04.task01.registrationForm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by MShekera on 04.07.2021
 */
public class Controller {
    private View view;
    private DataBaseEmulation db = DataBaseEmulation.getInstance();
    private ArrayList<String> activeRegex;

    public Controller(View view) {
        this.view = view;
    }

    public void processNote() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        initActiveRegex();
        view.printMessage(View.INPUT_STRING);
        db.add(validateSingleNote(reader));
    }

    private String readString(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException ignored) {
            view.printMessage(View.INPUT_ERROR);
            return readString(reader);
        }
    }

    private boolean isStringCorrect(String str, String regex) {
        return str.matches(regex);
    }

    private void initActiveRegex() {
        this.activeRegex = new ArrayList<>();
        this.activeRegex.add(RegexPatterns.REGEX_SURNAME);
        this.activeRegex.add(RegexPatterns.REGEX_NICKNAME);
    }

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
                view.printMessage(View.CORRECT);
            } else {
                view.printMessage(View.MISMATCH);
                i--;
            }
        }
        return note;
    }
}