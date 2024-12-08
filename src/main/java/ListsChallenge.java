import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListsChallenge {

    public static void main(String[] args) {
        arrayListChallenge();
        arrayListVsLinkedListChallenge();
    }

    private static void arrayListChallenge() {
        // Creating special kind of Lists

        // Arrays.asList
        // Returned List is NOT resizable, but is mutable
        var newList = Arrays.asList("Sunday", "Monday", "Tuesday");

        // List.of
        // Returned List is IMMUTABLE
        var listOne = List.of("Synday", "Monday", "Tuesday");
    }

    private static void arrayListVsLinkedListChallenge() {
        // ArrayList vs LinkedList
        String arrayListCapacity = """
                ArrayList capacity:
                1. If you know the maximum number of possible items, then it's probably better to use an ArrayList, but set it's capacity.
                2. An ArrayList's index is an int type, so an ArrayList's capacity is limited to the maximum number of elements an int can hold, Integer.MAX_VALUE = 2,147,483,647.
                """;
        System.out.println(arrayListCapacity);

        String arrayListFavor = """
                ArrayList vs. LinkedList
                                                
                ArrayList:
                1. Is usually the better default choice for a List,
                especially if the List is used predominantly for storing and reading data.
                                                
                LinkedList:
                1. Can be more efficient, when items are being processed predominantly
                from either the head or tail of the list.
                2. If you're adding and processing or manipulating a large amount of elements,
                and the maximum elements isn't known, but may be great, 
                or if your number of elements may exceed Integer.MAX_VALUE.
                """;
        System.out.println(arrayListFavor);
    }

}
