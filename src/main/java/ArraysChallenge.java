import java.util.Arrays;
import java.util.Random;

public class ArraysChallenge {

    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 6, 7, 2, 9, 8};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.binarySearch(arr, 7));

        intArrayChallenge(10);

        twoDimensionalIntArrayChallenge(10);
    }

    private static void intArrayChallenge(int arrSize) {
        System.out.println("==> intArrayChallenge()");

        if (arrSize <= 0) {
            System.err.println("Size of array has to be positive number");
        }

        int[] arr = fillArray(arrSize);
        System.out.println(Arrays.toString(arr));

        Arrays.sort(arr);
        reverseArray(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static int[] fillArray(int arrSize) {
        Random r = new Random();
        int[] arr = new int[arrSize];
        for (int i = 0; i < arrSize; i++) {
            arr[i] = r.nextInt(101);
        }

        return arr;
    }

    private static void reverseArray(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length / 2; i++) {
            temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
    }

    private static void twoDimensionalIntArrayChallenge(int arrSize) {
        System.out.println("==> twoDimensionalIntArrayChallenge()");

        int[][] arr = new int[arrSize][];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = fillArray(arrSize);
        }

        // print out
        for (var outer : arr) {
            System.out.print("[");
            for (var element : outer) {
                System.out.print(element + " ");
            }
            System.out.println("]");
        }
        System.out.println();

        // Arrays print out
        System.out.println(Arrays.deepToString(arr));
    }

}
