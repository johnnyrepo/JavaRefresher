public class SwitchChallenge {

    /**
     * Enhanced "switch" statement.
     * Since Java 12
     */
    public static void main(String[] args) {
        // Traditional switch
        printWord('A');
        printWord('B');
        printWord('C');
        printWord('D');
        printWord('E');
        printWord('Y');

        // Enhanced switch
        printDayOfWeek(0);
        printDayOfWeek(1);
        printDayOfWeek(2);
        printDayOfWeek(3);
        printDayOfWeek(4);
        printDayOfWeek(5);
        printDayOfWeek(6);
        printDayOfWeek(-1);
    }

    /**
     * Pre Java 12
     * Regular switch statement
     */
    private static void printWord(char c) {
        switch (c) {
            case 'A':
                System.out.print("Letter " + c + " is ");
                System.out.println("Able");
                break;
            case 'B':
                System.out.print("Letter " + c + " is ");
                System.out.println("Baker");
                break;
            case 'C':
                System.out.print("Letter " + c + " is ");
                System.out.println("Charlie");
                break;
            case 'D':
                System.out.print("Letter " + c + " is ");
                System.out.println("Delta");
                break;
            case 'E':
                System.out.print("Letter " + c + " is ");
                System.out.println("Easy");
                break;
            default:
                System.out.println("Letter " + c + " not found");
        }
    }

    /**
     * Post Java 12
     * Enhanced "switch" statement.
     */
    private static void printDayOfWeek(int day) {
        String dayOfTheWeek = switch (day) {
            case 0 -> { yield "Sunday";}
            case 1 ->  "Monday";
            case 2 -> "Tuesday";
            case 3 -> "Wednesday";
            case 4 -> "Thursday";
            case 5 -> "Friday";
            case 6 -> "Saturday";
            default -> "Invalid Day";
        };

        System.out.println(day + " day of the week is " + dayOfTheWeek);
    }

}
