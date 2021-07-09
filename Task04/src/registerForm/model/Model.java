package registerForm.model;

import registerForm.model.entity.DataBaseEmulation;
import registerForm.model.entity.Note;

public class Model {
    private DataBaseEmulation db;

    public Model() {
        this.db = DataBaseEmulation.getInstance();
    }

    public void addNote(Note note) {
        db.add(note);
    }
}