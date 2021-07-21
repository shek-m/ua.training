package blockedArrayList;

import blockedArrayList.mutableClassExample.Person;

public class Main {
    public static void main(String[] args) {
        BlackBox<String> bb = new BlackBox<>();
        bb.add("Misha");
        bb.add("Anna");

        bb.forEach(System.out::println);

        bb.remove("Anna");
        bb.remove(0);

        bb.forEach(System.out::println);

        //Mutable objects still can be modified using get()
        BlackBox<Person> bbPerson = new BlackBox<>();
        bbPerson.add(new Person("Misha", 24));
        bbPerson.add(new Person("Anna", 25));

        bbPerson.forEach(System.out::println);

        Person x = bbPerson.get(0);
        x.setName("Piotr");

        System.out.println();

        bbPerson.forEach(System.out::println);
    }
}