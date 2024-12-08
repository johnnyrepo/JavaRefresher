import java.lang.invoke.MethodHandles;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StringChallenge {

    /**
     * New String methods.
     * Since Java 11 and son on...
     */
    public static void main(String[] args) {
        java11();
        java12();
        java15();
        java21();
    }

    /**
     * Since Java 11
     */
    private static void java11() {
        System.out.println("\n==> Java 11 ==>\n");

        // 1. isBlank()
        String blankStr = " ";
        System.out.printf("isBlank = %b %n", blankStr.isBlank());

        // 2. strip()
        String toStripStr = "\t to_strip \n";
        System.out.println(toStripStr.strip());

        // 3. stripLeading()
        String toStripStr2 = "\t to_strip_2 \n";
        System.out.println(toStripStr2.stripLeading());

        // 4. stripTrailing()
        String toStripStr3 = "\t to_strip_3 \n";
        System.out.println(toStripStr3.stripTrailing());

        // 5. lines()
        String linesStr = """
                line1
                line2
                line3""";
        linesStr.lines().forEach(System.out::print);
        System.out.println();

        // 6. repeat()
        String toRepeatStr = "to_repeat!";
        System.out.println(toRepeatStr.repeat(3));
    }

    /**
     * Since Java 12
     */
    private static void java12() {
        System.out.println("\n==> Java 12 ==>\n");

        // 1. indent()
        String toIndentStr = "to_indent";
        System.out.println(toIndentStr.indent(3));

        // 2. transform()
        String toTransformStr = "to_transform1,to_transform2,to_transform3";
        List<String> list = toTransformStr.transform(s -> Arrays.asList(s.split(",")));
        System.out.println(list);

        // 3. describeConstable()
        String constableStr = "constable";
        Optional<String> optional = constableStr.describeConstable();
        System.out.println(optional);

        // 4. resolveConstantDesc()
        String resolveConstantStr = "resolveConstant";
        String result = resolveConstantStr.resolveConstantDesc(MethodHandles.lookup());
        System.out.println(result);
    }

    /**
     * Since Java 15
     */
    private static void java15() {
        System.out.println("\n==> Java 15 ==>\n");

        // 1. formatted()
        System.out.println("1. %s 2. %s 3. %s ".formatted("one", "two", "three"));

        // 2. stripIndent()
        System.out.println("   hello world".stripIndent());
        System.out.println("hello world  ".stripIndent());
        System.out.println("   hello world  ".stripIndent());

        // 3. translateEscapes()
        String translateStr = "This is tab \t, Next New Line \n,next backspace \b,next Single Quotes \' next,Double Quotes \" ";
        System.out.println(translateStr.translateEscapes());
    }

    private static void java21() {
        System.out.println("\n==> Java 21 ==>\n");

        // 1. indexOf(int ch, int beginIndex, int endIndex)
        String indexOfStr1 = "Let the power be with you!";
        System.out.println(indexOfStr1.indexOf('w', 0, 15));
        System.out.println(indexOfStr1.indexOf('w', 15, indexOfStr1.length()));
        
        // 2. indexOf(String str, int beginIndex, int endIndex)
        String indexOfStr2 = "as busy, as it gets";
        System.out.println(indexOfStr2.indexOf("as", 0, 8));
        System.out.println(indexOfStr2.indexOf("as", 8, indexOfStr2.length()));

        // 3. splitWithDelimiters()
        String splitStr = "the red brown fox jumps over the lazy dog";
        String[] parts = splitStr.splitWithDelimiters(" ", 5);
        System.out.println(Arrays.stream(parts).collect(Collectors.joining("', '", "'", "'")));
    }

}
