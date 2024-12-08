import java.util.Scanner;

public class ConsoleInputChallenge {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sum = 0;
        var i = 1;
        while (i <= 5) {
            System.out.println("Enter number #" + i + ": ");
            String numberStr = scanner.nextLine();
            try {
                sum += Integer.parseInt(numberStr);
                i++;
            } catch (Exception e) {
                System.out.println("Invalid number");
            }
        }

        System.out.println("The sum of the 5 numbers = " + sum);
    }

}
