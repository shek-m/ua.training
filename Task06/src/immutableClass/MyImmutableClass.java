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
            copy.add(thing.copy());
        }
        this.list = copy;
    }

    public int getId() {
        return id;
    }

    public List<Thing> getList() {
        List<Thing> listCopy = new ArrayList<>();
        for (Thing thing : list) {
            listCopy.add(thing.copy());
        }
        return listCopy;
    }
}