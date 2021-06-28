package homework01.task01.simpleMVC;

/**
 *
 * created by MS on 24.06.2021
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    //Constructor
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    //Work method
    public void processUser(){
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            for (int i = 0; i < 2; i++) {
                model.constructString(getInputFromUser(reader));
                if (i==0)
                    model.constructString(" ");
            }
        }
        catch (IOException ignored){}

        view.printMessage(model.getValue());
    }

    //Utility methods
    public String getInputFromUser(BufferedReader reader) throws IOException{
        view.printMessage(View.INPUT_CORRECT_LINE);
        String input = reader.readLine();
        while (!isAcceptable(input)) {
            view.printMessage(View.INCORRECT_LINE + " " + View.INPUT_CORRECT_LINE);
            input = reader.readLine();
        }
        return input;
    }

    public boolean isAcceptable(String str){
        return (str.equals("Hello") && model.getValue().isEmpty())
                || (str.equals("world!") && !model.getValue().isEmpty());
    }
}