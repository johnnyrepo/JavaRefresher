package nestedclasses.model;

import java.util.Comparator;

public class Employee {

    /**
     * ==> Static Nested Class <==
     *
     * 1. It has access to private fields of enclosing class.
     * 2. Enclosing class has access to nested class's private fields as well.
     */
    public static class EmployeeComparator<T extends Employee> implements Comparator<Employee> {

        private String sortType;

        public EmployeeComparator() {
            this("name");
        }

        public EmployeeComparator(String sortType) {
            this.sortType = sortType;
        }

        @Override
        public int compare(Employee o1, Employee o2) {
            return switch(sortType) {
                case "id" -> Integer.compare(o1.id, o2.id);
                case "yearStarted" -> o1.yearStarted - o2.yearStarted;
                default -> o1.name.compareTo(o2.name);
            };
        }
    }

    private int id;
    private String name;
    private int yearStarted;

    public Employee() {

    }

    public Employee(int id, String name, int yearStarted) {
        this.id = id;
        this.name = name;
        this.yearStarted = yearStarted;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "%d %-8s %d".formatted(id, name, yearStarted);
    }
}
