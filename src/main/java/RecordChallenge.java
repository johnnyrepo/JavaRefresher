import record.StudentRecord;

public class RecordChallenge {

    /**
     * Records.
     * Since Java 14. Officially supported since Java 16
     */
    public static void main(String[] args) {
        var studentRec1 = new StudentRecord("1", "John", "1995", "Philosophy,Maths");
        var studentRec2 = new StudentRecord("2", "Mary", "1997", "Arts,Design");
        var studentRec3 = new StudentRecord("3", "Bob", "1999", "Physics,Agriculture,Sports");

        // toString()
        System.out.println("==> toString()");
        System.out.println(studentRec1);
        System.out.println(studentRec2);
        System.out.println(studentRec3);

        // getters
        System.out.println("==> getters()");
        System.out.println("Student " + studentRec1.name() + " is taking " + studentRec1.classList());
        System.out.println("Student " + studentRec2.name() + " is taking " + studentRec2.classList());
        System.out.println("Student " + studentRec3.name() + " is taking " + studentRec3.classList());

        // hashCode()
        System.out.println("==> hashCode()");
        System.out.println(studentRec1.hashCode());

        // equals()
        System.out.println("==> equals()");
        var studentRec4 = new StudentRecord("1", "John", "1995", "Philosophy,Maths");
        System.out.println(studentRec1.equals(studentRec2));
        System.out.println(studentRec1.equals(studentRec4));
    }

}
