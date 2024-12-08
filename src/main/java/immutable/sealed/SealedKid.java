package immutable.sealed;

public sealed class SealedKid extends SpecialAbstractClass {

    @Override
    public String aboutMe() {
        return getClass().getName() + " -> I'm sealed permitted class";
    }

    public static final class GrandKid extends SealedKid {

    }

}
