package registerForm.controller;

import registerForm.model.Note;
import registerForm.view.View;

import java.util.Scanner;

import static registerForm.view.TextConstants.INPUT_FIRST_NAME;
import static registerForm.view.TextConstants.INPUT_LOGIN;

public class InputTheNote {
    private View view;
    private Scanner sc;

    public InputTheNote(View view, Scanner sc) {
        this.view = view;
        this.sc = sc;
    }

    /**
     * Proceed the procedure of filling of one single note.
     * If the mismatch occurs, parameter i in the "else" block is
     * decremented to repeat input/check procedure for same field.
     * @return note with all filled fields
     */
    public Note validateSingleNote() {
        Note note = new Note();
        UtilityController uc = new UtilityController(sc, view);

        note.setName(uc.getInputAndMatch(INPUT_FIRST_NAME
                , View.regexBundle.getString(RegexPatterns.REGEX_NAME)));
        note.setNickname(uc.getInputAndMatch(INPUT_LOGIN
                , View.regexBundle.getString(RegexPatterns.REGEX_NICKNAME)));
        return note;
    }
}