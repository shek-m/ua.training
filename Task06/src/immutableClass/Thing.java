package immutableClass;


public class Thing implements Copyable {
    private int age;
    private String name;

    public Thing(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public Thing copy() {
        Thing prototype = new Thing(age, name);
        return prototype;
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