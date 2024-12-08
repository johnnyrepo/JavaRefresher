package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.*;

public class LambdaMiniChallenges {

    /**
     * FunctionalInterface to Lambda
     */
    public static void main(String[] args) {
        consumerLambda();

        functionLambda();

        supplierLambda();

        listAndArrayChallenge();
    }

    static void consumerLambda() {
        System.out.println("=> consumerLambda()");
        Consumer<String> printWords = new Consumer<String>() {
            @Override
            public void accept(String sentence) {
                String[] parts = sentence.split(" ");
                for (String part : parts) {
                    System.out.println(part);
                }
            }
        };

        Consumer<String> printWordsLambda = sentence -> {
            String[] parts = sentence.split(" ");
            for (String part : parts) {
                System.out.println(part);
            }
        };

        String testStr = "The quick brown fox jumps over the lazy dog";
        printWords.accept(testStr);
        System.out.println("-------");
        printWordsLambda.accept(testStr);
    }

    static void functionLambda() {
        System.out.println("=> functionLambda()");
        String testStr = "aBrAkAdAbRa";

        System.out.println(everySecondChar(testStr));
        System.out.println("-------");

        UnaryOperator<String> everySecondCharLambda = source -> {
            StringBuilder returnVal = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                if (i % 2 == 1) {
                    returnVal.append(source.charAt(i));
                }
            }

            return returnVal.toString();
        };

        System.out.println(everySecondCharLambda.apply(testStr));
        System.out.println("-------");
        System.out.println(everySecondCharacter(everySecondCharLambda, testStr));
    }


    private static String everySecondChar(String source) {
        StringBuilder returnVal = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (i % 2 == 1) {
                returnVal.append(source.charAt(i));
            }
        }

        return returnVal.toString();
    }

    private static String everySecondCharacter(Function<String, String> func, String source) {
        return func.apply(source);
    }

    static void supplierLambda() {
        System.out.println("=> supplierLambda()");

        Supplier<String> supplierLambda = () -> "I love Java";
        System.out.println(supplierLambda.get());
    }

    static void listAndArrayChallenge() {
        System.out.println("=> listAndArrayChallenge()");
        String[] names = {"John", "Jack", "Juliet", "Sindy", "Sara", "Bob"};
        var namesList = Arrays.asList(names);

        System.out.println("--> Transform to upper case");
        namesList.replaceAll(name -> name.toUpperCase());
        System.out.println(Arrays.toString(names)); // 'names' array is backed up by 'namesList'
        System.out.println("-------");

        System.out.println("--> Adding random middle initial");
        namesList.replaceAll(name -> {
            var i = new Random().nextInt(0, 5);
            return name + " "
                    + switch (i) {
                        case 0 -> "A";
                        case 1 -> "B";
                        case 2 -> "C";
                        case 3 -> "D";
                        case 4 -> "E";
                        default -> "";
                    }
                    + ".";
        });
        namesList.forEach(name -> System.out.println(name));
        System.out.println("-------");

        System.out.println("--> Add reversed name as last name");
        namesList.replaceAll(name -> name + (" " + revertString(name.split(" ")[0])));
        namesList.forEach(name -> System.out.println(name));
        System.out.println("-------");

        System.out.println("--> Remove if name == surname");
        List<String> newList = new ArrayList<>(List.of(names));
        newList.removeIf(name -> name.substring(0, name.indexOf(' ')).equals(
                name.substring(name.lastIndexOf(' ') + 1)));
        newList.forEach(name -> System.out.println(name));
        System.out.println("-------");
    }

    private static String revertString(String source) {
        return new StringBuilder(source).reverse().toString();
    }

}
