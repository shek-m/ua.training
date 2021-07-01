package homework03.task01.moreOrLessGameUpdated;

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
        view.printMessage(View.RANDOM_NUMBER_HAS_BEEN_PICKED);

        model.setPrimaryBarrier(GlobalConstants.PRIMARY_LOWER_BARRIER, GlobalConstants.PRIMARY_HIGHER_BARRIER);
        model.setPickedNumber(model.pickRandomNumber());

        while (!model.checkValue(getInputIntFromUser(reader))) {
            view.printMessage(View.THE_RANGE_TO_SEARCH, model.getLowerBound(), model.getHigherBound());
        }

        view.printMessage(View.CORRECT_NUMBER);
        view.printStatistics(model.getStatisticStorage());
    }

    //Utility methods
    public int getInputIntFromUser(BufferedReader reader) {
        int usersNumber;
        try {
            usersNumber = Integer.parseInt(reader.readLine());
            if (model.isMatchingTheRange(usersNumber)) {
                return usersNumber;
            } else {
                view.printMessage(View.INCORRECT_INPUT, model.getLowerBound(), model.getHigherBound());
                return getInputIntFromUser(reader);
            }
        } catch (IOException | NumberFormatException e) {
            view.printMessage(View.WRONG_INPUT);
            return getInputIntFromUser(reader);
        }
    }
}