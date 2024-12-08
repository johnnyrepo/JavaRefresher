package generics;

import generics.model.LPAStudent;
import generics.model.Student;
import generics.util.QueryList;

import java.util.Comparator;
import java.util.List;


public class GenericsChallenge {

    public static void main(String[] args) {
        QueryList<LPAStudent> lpaStudents = new QueryList<>();
        for (int i = 0; i < 25; i++) {
            lpaStudents.add(new LPAStudent());
        }
        printList(lpaStudents);

        var lessThanHalfCourseStudents = lpaStudents
                .getMatches("PERCENTCOMPLETE", "50")
                .getMatches("COURSE", "Python");
        System.out.println("Students who's course completion < 50% (Sorted by id)");
        lessThanHalfCourseStudents.sort(Comparator.naturalOrder());
        printList(lessThanHalfCourseStudents);

        System.out.println("Students who's course completion < 50% (Sorted by year)");
        lessThanHalfCourseStudents.sort(new Student.YearComparator());
        printList(lessThanHalfCourseStudents);

        System.out.println("Students who's course completion < 50% (Sorted by percent complete)");
        lessThanHalfCourseStudents.sort(new LPAStudent.PercentCompleteComparator());
        printList(lessThanHalfCourseStudents);
    }

    public static void printList(List<? extends Student> students) {
        for (var student : students) {
            System.out.println(student);
        }
        System.out.println();
    }

}
