package streams;

import streams.students.Course;
import streams.students.Student;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamsToMapChallenge {

    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);

        List<Student> students = IntStream.rangeClosed(1, 5000)
                .mapToObj(i -> Student.getRandomStudent(pymc, jmc))
                .toList();

        streamToMap(students);

        mapToStream(students);
    }

    /**
     * Collectors.groupingBy(), Collectors.partitioningBy(), etc. to group stream elements into maps
     */
    private static void streamToMap(List<Student> students) {
        System.out.println("=> streamToMap() <=");

        var mappedStudents = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode));
        mappedStudents.forEach((k, v) -> System.out.println(k + " " + v.size()));

        System.out.println("------------");
        int minAge = 25;
        var youngerSet = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode,
                        Collectors.filtering(s -> s.getAge() <= minAge, Collectors.toList())));
        youngerSet.forEach((k, v) -> System.out.println(k + " " + v.size()));

        System.out.println("------------");
        var experienced = students.stream()
                .collect(Collectors.partitioningBy(Student::hasProgrammingExperience));
        System.out.println("Experienced Students = " + experienced.get(true).size());

        System.out.println("------------");
        var expCount = students.stream()
                .collect(Collectors.partitioningBy(Student::hasProgrammingExperience, Collectors.counting()));
        System.out.println("Experienced Students = " + expCount.get(true));

        System.out.println("------------");
        var experiencedAndActive = students.stream()
                .collect(Collectors.partitioningBy(s -> s.hasProgrammingExperience() && s.getMonthsSinceActive() == 0,
                        Collectors.counting()));
        System.out.println("Experienced and Active Students = " + experiencedAndActive.get(true));

        System.out.println("------------");
        var multiLevel = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode,
                        Collectors.groupingBy(Student::getGender)));
        multiLevel.forEach((key, value) -> {
            System.out.println(key);
            value.forEach((k, v) -> System.out.println("\t" + k + " " + v.size()));
        });
    }

    /**
     * Stream.flatMap()
     * <p>
     * Used to perform one-to-many transformations, on elements in a stream pipeline.
     * It's called flatMap, because it flattens results from a hierarchical collection, into one stream of uniformly typed elements.
     * Stream.map() vs Stream.flatMap() differentiate in the return type of the function.
     * - For Stream.map(), you return a different instance of an object.
     * In this case, you're exchanging one type, for another, for each element on the stream
     * - For Stream.flatMap(), you return a Stream, which means you're exchanging one element, for a stream of elements back
     * <p>
     * Stream<R> | map(Function<T, R> mapper) | flatMap(Function<T, Stream<R>> mapper)
     */
    private static void mapToStream(List<Student> students) {
        System.out.println("=> mapToStream() <=");

        var experienced = students.stream()
                .collect(Collectors.partitioningBy(Student::hasProgrammingExperience));

        var multiLevel = students.stream()
                .collect(Collectors.groupingBy(Student::getCountryCode,
                        Collectors.groupingBy(Student::getGender)));
        multiLevel.forEach((key, value) -> {
            System.out.println(key);
            value.forEach((k, v) -> System.out.println("\t" + k + " " + v.size()));
        });

        long count = experienced.values().stream()
                .flatMap(l -> l.stream())
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active Students = " + count);

        count = multiLevel.values().stream()
                .flatMap(map -> map.values().stream())
                .flatMap(l -> l.stream())
                .filter(s -> s.getMonthsSinceActive() <= 3)
                .count();
        System.out.println("Active Students in multiLevel = " + count);
    }

}
