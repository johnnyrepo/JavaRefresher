import java.time.Year;

public class TextBlockAndStringFormatChallenge {

    /**
     * New String formatting options.
     * Since Java 15
     */
    public static void main(String[] args) {
        // 1. Text block support
        // Pre Java 15
        String bulletIn = "Print a Bulleted List:\n"
                + "\t\u2022 First Point\n"
                + "\t\t\u2022 Sub Point";
        System.out.println(bulletIn);

        // Post Java 15
        String textBlock = """
                Print a Bulleted List:
                    \u2022 First Point
                        \u2022 Sub Point
                """;
        System.out.println(textBlock);

        //2. Formatted string support for System.out.print() & System.out.println()
        String formattedString = String.format("Current year is: %d", Year.now().getValue());
        System.out.println(formattedString);

        // 3. String.formatted() method introduced
        formattedString = "Current year is: %d".formatted(Year.now().getValue());
        System.out.println(formattedString);
    }

}
