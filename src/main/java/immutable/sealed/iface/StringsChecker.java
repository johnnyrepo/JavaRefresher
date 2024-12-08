package immutable.sealed.iface;

import java.util.function.Predicate;

public final class StringsChecker implements SealedInterface {
    @Override
    public boolean testData(Predicate<String> p, String... strings) {
        for (var s : strings) {
            if (p.test(s)) {
                return false;
            }
        }
        return true;
    }
}
