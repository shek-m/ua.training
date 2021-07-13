package immutableClass;

import java.util.ArrayList;
import java.util.List;

public final class MyImmutableClass {
    private final int id;
    private final List<Thing> list;

    public MyImmutableClass(int id, List<Thing> list) {
        this.id = id;

        List<Thing> copy = new ArrayList<>();
        for (Thing thing : list) {
            copy.add(new Thing(thing));
        }
        this.list = copy;
    }

    public int getId() {
        return id;
    }

    //using copy constructor instead of clone() (by Joshua Bloch)
    public List<Thing> getList() {
        List<Thing> listCopy = new ArrayList<>();
        for (Thing thing : list) {
            listCopy.add(new Thing(thing));
        }
        return listCopy;
    }
}