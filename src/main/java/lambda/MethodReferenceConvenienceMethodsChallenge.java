package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class MethodReferenceConvenienceMethodsChallenge {

    public static void main(String[] args) {
        functionAndConsumerMethods();

        predicateMethods();

        comparatorMethods();
    }

    /**
     * 'Function' & 'Consumer' convenience methods:
     * 1. andThen() - Not implemented on IntFunction, DoubleFunction, LongFunction
     * 2. compose() - Only implemented on 'Function' & UnaryOperator
     */
    private static void functionAndConsumerMethods() {
        System.out.println("=> 'Function' & 'Consumer' convenience methods: andThen(), compose()");

        String name = "Jim";
        Function<String, String> uCase = String::toUpperCase;
        System.out.println(uCase.apply(name));

        Function<String, String> lastName = s -> s.concat(" TheEarthWorm");
        Function<String, String> uCaseLastName = uCase.andThen(lastName);
        System.out.println(uCaseLastName.apply(name));

        uCaseLastName = uCase.compose(lastName);
        System.out.println(uCaseLastName.apply(name));

        String[] names = {"Ann", "Bob", "Carol"};
        Consumer<String> s0 = s -> System.out.print(s.charAt(0));
        Consumer<String> s1 = System.out::println;
        Arrays.asList(names).forEach(s0.andThen(s -> System.out.print(" - ")).andThen(s1));
    }

    /**
     * 'Predicate' convenience methods:
     * 1. or()
     * 2. and()
     * 3. negate()
     */
    private static void predicateMethods() {
        System.out.println("=> 'Predicate' convenience methods: or(), and(), negate()");

        String name = "Jim";

        Predicate<String> p1 = s -> s.equals("JIM");
        Predicate<String> p2 = s -> s.equalsIgnoreCase("Jim");
        Predicate<String> p3 = s -> s.startsWith("J");
        Predicate<String> p4 = s -> s.endsWith("o");

        Predicate<String> combined1 = p1.or(p2);
        System.out.println("combined1 = " + combined1.test(name));

        Predicate<String> combined2 = p3.and(p4);
        System.out.println("combined2 = " + combined2.test(name));

        Predicate<String> combined3 = p3.and(p4).negate();
        System.out.println("combined3 = " + combined3.test(name));
    }

    /**
     * Comparator convenience methods
     */
    private static void comparatorMethods() {
        System.out.println("=> Comparator convenience methods");

        record Person(String firstName, String lastName) {}

        List<Person> list = new ArrayList<>(Arrays.asList(
                new Person("Peter", "Pan"),
                new Person("Peter", "PumpkinEater"),
                new Person("Minnie", "Mouse"),
                new Person("Mickey", "Mouse")
        ));

        list.sort(Comparator.comparing(Person::lastName));
        list.forEach(System.out::println);

        System.out.println("-------");
        list.sort(Comparator.comparing(Person::lastName)
                .thenComparing(Person::firstName));
        list.forEach(System.out::println);

        System.out.println("-------");
        list.sort(Comparator.comparing(Person::lastName)
                .thenComparing(Person::firstName)
                .reversed());
        list.forEach(System.out::println);
    }

}
