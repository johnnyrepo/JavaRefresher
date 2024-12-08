package streams.students;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentsChallenge3 {

    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
        Course jsmc = new Course("JSMC", "JavaScript Masterclass");

        int currentYear = LocalDate.now().getYear();
        var students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc, jsmc))
                .filter(s -> s.getYearEnrolled() >= (currentYear - 4))
                .limit(10_000)
                .toList();

        System.out.println(students.stream()
                .mapToInt(Student::getYearEnrolled)
                .summaryStatistics());

        System.out.println("Amount of students enrolled in each class:");
        var mappedActivity = students.stream()
                .flatMap(s -> s.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.counting()));
        mappedActivity.forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println("Amount of students taking 1, 2, 3 classes:");
        var classCounts = students.stream()
                .collect(Collectors.groupingBy(s -> s.getEngagementMap().size(),
                        Collectors.counting()));
        classCounts.forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println("Average percentage complete for all courses:");
        var percentages = students.stream()
                .flatMap(s -> s.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.averagingDouble(CourseEngagement::getPercentComplete)));
        percentages.forEach((k, v) -> System.out.println(k + " " + v));

        System.out.println("Activity counts by year:");
        var yearMap = students.stream()
                .flatMap(s -> s.getEngagementMap().values().stream())
                .collect(Collectors.groupingBy(CourseEngagement::getCourseCode,
                        Collectors.groupingBy(CourseEngagement::getLastActivityYear,
                                Collectors.counting())));
        yearMap.forEach((k, v) -> System.out.println(k + " " + v));
    }

}
