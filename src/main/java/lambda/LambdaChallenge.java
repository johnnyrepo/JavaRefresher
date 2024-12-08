package lambda;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class LambdaChallenge {

    record Person(String firstName, String lastName) {
        @Override
        public String toString() {
            return firstName + " " + lastName;
        }
    }

    public static void main(String[] args) {
        functionalInterface();

        lambdaExpression();

        functionalInterfaceTypes();
    }

    /**
     * 1. Java requires types which support lambda expressions, to be a Functional Interface
     * 2. Functional Interface is an interface that has one, and only one, abstract method
     */
    private static void functionalInterface() {
        System.out.println("=> Functional Interface <=");
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person("Lucy", "Van Pelt"),
                new Person("Sally", "Brown"),
                new Person("Linus", "Van Pelt"),
                new Person("Peppermint", "Patty"),
                new Person("Charlie", "Brown")
        ));

        people.sort((o1, o2) -> o1.lastName.compareTo(o2.lastName));
        System.out.println(people);

        // Cant be a Functional Interface, because there are more than 1 abstract methods
        // @FunctionalInterface
        interface EnhancedComparator<T> extends Comparator<T> {
            int secondLevel(T o1, T o2);
        }
        var comparatorMixed = new EnhancedComparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                int result = o1.lastName.compareTo(o2.lastName);
                return (result == 0 ? secondLevel(o1, o2) : result);
            }

            @Override
            public int secondLevel(Person o1, Person o2) {
                return o1.firstName.compareTo(o2.firstName);
            }
        };

        people.sort(comparatorMixed);
        System.out.println(people);
    }

    private static void lambdaExpression() {
        System.out.println("=> Lambda Expression <=");
        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"));

        list.forEach(s -> System.out.println(s));
        System.out.println("-------");
        list.forEach(myString -> {
            char first = myString.charAt(0);
            System.out.println(myString + " means " + first);
        });

        var result = calculator((a, b) -> a + b, 5, 2);
        var result2 = calculator((a, b) -> a / b, 10.0, 2.5);
        var result3 = calculator((a, b) -> a.toUpperCase() + " " + b.toUpperCase(), "Ralph", "Lauren");
    }

    private static <T> T calculator(Operation<T> function, T value1, T value2) {
        T result = function.operate(value1, value2);
        System.out.println("Result of operation: " + result);

        return result;
    }

    /**
     * 'java.util.function' has 4 basic types of Functional Interfaces:
     * 1. Consumer  -> void accept(T t)  -> execute code without returning data
     * 2. Function  -> R apply(T t)      -> return a result of an operation or function
     * 3. Predicate -> boolean test(T t) -> test if a condition is true or false
     * 4. Supplier  -> T get()           -> return an instance of something
     */
    private static void functionalInterfaceTypes() {
        System.out.println("=> Functional Interface Types <=");

        List<String> list = new ArrayList<>(List.of(
                "alpha", "bravo", "charlie", "delta"));

        // 'Consumer'
        System.out.println("=> Consumer <=");
        list.forEach(s -> System.out.println(s));
        System.out.println("-------");

        // 'Function'
        System.out.println("=> Function <=");
        // UnaryOperator
        list.replaceAll(s -> s.charAt(0) + " - " + s.toUpperCase());
        list.forEach(s -> System.out.println(s));
        System.out.println("-------");
        // BinaryOperator
        calculator2((a, b) -> a + b, 5, 2);
        System.out.println("-------");
        // IntFunction
        String[] emptyStrings = new String[10];
        System.out.println(Arrays.toString(emptyStrings));
        Arrays.setAll(emptyStrings, i -> (i + 1) + ". "
                + switch (i) {
                    case 0 -> "one";
                    case 1 -> "two";
                    case 2 -> "three";
                    default -> "";
                });
        System.out.println(Arrays.toString(emptyStrings));
        System.out.println("-------");

        // 'Predicate'
        System.out.println("=> Predicate <=");
        list.removeIf(s -> s.toLowerCase().contains("bravo"));
        list.forEach(s -> System.out.println(s));
        System.out.println("-------");

        // 'Supplier'
        System.out.println("=> Supplier <=");
        Supplier<String> supplier = () -> "zulu";
        list.add(supplier.get());
        list.forEach(s -> System.out.println(s));
        System.out.println("-------");

        String[] names = {"Ann", "Bob", "Carol", "David", "Ed", "Fred"};
        String[] randomList = randomlySelectedValues(15, names,
                () -> new Random().nextInt(0, names.length));
        System.out.println(Arrays.toString(randomList));
    }

    private static <T> T calculator2(BinaryOperator<T> function, T value1, T value2) {
        T result = function.apply(value1, value2);
        System.out.println("Result of operation, using BinaryOperator: " + result);

        return result;
    }

    private static String[] randomlySelectedValues(int count, String[] values, Supplier<Integer> s) {
        String[] selectedValues = new String[count];
        for (int i = 0; i < count; i++) {
            selectedValues[i] = values[s.get()];
        }

        return selectedValues;
    }
}
