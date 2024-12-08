package immutable.sealed.iface;

import java.util.function.Predicate;

/**
 * Sealed interface.
 * Since Java 17
 */
public sealed interface SealedInterface permits BetterInterface, StringsChecker {

    boolean testData(Predicate<String> p, String... strings);

}
