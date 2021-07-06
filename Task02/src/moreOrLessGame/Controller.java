package moreOrLessGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    private Model model;
    private View view;

    //Constructor
    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    //Work method
    public void processUser() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        view.printMessage(View.GREETINGS);
        launchAccept(reader);

        model.setPickedNumber(model.pickRandomNumber());
        view.printMessage(View.RANDOM_NUMBER_HAS_BEEN_PICKED + "\n" + View.GUESS_THIS_NUMBER);

        int numb;
        int tempComparing;

        do {
            numb = getInputIntFromUser(reader);

            if (!isAcceptable(numb)){
                view.printMessage(View.INCORRECT_INPUT,
                        model.getLowerBound(), model.getHigherBound());
                continue;
            }

            tempComparing = model.compareUsersNumber(numb);
            if (tempComparing > 0) {
                model.addStatistics(numb);
                setNewRange(numb);
                view.printMessage(View.YOUR_NUMBER_IS_GREATER, model.getStatisticStorage().size(), numb);
                view.printMessage(View.THE_RANGE_TO_SEARCH, model.getLowerBound(), model.getHigherBound());
            } else if (tempComparing < 0) {
                model.addStatistics(numb);
                setNewRange(numb);
                view.printMessage(View.YOUR_NUMBER_IS_LESS, model.getStatisticStorage().size(), numb);
                view.printMessage(View.THE_RANGE_TO_SEARCH, model.getLowerBound(), model.getHigherBound());
            } else {
                model.addStatistics(numb);
                model.setGameFinished(true);
                view.printMessage(View.CORRECT_NUMBER, model.getStatisticStorage().size(), numb);
            }
        } while (!model.isGameFinished());

        view.printStatistics(model.getStatisticStorage());
    }

    //Utility methods
    private int getInputIntFromUser(BufferedReader reader) {
        int usersNumber;
        try {
            usersNumber = Integer.parseInt(reader.readLine());
            return usersNumber;
        } catch (IOException | NumberFormatException e) {
            view.printMessage(View.WRONG_INPUT);
            return getInputIntFromUser(reader);
        }
    }

    private boolean isAcceptable(int usersNumber) {
        return model.isMatchingTheRange(usersNumber);
    }

    private void launchAccept(BufferedReader reader) {
        view.printMessage(View.TYPE_START_IF_READY);
        try {
            while (!reader.readLine().equalsIgnoreCase("start"));
        } catch (IOException e) {
            view.printMessage(View.WRONG_INPUT);
            launchAccept(reader);
        }
    }

    private void setNewRange(int numb) {
        if (numb > model.getPickedNumber()) {
            model.setHigherBound(numb-1);
        } else {
            model.setLowerBound(numb+1);
        }
    }
}