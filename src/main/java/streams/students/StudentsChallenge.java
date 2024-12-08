package streams.students;

import java.util.List;
import java.util.stream.Stream;

public class StudentsChallenge {

    public static void main(String[] args) {

        Course pymc = new Course("PYMC", "Python Masterclass");
        Course jmc = new Course("JMC", "Java Masterclass");
        Course jsmc = new Course("JSMC", "JavaScript Masterclass");
        Course dmc = new Course("DMC", "Docker Masterclass");
        Course gmc = new Course("DMC", "Go Masterclass");
//        Student tim = new Student("AU", 2019, 30, "M",
//                true, jmc, pymc);
//        System.out.println(tim);
//
//        tim.watchLecture("JMC", 10, 5, 2019);
//        tim.watchLecture("PYMC", 7, 7, 2020);
//        System.out.println(tim);

        var students = Stream.generate(() -> Student.getRandomStudent(jmc, pymc, jsmc, dmc, gmc))
                .limit(100)
                .toList();
        // data printout
        students.forEach(System.out::println);

        // Students age summaryStatistics
        var enrollmentAgeStream = students.stream()
                .mapToInt(Student::getAgeEnrolled);
        System.out.println("Students enrollment age summary statistics = " +  enrollmentAgeStream.summaryStatistics());

        var currentAgeStream = students.stream()
                .mapToInt(Student::getAge);
        System.out.println("Students current age summary statistics = " +  currentAgeStream.summaryStatistics());

        // Students gender distribution
        for (String gender : List.of("M", "F", "U")) {
            var studentsCount = students.stream()
                    .filter(s -> gender.equals(s.getGender()))
                    .count();
            System.out.println(gender + " students count = " + studentsCount);
        }

        // Age <30
        long age30 = students.stream()
                .filter(s -> s.getAge() < 30)
                .count();
        System.out.println("Students (aged<30) count = " + age30);

        // Age 30 - 60
        long age3060 = students.stream()
                .filter(s -> s.getAge() > 30 && s.getAge() <= 60)
                .count();
        System.out.println("Students (aged 30 - 60) count = " + age3060);

        // Age 60+
        long age60Plus = students.stream()
                .filter(s -> s.getAge() > 60)
                .count();
        System.out.println("Students (aged 60+) count = " + age60Plus);

        // Student countries
        var studentCountries = students.stream()
                .map(Student::getCountryCode)
                .distinct()
                .sorted()
                .toList();
        System.out.println("Students are from the following countries = " + studentCountries);

        // Students enrolled 7+ years ago and still active
        boolean activeOlds = students.stream()
                .anyMatch(s -> (s.getAge() - s.getAgeEnrolled()) >= 7 && s.getMonthsSinceActive() < 12);
        System.out.println("Are there Students enrolled 7+ years ago and still active = " + activeOlds);

        // 5 active olds
        System.out.println("5 Students enrolled 7+ years ago and still active:");
        students.stream()
                .filter(s -> (s.getAge() - s.getAgeEnrolled()) >= 7 && s.getMonthsSinceActive() < 12)
                .limit(5)
                .forEach(System.out::println);

        var longTimeLearners = students.stream()
                .filter(s -> (s.getAge() - s.getAgeEnrolled()) >= 7 && s.getMonthsSinceActive() < 12)
                .limit(5)
                .toArray(Student[]::new);
    }
}
