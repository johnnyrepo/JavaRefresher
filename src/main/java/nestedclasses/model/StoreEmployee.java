package nestedclasses.model;

import java.util.Comparator;

public class StoreEmployee extends Employee {
    private String store;

    public StoreEmployee(int id, String name, int yearStarted, String store) {
        super(id, name, yearStarted);
        this.store = store;
    }

    public StoreEmployee() {
        super();
    }

    @Override
    public String toString() {
        return "%-10s%s".formatted(store, super.toString());
    }

    /**
     * ==> Inner Class <==
     *
     * 1. Inner classes are non-static classes, declared on an enclosing class, at the member level.
     * 2. Inner classes can have any of the four valid access modifiers.
     * 3. Inner class has access to instance members, including private members, of the enclosing class.
     *
     * Since Java 16
     * Static members of all types are supported on inner classes.
     */
    public class StoreComparator<T extends StoreEmployee> implements Comparator<StoreEmployee> {

        @Override
        public int compare(StoreEmployee o1, StoreEmployee o2) {
            int result = o1.store.compareTo(o2.store);
            if (result == 0) {
                return new Employee.EmployeeComparator("yearStarted").compare(o1, o2);
            }

            return result;
        }
    }
}
