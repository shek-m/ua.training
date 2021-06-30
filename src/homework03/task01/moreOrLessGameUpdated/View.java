package homework03.task01.moreOrLessGameUpdated;

import java.util.List;

public class View {
    //String constants
    public static final String GREETINGS = "Hi, User! Welcome to the " +
            "\"Guess the number\" console game!\nHere, you have to guess " +
            "the randomly picked number from 0 to 100 by " +
            "computer.\nFasten your seatbelt and let's have a great time!";

    public static final String RANDOM_NUMBER_HAS_BEEN_PICKED = "The random " +
            "number has been picked by computer according to the rules.. Input your INT...";

    public static final String THE_RANGE_TO_SEARCH = "Current range to " +
            "search the number was set to [%d; %d]. Try again.";

    public static final String CORRECT_NUMBER = "Yes! " +
            "Congratulations! You've just correctly guessed the number! " +
            "Now, see the statistics of your game: ";

    public static final String WRONG_INPUT = "Something went wrong. Please, " +
            "try again.";

    public static final String INCORRECT_INPUT = "The input number doesn't match previously set range. " +
            "Please, specify the number in following range: [%d; %d]";

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printMessage(String message, int param1, int param2) {
        System.out.printf(message + "\n", param1, param2);
    }

    public void printStatistics(List<String> statisticStorage) {
        printMessage("*** Game statistics ***");
        for (String s: statisticStorage) {
            printMessage(s);
        }
        printMessage("Total attempts: " + statisticStorage.size());
        printMessage("See you next time! Good luck ;)");
    }
}
