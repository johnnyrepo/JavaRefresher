package setsnmaps;

import java.time.LocalDate;
import java.util.*;

public class OrderedMapChallenge {

    private static Map<String, Purchase> purchases = new LinkedHashMap<>();
    private static NavigableMap<String, Student> students = new TreeMap<>();

    public static void main(String[] args) {
        Course jmc = new Course("jmc101", "Java Master Class",
                "Java");
        Course python = new Course("pyt101", "Python Master Class",
                "Python");

        addPurchase("Mary Martin", jmc, 129.99);
        addPurchase("Andy Martin", jmc, 139.99);
        addPurchase("Mary Martin", python, 149.99);
        addPurchase("Joe Jones", jmc, 149.99);
        addPurchase("Bill Brown", python, 119.99);

        addPurchase("Chuck Cheese", python, 119.99);
        addPurchase("Davey Jones", jmc, 139.99);
        addPurchase("Eva East", python, 139.99);
        addPurchase("Fred Forker", jmc, 139.99);
        addPurchase("Greg Brady", python, 129.99);

        purchases.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("-----------------------");
        students.forEach((key, value) -> System.out.println(key + ": " + value));

        NavigableMap<LocalDate,List<Purchase>> datedPurchases = new TreeMap<>();

        for (Purchase p : purchases.values() ) {
            datedPurchases.compute(p.purchaseDate(),
                    (pdate, plist) -> {
                        List<Purchase> list =
                                (plist == null) ? new ArrayList<>() : plist;
                        list.add(p);
                        return list;
                    });
        }
        datedPurchases.forEach((key, value) -> System.out.println(key + ": " + value));

        // SortedMap.headMap() & SortedMap.tailMap()
        int currentYear = LocalDate.now().getYear();
        LocalDate firstDay = LocalDate.ofYearDay(currentYear, 1);
        LocalDate week1 = firstDay.plusDays(7);
        Map<LocalDate, List<Purchase>> weeks1Purchases = datedPurchases.headMap(week1);
        Map<LocalDate, List<Purchase>> weeks2Purchases = datedPurchases.tailMap(week1);

        System.out.println("-----------------------");
        weeks1Purchases.forEach((key, value) -> System.out.println(key + ": " + value));
        System.out.println("-----------------------");
        weeks2Purchases.forEach((key, value) -> System.out.println(key + ": " + value));

        displayStats(1, weeks1Purchases);
        displayStats(2, weeks2Purchases);
        System.out.println("-----------------------");

        // SortedMap.lastKey()
        // SortedMap.lastEntry()
        LocalDate lastDate = datedPurchases.lastKey();
        var previousEntry = datedPurchases.lastEntry();

        List<Purchase> lastDaysData = previousEntry.getValue();
        System.out.println(lastDate + " purchases: " + lastDaysData.size());
        System.out.println("-----------------------");

        // SortedMap.lowerKey()
        // SortedMap.lowerEntry()
        while (previousEntry != null) {
            List<Purchase> lastData = previousEntry.getValue();
            System.out.println(lastDate + " purchases: " + lastData.size());

            LocalDate prevDate = datedPurchases.lowerKey(lastDate);
            previousEntry = datedPurchases.lowerEntry(lastDate);
            lastDate = prevDate;
        }
        System.out.println("-----------------------");

        // SortedMap.descendingMap()
        // SortedMap.higherKey()
        // SortedMap.higherEntry()
        var reversed = datedPurchases.descendingMap();
        LocalDate firstDate = reversed.firstKey();
        var nextEntry = reversed.firstEntry();
        while (nextEntry != null) {
            List<Purchase> firstData = nextEntry.getValue();
            System.out.println(firstDate + " purchases: " + firstData.size());

            LocalDate nextDate = reversed.higherKey(firstDate);
            nextEntry = reversed.higherEntry(firstDate);
            firstDate = nextDate;
        }

        // To remove the first or last entry from the Map
        // SortedMap.pollFirstEntry()
        // SortedMap.pollLastEntry()
    }

    private static void addPurchase(String name, Course course, double price) {
        Student existingStudent = students.get(name);
        if (existingStudent == null) {
            existingStudent = new Student(name, course);
            students.put(name, existingStudent);
        } else {
            existingStudent.addCourse(course);
        }

        int day = new Random().nextInt(1, 15);
        String key = course.courseId() + "_" + existingStudent.getId();
        int year = LocalDate.now().getYear();
        Purchase purchase = new Purchase(course.courseId(),
                existingStudent.getId(), price, year, day);
        purchases.put(key, purchase);
    }

    private static void displayStats(int period, Map<LocalDate, List<Purchase>> periodData) {
        System.out.println("-----------------------");
        Map<String, Integer> weeklyCounts = new TreeMap<>();
        periodData.forEach((key, value) -> {
            System.out.println(key + ": " + value);
            for (Purchase p : value) {
                weeklyCounts.merge(p.courseId(), 1, (prev, current) -> {
                    return prev + current;
                });
            }
        });
        System.out.printf("Week %d Purchases = %s%n", period, weeklyCounts);
    }

}
