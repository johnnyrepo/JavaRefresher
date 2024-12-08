package enumcollections;

import java.util.*;

public class EnumCollectionsChallenge {

    enum Weekday {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY}

    public static void main(String[] args) {
        List<Weekday> annsWorkDays = new ArrayList<>(List.of(Weekday.MONDAY,
                Weekday.TUESDAY, Weekday.THURSDAY, Weekday.FRIDAY));

        System.out.println("=> EnumSet");
        // copyOf()
        var annsDaysSet = EnumSet.copyOf(annsWorkDays);
        System.out.println(annsDaysSet.getClass().getSimpleName());
        annsDaysSet.forEach(System.out::println);

        // allOf()
        System.out.println("-----------------------");
        var allDaysSet = EnumSet.allOf(Weekday.class);
        allDaysSet.forEach(System.out::println);

        // complementOf()
        System.out.println("-----------------------");
        Set<Weekday> newPersonDays = EnumSet.complementOf(annsDaysSet);
        newPersonDays.forEach(System.out::println);

        // range()
        System.out.println("-----------------------");
        Set<Weekday> businessDays = EnumSet.range(Weekday.MONDAY, Weekday.FRIDAY);
        businessDays.forEach(System.out::println);

        System.out.println("=> EnumMap");
        Map<Weekday, String[]> employeeMap = new EnumMap<>(Weekday.class);
        employeeMap.put(Weekday.FRIDAY, new String[] {"Ann", "Mary", "Bob"});
        employeeMap.put(Weekday.MONDAY, new String[] {"Mary", "Bob"});
        employeeMap.forEach((k, v) -> System.out.println(k + ": " + Arrays.toString(v)));
    }

}
