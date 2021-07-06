package homework04.task01.registrationForm.view;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by MShekera on 04.07.2021
 */
public class View {
    static String TEXT_BUNDLE = "text";
    static String REGEX_BUNDLE = "regex";
    //default bundle
    public static ResourceBundle bundle = ResourceBundle.getBundle(TEXT_BUNDLE, Locale.US);
    public static ResourceBundle regexBundle = ResourceBundle.getBundle(REGEX_BUNDLE, Locale.US);


    public void printMessage(String message) {
        System.out.println(message);
    }

    public void setDesiredLocale(String name, Locale locale) {
        bundle = ResourceBundle.getBundle(name, locale);
    }

    public String concatenationString(String... str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length; i++) {
            builder.append(str[i]);
        }
        return builder.toString();
    }

    public void printInputText(String message) {
        printMessage(concatenationString(bundle.getString(TextConstants.INPUT_STRING),
                bundle.getString(message)));
    }

    public void printWrongText(String message) {
        printMessage(concatenationString(bundle.getString(TextConstants.INPUT_ERROR),
                bundle.getString(TextConstants.INPUT_STRING), bundle.getString(message)));
    }
}