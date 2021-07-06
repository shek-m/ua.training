package homework04.task01.registrationForm.view;

/**
 * Created by MShekera on 04.07.2021
 */
public class View {
    //String constants
    public static final String INPUT_STRING = "Input the line..";
    public static final String INPUT_ERROR = "Input error. Try again.";
    public static final String CORRECT = "Correct.";
    public static final String MISMATCH = "The input string doesn't match it's regex. " +
            "Try again.";

    public void printMessage(String message) {
        System.out.println(message);
    }
}