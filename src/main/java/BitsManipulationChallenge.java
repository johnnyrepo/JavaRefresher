import java.math.BigDecimal;
import java.util.Arrays;

public class BitsManipulationChallenge {

    public static void main(String[] args) {
        checkSumBytes();

        // https://www.educative.io/blog/bit-manipulation-in-java
        checkEvenNumber();
        checkToggleCase();
        checkCountSetBits();
        checkUniqueNumber();
    }

    private static void checkSumBytes() {
        System.out.println("=> checkSumBytes()");
        System.out.println("1=" + binaryStringToLong("1"));
        System.out.println("11=" + binaryStringToLong("11"));
        System.out.println("101=" + binaryStringToLong("101"));
        System.out.println("1010=" + binaryStringToLong("1010"));
        System.out.println("1011=" + binaryStringToLong("1011"));

        System.out.println('1' - '0');
        System.out.println('0' - '0');

        System.out.println(sumBytes("11", "1"));
        System.out.println(sumBytes("1010", "1011"));
        System.out.println(sumBytes("10100000100100110110010000010101111011011001101110111111111101000000101111001110001111100001101",
                "110101001011101110001111100110001010100001101011101010000011011011001011101111001100000011011110011"));
    }

    private static String sumBytes(String number1, String number2) {
        long num1 = binaryStringToLong(number1);
        long num2 = binaryStringToLong(number2);
        System.out.println("num1 + num2 = " + num1 + " + " + num2);
        long result = num1 + num2;
        return Long.toBinaryString(result);
    }

    private static long binaryStringToLong(String number) {
        long result = 0;
        for (var i = number.length() - 1; i >= 0; i--) {
            // "11" = "1*2^1 + 1*2^0" = "3"
            if (number.charAt(i) == '1') {
                result += Math.pow(2, number.length() - i - 1);
            }
        }

        return result;
    }

    private static BigDecimal binaryStringToDecimal(String number) {
        BigDecimal result = BigDecimal.ZERO;
        for (var i = number.length() - 1; i >= 0; i--) {
            // "11" = "1*2^1 + 1*2^0" = "3"
            if (number.charAt(i) == '1') {
                result.add(BigDecimal.TWO.pow(number.length() - i - 1));
            }
        }

        return result;
    }

    private static void checkEvenNumber() {
        System.out.println("\n=> checkEvenNumber()");
        System.out.printf("Is '%d' an even number? %s %n", 1, (isEvenNumber(1) ? "YES" : "NO"));
        System.out.printf("Is '%d' an even number? %s %n", 2, (isEvenNumber(2) ? "YES" : "NO"));
        System.out.printf("Is '%d' an even number? %s %n", 9, (isEvenNumber(9) ? "YES" : "NO"));
        System.out.printf("Is '%d' an even number? %s %n", 100, (isEvenNumber(100) ? "YES" : "NO"));
    }

    private static boolean isEvenNumber(int number) {
        return (number & 1) == 0;
    }

    private static void checkToggleCase() {
        System.out.println("\n=> checkToggleCase()");
        System.out.printf("'%c' char toggled is '%c' %n", 'a', 'a'^32);
        System.out.printf("'%c' char toggled is '%c' %n", 'v', 'v'^32);
        System.out.printf("'%c' char toggled is '%c' %n", 'O', 'O'^32);
        System.out.printf("'%c' char toggled is '%c' %n", 'Z', 'Z'^32);
    }

    private static void checkCountSetBits() {
        System.out.println("\n=> checkCountSetBits()");
        System.out.printf("'%d' numbers has '%d' set bits %n", 125, countSetBits(125));
        System.out.printf("'%d' numbers has '%d' set bits %n", 128, countSetBits(128));
        System.out.printf("'%d' numbers has '%d' set bits %n", 256, countSetBits(256));
    }

    private static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    private static void checkUniqueNumber() {
        System.out.println("\n=> checkUniqueNumber()");

        int[] nums1 = { 4, 1, 2, 9, 1, 4, 2 };
        int[] nums2 = { 1, 2, 3, 7, 1, 2, 3 };
        int[] nums3 = { 1, 4, 7, 8, 2, 5, 7 };

        System.out.printf("In an array of %s the unique number is: '%d' %n", Arrays.toString(nums1), findUniqueNumber(nums1));
        System.out.printf("In an array of %s the unique number is: '%d' %n", Arrays.toString(nums2), findUniqueNumber(nums2));
        System.out.printf("In an array of %s the unique number is: '%d' %n", Arrays.toString(nums3), findUniqueNumber(nums3));
    }

    private static int findUniqueNumber(int[] nums) {
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor;
    }

}
