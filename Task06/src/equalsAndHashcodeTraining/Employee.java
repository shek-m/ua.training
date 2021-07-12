package equalsAndHashcodeTraining;

public class Employee {
    private int age;
    private String name;
    private String surname;

    public Employee () {}

    public Employee (int age, String name, String surname) {
        this.age = age;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() == this.getClass()) {
            Employee temp = (Employee) obj;
            return this.age == temp.age && this.name.equals(temp.name)
                    && this.surname.equals(temp.surname);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (31 * age + (name == null ? 0 : name.hashCode()))
                * 31 + (surname == null ? 0 : surname.hashCode());
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}