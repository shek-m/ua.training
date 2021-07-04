package homework04.task01.registrationForm;

import java.util.ArrayList;

/**
 * Created by MShekera on 04.07.2021
 */
public class DataBaseEmulation {
    private static DataBaseEmulation instance;

    private ArrayList<Note> noteTable = new ArrayList<>();

    private DataBaseEmulation(){}

    //Singleton
    public static DataBaseEmulation getInstance() {
        if (instance == null) {
            instance = new DataBaseEmulation();
        }
        return instance;
    }

    public void add(Note note) {
        noteTable.add(note);
    }
}