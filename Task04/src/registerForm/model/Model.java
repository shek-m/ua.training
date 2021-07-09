package registerForm.model;

import registerForm.model.entity.DataBaseEmulation;
import registerForm.model.entity.Note;

public class Model {
    private DataBaseEmulation db;

    public Model() {
        this.db = DataBaseEmulation.getInstance();
        addSeveralNotesToStorage();
    }

    public void addNote(Note note) {
        db.add(note);
    }

    //hard-coded Notes for NotUniqueLoginException check
    private void addSeveralNotesToStorage() {
        addNote(new Note("Михайло", "endurance2296"));
        addNote(new Note("Владислав", "vladislavandri"));
    }
}