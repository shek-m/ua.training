package immutableClass;

import java.util.ArrayList;
import java.util.List;

public final class MyImmutableClass {
    private final int id;
    private final List<String> list;

    public MyImmutableClass(int id, List<String> list) {
        this.id = id;
        this.list = new ArrayList<>(list);
    }

    public int getId() {
        return id;
    }

    public List<String> getList() {
        return new ArrayList<>(list);
    }
}
