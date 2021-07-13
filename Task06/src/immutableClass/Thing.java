package immutableClass;

public class Thing {
    private int age;
    private String name;

    public Thing(int age, String name) {
        this.age = age;
        this.name = name;
    }

    //Copy constructor
    public Thing(Thing thing) {
        this.age = thing.age;
        this.name = thing.name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
