package javapractice.lambdas;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@FunctionalInterface
interface Condition{
    boolean test(Person person);
}

public class LambdaExercise1 {

    public static void main(String[] args) {

        List<Person> listOfPerson = Arrays.asList(
          new Person("ajay",30),
          new Person("ravi",28)
        );

        //sort by last name
        Collections.sort(listOfPerson,(person1,person2) ->person1.getLastName().compareTo(person2.getLastName()));

        //print all data
        printData(listOfPerson);
        System.out.println("printing all data");
        printListWithInBuiltCondition(listOfPerson, (person) -> true);
        //sout data while passing arg to Consumer Function
        printDataWithCondtion(listOfPerson,(person)->true,(person)-> System.out.println(person));

        //list start with c
        printListWithCondition(listOfPerson, (person) -> person.getLastName().startsWith("a"));

        printListWithInBuiltCondition(listOfPerson, (person) -> person.getLastName().startsWith("a"));
    }

    private static void printDataWithCondtion(List<Person> listOfPerson, Predicate<Person> personPredicate, Consumer<Person> consumer) {
        if(listOfPerson != null){
            System.out.println("consumer  ");
            for (Person person:listOfPerson) {
                if (personPredicate.test(person)){
                    consumer.accept(person);
                }
            }
        }
    }

    private static void printListWithCondition(List<Person> listOfPerson, Condition condition) {
        if(listOfPerson != null){
            System.out.println("start with a ");
            for (Person person:listOfPerson) {
                if (condition.test(person)){
                    System.out.println(person.toString());
                }
            }
        }

    }


    private static void printListWithInBuiltCondition(List<Person> listOfPerson, Predicate<Person> condition) {
        if(listOfPerson != null){
            System.out.println("printListWithInBuiltCondition ");
            for (Person person:listOfPerson) {
                if (condition.test(person)){
                    System.out.println(person.toString());
                }
            }
        }

    }
    private static void printData(List<Person> listOfPerson) {
        /*if(listOfPerson != null){
            for (Person person:listOfPerson){
                System.out.println(person.toString());
            }
        }*/
        //other way
        listOfPerson.stream().forEach(person -> System.out.println(person.toString()));
    }

}
