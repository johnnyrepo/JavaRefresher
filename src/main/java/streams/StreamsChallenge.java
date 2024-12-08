package streams;

import streams.students.Course;
import streams.students.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class StreamsChallenge {

    public static void main(String[] args) {
        streamSourceOperations();

        streamIntermediateOperations();

        streamTerminalOperations();

        streamTerminalOperationsContinued();

        streamTerminalOperationOptional();

//        bingoChallenge();
    }

    /**
     * Stream source operations:
     * map(), concat(), generate(), iterate(), range()
     * These operations return the Stream
     */
    private static void streamSourceOperations() {
        System.out.println("=> Stream Source Operations<=");
        String[] strings = {"One", "Two", "Three"};
        var firstStream = Arrays.stream(strings)
                .sorted(Comparator.reverseOrder());

        var secondStream = Stream.of("Six", "Five", "Four")
                .map(String::toUpperCase);

        Stream.concat(secondStream, firstStream)
                .map(s -> s.charAt(0) + " - " + s)
                .forEach(System.out::println);
        System.out.println("------------");

        System.out.println("=> Infinite Stream");
        Random random = new Random();
        Stream.generate(() -> random.nextInt(2))
                .limit(10)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.iterate(1, n -> n + 1)
                .limit(20)
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        IntStream.range(1, 20)
                .forEach(s -> System.out.print(s + " "));
    }

    /**
     * Stream intermediate operations:
     * distinct(), filter(), limit(), skip(), takeWhile(), dropWhile(), map(), peek(), sorted()
     * These operations may change the number of elements in the resulting Stream
     */
    private static void streamIntermediateOperations() {
        System.out.println("\n=> Stream Intermediate Operations <=");

        // filter()
        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();

        // skip()
        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .skip(5)
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();

        // NB! dropWhile() & takeWhile() work well with ordered streams.
        // Result might be un-deterministic with unordered streams

        // dropWhile()
        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .dropWhile(i -> Character.toUpperCase(i) <= 'E')
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();

        // takeWhile()
        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .takeWhile(i -> i < 'a')
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println();

        // distinct()
        IntStream.iterate((int) 'A', i -> i <= (int) 'z', i -> i + 1)
                .filter(Character::isAlphabetic)
                .map(Character::toUpperCase)
                .distinct()
                .forEach(d -> System.out.printf("%c ", d));

        System.out.println("\n------------");

        // map()
        int maxSeats = 100;
        int seatsInRow = 10;
        var stream = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1));
        stream.forEach(System.out::println);

        System.out.println("\n------------");

        // boxed()
        var stream2 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                .mapToDouble(Seat::price)
                .boxed()
                .map("%.2f"::formatted);
        stream2.forEach(System.out::println);

        System.out.println("\n------------");

        // sorted()
        var stream3 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                .sorted(Comparator.comparing(Seat::price)
                        .thenComparing(Seat::toString));
        stream3.forEach(System.out::println);

        System.out.println("\n------------");

        // peek() --> used mainly to support debugging of streams
        var stream4 = Stream.iterate(0, i -> i < maxSeats, i -> i + 1)
                .map(i -> new Seat((char) ('A' + i / seatsInRow), i % seatsInRow + 1))
                .skip(5)
                .limit(10)
                .peek(s -> System.out.println("--> " + s))
                .sorted(Comparator.comparing(Seat::price)
                        .thenComparing(Seat::toString));
        stream4.forEach(System.out::println);
    }

    /**
     * Stream terminal operations:
     * 1. Matching and searching:
     *    allMatch(), anyMatch(), findAny(), findFirst(), noneMatch()
     * 2. Transformations and type reductions:
     *    collect(), reduce(), toArray(), toList()
     * 3. Statistical (Numeric) reductions:
     *    average(), count(), max(), min(), sum(), summaryStatistics()
     * 4. Processing:
     *    forEach(), forEachOrdered()
     */
    private static void streamTerminalOperations() {
        System.out.println("\n=> Stream Terminal Operations <=");

        // summaryStatistics()
        var result = IntStream.iterate(0, i -> i <= 1000, i -> i + 10)
                .summaryStatistics();
        System.out.println("Result = " + result);

        var leapYearData = IntStream.iterate(2000, i -> i <= 2025, i -> i + 1)
                .filter(i -> i % 4 == 0)
                .peek(System.out::println)
                .summaryStatistics();
        System.out.println("Leap Year Data = " + leapYearData);

        SeatReservation[] seats = new SeatReservation[100];
        Arrays.setAll(seats, i -> new SeatReservation((char) ('A' + i / 10), i % 10 + 1));

        // count()
        long reservationCount = Arrays.stream(seats)
                .filter(SeatReservation::isReserved)
                .count();
        System.out.println("reservationCount = " + reservationCount);

        // anyMatch()
        boolean hasBookings = Arrays.stream(seats)
                .anyMatch(SeatReservation::isReserved);
        System.out.println("hasBookings = " + hasBookings);

        // allMatch()
        boolean fullyBooked = Arrays.stream(seats)
                .allMatch(SeatReservation::isReserved);
        System.out.println("fullyBooked = " + fullyBooked);

        // noneMatch()
        boolean eventWashedOut = Arrays.stream(seats)
                .noneMatch(SeatReservation::isReserved);
        System.out.println("eventWashedOut = " + eventWashedOut);
    }

    /**
     * 1. collect() & Collector
     * 2. reduce()
     */
    private static void streamTerminalOperationsContinued() {
        System.out.println("\n=> Stream Terminal Operations Continued <=");

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(1000)
                .toList();

        System.out.println("=> collect()");

        Set<Student> australianStudents = students.stream()
                .filter(s -> s.getCountryCode().equals("AU"))
                .collect(Collectors.toSet());
        System.out.println("# of Australian students = " + australianStudents.size());

        Set<Student> under30 = students.stream()
                .filter(s -> s.getAgeEnrolled() < 30)
                .collect(Collectors.toSet());
        System.out.println("# of Under 30 students = " + under30.size());

        Set<Student> youngAussies1 = new TreeSet<>(Comparator.comparing(
                Student::getStudentId));
        youngAussies1.addAll(australianStudents);
        youngAussies1.retainAll(under30);
        youngAussies1.forEach(s -> System.out.print(s.getStudentId() + " "));
        System.out.println();

        // NB!!! Alternative to code from the top, but using single stream pipeline
        Set<Student> youngAussies2 = students.stream()
                .filter(s -> s.getAgeEnrolled() < 30)
                .filter(s -> s.getCountryCode().equals("AU"))
                .collect(() -> new TreeSet<>(Comparator.comparing(Student::getStudentId)),
                        TreeSet::add, TreeSet::addAll);
        youngAussies2.forEach(s -> System.out.print(s.getStudentId() + " "));
        System.out.println();

        System.out.println("=> reduce()");
        String countryList = students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .reduce("", (r, v) -> r + " " + v);
        System.out.println("countryList=" + countryList);
    }

    /**
     * The downside of Optional:
     * 1. Wrapping elements in Optional will consume more memory and has the possibility of slowing down execution
     * 2. Wrapping elements in Optional adds complexity, and reduces readability of your code
     * 3. Optional is not serializable
     * 4. Using Optional for fields or method parameters is not recommended
     *
     * https://stackoverflow.com/questions/26327957/should-java-8-getters-return-optional-type/26328555#26328555
     */
    private static void streamTerminalOperationOptional() {
        System.out.println("\n=> Stream Optional <=");

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");

        List<Student> students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(1000)
                .collect(Collectors.toList());

        Optional<Student> o1 = getStudent(new ArrayList<>(), "first");
        System.out.println("Empty = " + o1.isEmpty() + ", Present = " + o1.isPresent());
        System.out.println(o1);
        o1.ifPresentOrElse(System.out::println, () -> System.out.println("--> Empty"));

        Optional<Student> o2 = getStudent(students, "first");
        System.out.println("Empty = " + o2.isEmpty() + ", Present = " + o2.isPresent());
        System.out.println(o2);
        o2.ifPresent(System.out::println);

        // Student firstStudent = o2.orElse(getDummyStudent(jmc));
        // More performant code, because of lambda use
        Student firstStudent = o2.orElseGet(() -> getDummyStudent(jmc));
        long id = firstStudent.getStudentId();
        System.out.println("firstStudent's id is " + id);

        List<String> countries = students.stream()
                        .map(Student::getCountryCode)
                        .distinct()
                        .toList();
        Optional.of(countries)
                .map(l -> String.join(",", l))
                .filter(l -> l.contains("FR"))
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("Missing FR"));

        System.out.println("------------");

        int minAge = 21;
        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .findAny()
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                        s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .min(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                                s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .max(Comparator.comparing(Student::getAge))
                .ifPresentOrElse(s -> System.out.printf("Student %d from %s is %d%n",
                                s.getStudentId(), s.getCountryCode(), s.getAge()),
                        () -> System.out.println("Didn't find anyone under " + minAge));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .mapToInt(Student::getAge)
                .average()
                .ifPresentOrElse(a -> System.out.printf("Avg age under %d: %.2f%n", minAge, a),
                        () -> System.out.println("Didn't find anyone under " + minAge));

        students.stream()
                .filter(s -> s.getAge() <= minAge)
                .map(Student::getCountryCode)
                .distinct()
                .reduce((a, b) -> String.join(",", a, b))
                .ifPresentOrElse(System.out::println,
                        () -> System.out.println("None found"));
    }

    private static Optional<Student> getStudent(List<Student> list, String type) {
        if (list == null || list.size() == 0) {
            return Optional.empty();
        } else if (type.equals("first")) {
            return Optional.ofNullable(list.get(0));
        } else if (type.equals("last")) {
            return Optional.ofNullable(list.get(list.size() - 1));
        }

        return Optional.ofNullable(list.get(new Random().nextInt(list.size())));
    }

    private static Student getDummyStudent(Course... courses) {
        System.out.println("Getting the dummy student");
        return new Student("NO", 1, 1, "U", false, courses);
    }

    private static void bingoChallenge() {
        System.out.println("=> Bingo Challenge <=");

        var bStream = IntStream.range(0, 16)
                .mapToObj(i -> "B" + i);

        var iStream = IntStream.rangeClosed(16, 30)
                .mapToObj(i -> "I" + i);

        var nStream = Stream.iterate(31, n -> n <= 45, n -> n + 1)
                .map(i -> "N" + i);

        String[] gLabels = new String[15];
        Arrays.setAll(gLabels, i -> "G" + (46 + i));
        var gStream = Arrays.stream(gLabels);

        var oStream = Arrays.stream(new int[]{61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75})
                .mapToObj(i -> "O" + i);

        var biStream = Stream.concat(bStream, iStream);
        var ngStream = Stream.concat(nStream, gStream);
        var bingStream = Stream.concat(biStream, ngStream);
        var bingoStream = Stream.concat(bingStream, oStream);
        bingoStream.forEach(System.out::println);

        System.out.println("------------");

        // Stream.distinct() method usage
        Stream.generate(() -> new Random().nextInt(61, 76))
                .distinct()
                .limit(15)
                .map(i -> "O" + i)
                .sorted()
                .forEach(System.out::println);
    }

}
