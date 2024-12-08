package immutable.sealed;

/**
 * Sealed class.
 *
 * 1. 'sealed' modifier can be used for both outer types and nested types.
 * 2. When used, a 'permits' clause is also required in most cases,
 *    which lists the allowed subclasses.
 * 3. Subclasses can be nested classes, classes declared in the same file,
 *    classes in the same package, or if using Java's modules, in the same module.
 * 4. In addition, the sealed keyword puts a requirement on all the subclasses
 *    that were declared in the 'permits' clause.
 *    It requires each subclass to declare one of the three valid modifiers
 *    for a class extending a sealed class: 'final', 'sealed' or 'non-sealed'
 *
 * Since Java 17
 */
public sealed abstract class SpecialAbstractClass permits FinalKid,
        NonSealedKid, SealedKid, SpecialAbstractClass.Kid {

    public abstract String aboutMe();

    public static final class Kid extends SpecialAbstractClass {
        @Override
        public String aboutMe() {
            return getClass().getName() + " -> I'm final nested permitted class";
        }
    }

}
