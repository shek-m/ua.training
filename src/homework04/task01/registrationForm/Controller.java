package homework04.task01.registrationForm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by MShekera on 04.07.2021
 */
public class Controller {
    private ArrayList<String> activeRegex;
    private DataBaseEmulation db = DataBaseEmulation.getInstance();

    //Regex patterns
    //Surname (Latin, Cyrillic)
    public static final String REGEX_SURNAME = "^([A-ZÀ-ÿА-Я][-,a-zа-я. ']+[ ]*)+$";
    //Name (Latin, Cyrillic)
    public static final String REGEX_NAME = "^([A-ZА-Я][a-zа-я-]+[ ]*)+$";
    //Middle name (Latin, Cyrillic)
    public static final String REGEX_MIDDLE_NAME = "^([A-ZА-Я][a-zа-я-]+)+$";
    //Nickname
    public static final String REGEX_NICKNAME = "^[\\S^@A-Za-z]+$";
    //Comment
    public static final String REGEX_COMMENT = "^(.|\\s)+$";
    //City number: "+CCCTTTTXXXXXXX" C = country code (1 to 3 dig) T = city code (1 to 8 dig) X = number (5 to 7 dig)
    public static final String REGEX_CITY_NUMBER = "^\\+\\d{1,3}(\\()*\\d{1,8}(\\))*\\d{1,3}(-)*\\d{1,2}(-)*\\d{1,2}$";
    //Mobile number
    public static final String REGEX_MOBILE_NUMBER = "^\\+\\d{1,3}(\\()*\\d{1,8}(\\))*\\d{1,3}(-)*\\d{1,2}(-)*\\d{1,2}$";
    //E-mail "shekera@mail.com"
    public static final String REGEX_EMAIL = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    //ZIP code
    public static final String REGEX_ZIP = "^[0-9]{5}(?:-[0-9]{4})?$";
    //City
    public static final String REGEX_CITY = "[A-ZА-Я][A-ZА-Яa-zа-я- ]+";
    //Street
    public static final String REGEX_STREET = "[A-ZА-Я][A-Za-zА-Яа-я., -]+";
    //Street number
    public static final String REGEX_STREET_NUMBER = "[\\d/A-Za-zА-Яа-я]+";
    //Apartment number (optional, for private houses)
    public static final String REGEX_APARTMENT_NUMBER = "[\\d/A-Za-zА-Яа-я]*";
    //Date "dd/mm/yyyy"
    public static final String REGEX_DATE = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$";


    public void processNote() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        initActiveRegex();
        db.add(validateSingleNote(reader));
    }

    private String readString(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException ignored) {
            return readString(reader);
        }
    }

    private boolean isStringCorrect(String str, String regex) {
        return str.matches(regex);
    }

    private void initActiveRegex() {
        this.activeRegex.add(Controller.REGEX_SURNAME);
        this.activeRegex.add(Controller.REGEX_NICKNAME);
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
            }
        }
        return note;
    }
}