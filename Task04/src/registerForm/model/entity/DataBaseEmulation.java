package registerForm.model.entity;

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
            instance.addSeveralNotesToStorage();    //hard-coded Notes
        }
        return instance;
    }

    public void add(Note note) throws NotUniqueLoginException {
        noteTable.add(note);
        if (!isLoginUnique(note.getNickname())) {
            throw new NotUniqueLoginException();
        }
    }

    public Note removeLastAndGet() {
        Note temp = noteTable.get(noteTable.size()-1);
        noteTable.remove(temp);
        return temp;
    }

    //Utility methods
    public boolean isLoginUnique(String login) {
        for (int i = 0; i < noteTable.size()-1; i++) {
            if (login.equals(noteTable.get(i).getNickname())){
                return false;
            }
        }
        return true;
    }

    //hard-coded Notes for NotUniqueLoginException check
    private void addSeveralNotesToStorage() {
        noteTable.add(new Note("Михайло", "endurance2296"));
        noteTable.add(new Note("Владислав", "vladislavandri"));
        noteTable.add(new Note("Alex", "alexdarkstalker98"));
        noteTable.add(new Note("Jonas", "jonasbros"));
    }
}