package registerForm.model;

import registerForm.model.entity.DataBaseEmulation;
import registerForm.model.entity.NotUniqueLoginException;
import registerForm.model.entity.Note;

public class Model {
    private DataBaseEmulation db;

    public Model() {
        this.db = DataBaseEmulation.getInstance();
    }

    public void addNote(Note note) throws NotUniqueLoginException {
        db.add(note);
    }

    public Note removeLastAndGet() {
        return db.removeLastAndGet();
    }
}