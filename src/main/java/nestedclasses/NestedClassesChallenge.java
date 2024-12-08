package nestedclasses;

import nestedclasses.model.Employee;
import nestedclasses.model.StoreEmployee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NestedClassesChallenge {

    /**
     * Going through all types of nested classes:
     * 1. Static Nested Class
     * 2. Inner Class
     * 3. Local Class
     * 4. Anonymous Class
     */
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(10001, "Ralph", 2015),
                new Employee(10005, "Carole", 2021),
                new Employee(10022, "Jane", 2013),
                new Employee(13515, "Laura", 2020),
                new Employee(10500, "Jim", 2018)
        ));

        employees.sort(new Employee.EmployeeComparator<>());
        printEmployees(employees);

        employees.sort(new Employee.EmployeeComparator<>("yearStarted").reversed());
        printEmployees(employees);

        System.out.println("Store Employees");
        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Mercadona"),
                new StoreEmployee(10515, "Joe", 2021, "Maxima"),
                new StoreEmployee(10689, "Tom", 2020, "Rimi"),
                new StoreEmployee(10777, "Marty", 2018, "Maxima"),
                new StoreEmployee(10999, "Bud", 2016, "Rimi")
        ));

        /* Instantiation of the Inner Class */
        var comparator = new StoreEmployee().new StoreComparator<>();
        storeEmployees.sort(comparator);
        printEmployees(storeEmployees);

        System.out.println("With Pig Latin Names");
        addPigLatinName(storeEmployees);

        System.out.println("Sort by Names using Anonymous Class");
        /**
         * ==> Anonymous Class <==
         *
         * Anonymous classes are used a lot less, since the introduction of Lambda Expressions in Java 8
         */
        var nameComparator = new Comparator<StoreEmployee>() {
            @Override
            public int compare(StoreEmployee o1, StoreEmployee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        storeEmployees.sort(nameComparator);
        printEmployees(storeEmployees);
    }

    private static void printEmployees(List<? extends Employee> employees) {
        for (Employee e : employees) {
            System.out.println(e);
        }
        System.out.println();
    }

    public static void addPigLatinName(List<? extends StoreEmployee> list) {
        /**
         * ==> Local Class <==
         * 1. Enclosing method can access all private fields of the Local Class
         * 2. Variable from enclosing method has to be final or 'effectively' final in order to be used inside Local Class
         */
        class DecoratedEmployee extends StoreEmployee implements Comparable<DecoratedEmployee> {
            private String pigLatinName;
            private Employee originalInstance;

            public DecoratedEmployee(String pigLatinName, Employee originalInstance) {
                this.pigLatinName = pigLatinName;
                this.originalInstance = originalInstance;
            }

            @Override
            public String toString() {
                return originalInstance + " " + pigLatinName;
            }

            @Override
            public int compareTo(DecoratedEmployee o) {
                return pigLatinName.compareTo(o.pigLatinName);
            }
        }

        List<DecoratedEmployee> newList = new ArrayList<>(list.size());
        for (var employee : list) {
            String name = employee.getName();
            String pigLatin = name.substring(1) + name.charAt(0) + "ay";
            newList.add(new DecoratedEmployee(pigLatin, employee));
        }
        // sort by default comparator
        newList.sort(null); // 'null' == Comparator.naturalOrder()
        printEmployees(newList);
    }

}
