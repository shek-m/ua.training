package moreOrLessGame;

import java.util.List;

public class View {
    //String constants
    public static final String GREETINGS = "Hi, User! Welcome to the " +
            "\"Guess the number\" console game!\nHere, you have to guess " +
            "the randomly picked number from 0 to 100 (inclusively) by " +
            "computer.\nFasten your seatbelt and let's have a great time!";

    public static final String RANDOM_NUMBER_HAS_BEEN_PICKED = "The random " +
            "number has been picked by computer according to the rules..";

    public static final String TYPE_START_IF_READY = "Please, type \"start\" " +
            "to launch the game..";

    public static final String GUESS_THIS_NUMBER = "Guess this number! Type" +
            " it down.";

    public static final String YOUR_NUMBER_IS_GREATER = "Attempt №%d: %d. " +
            "Your number is greater then the one, picked by computer. Try " +
            "again.";

    public static final String YOUR_NUMBER_IS_LESS = "Attempt №%d: %d. " +
            "Your number is less then the one, picked by computer. Try " +
            "again.";

    public static final String THE_RANGE_TO_SEARCH = "Current range to " +
            "search the number was set to [%d; %d].";

    public static final String CORRECT_NUMBER = "Attempt №%d: %d. Yes! " +
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
