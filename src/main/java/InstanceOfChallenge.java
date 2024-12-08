import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InstanceOfChallenge {

    /**
     * "instanceof" improvements.
     * Pattern matching support
     * Since Java 16
     */
    public static void main(String[] args) {
        List<String> linkedList = new LinkedList<>();
        linkedList.add("elem1");
        linkedList.add("elem2");

        // Pre Java 16
        if (linkedList instanceof LinkedList) {
            System.out.println("\n==> Pre-Java 16 ==>\n");
            System.out.println(((LinkedList) linkedList).pop());
        }

        // Post Java 16
        if (linkedList instanceof LinkedList ll) {
            System.out.println("\n==> Java 16 ==>\n");
            System.out.println(ll.pop());
        }

        System.out.println(getDoubleUsingSwitch(1));
        System.out.println(getDoubleUsingSwitch(99.0f));
        System.out.println(getDoubleUsingSwitch("101"));
        System.out.println(getDoubleUsingSwitch(10.01));
    }

    // Pattern matching within switch-case statement
    static double getDoubleUsingSwitch(Object o) {
        return switch (o) {
            case Integer i -> i.doubleValue();
            case Float f -> f.doubleValue();
            case String s -> Double.parseDouble(s);
            default -> 0d;
        };
    }

}
