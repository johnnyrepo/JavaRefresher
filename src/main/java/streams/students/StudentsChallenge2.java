package streams.students;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class StudentsChallenge2 {

    public static void main(String[] args) {
        Course pymc = new Course("PYMC", "Python Masterclass", 50);
        Course jmc = new Course("JMC", "Java Masterclass", 100);
        Course jgames = new Course("JGAME", "Creating games in Java");

        List<Student> students = IntStream.rangeClosed(1, 5000)
                .mapToObj(i -> Student.getRandomStudent(pymc, jmc))
                .toList();

        double totalPercent = students.stream()
                .mapToDouble(s -> s.getPercentComplete("JMC"))
                .reduce(0, Double::sum); // sum() - is alternative
        double avgPercent = totalPercent / students.size();
        System.out.printf("Average Percentage Complete = %.2f%% %n", avgPercent);

        int topPercent = (int) (1.25 * avgPercent);
        System.out.printf("Best Percentage Complete = %d%% %n", topPercent);

        List<Student> hardWorkers = students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .toList();
        System.out.println("hardWorkers = " + hardWorkers.size());

        hardWorkers = students.stream()
                .filter(s -> s.getMonthsSinceActive("JMC") == 0)
                .filter(s -> s.getPercentComplete("JMC") >= topPercent)
                .sorted(Comparator.comparing(Student::getYearEnrolled))
                .limit(10)
                .toList();

        hardWorkers.forEach(s -> {
            s.addCourse(jgames);
            System.out.println(s);
        });
    }

}
